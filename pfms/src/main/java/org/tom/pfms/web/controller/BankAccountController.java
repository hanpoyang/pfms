package org.tom.pfms.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tom.pfms.common.dto.BankLoanDTO;
import org.tom.pfms.common.dto.KeyValuePair;
import org.tom.pfms.common.dto.OtherAccountDTO;
import org.tom.pfms.common.dto.PaginatedDTO;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.dto.UserDTO;
import org.tom.pfms.common.exception.ServiceException;
import org.tom.pfms.common.utils.AES256;
import org.tom.pfms.common.utils.ConstantSettings;
import org.tom.pfms.common.utils.MessageUtils;
import org.tom.pfms.service.BankAccountService;

import com.google.gson.Gson;

@Controller
public class BankAccountController extends BaseController {
	
	@Autowired
	private BankAccountService bankAccountService = null;
	
	@RequestMapping("/bank/loan")
    public String shoLoanAccount(HttpServletRequest request) throws Exception {
		String action = request.getParameter("Action");
		UserDTO user = (UserDTO)request.getSession(false).getAttribute(ConstantSettings.LOGIN_USER);
		String username = user.getUserName();
		BankLoanDTO bankLoanDTO = (BankLoanDTO)encapsulateSubmitDTO(request, BankLoanDTO.class);
		bankLoanDTO.setCardOwner(username);
		String pageNo = request.getParameter("pageNo");
		int iPageNo = pageNo == null ? 1 : Integer.parseInt(pageNo);
		RequestParam rp = new RequestParam();
		rp.setRequestObject(bankLoanDTO);
		rp.setPageNo(iPageNo);
		saveRequestParam(request, rp);
		if(ConstantSettings.ACTION_BACK.equals(action)){
			rp = getPreRequestParam(request);
		}
		PaginatedDTO<BankLoanDTO> result = null;
		String message = null;
		try{
			result = bankAccountService.queryCreditList(rp);
			result.setPageNo(iPageNo);
			request.setAttribute(ConstantSettings.KEY_RESULT, result);
		}catch(ServiceException e) {
			log.error("showList", e);
			message = MessageUtils.getMessage(ConstantSettings.LABEL_SYSTEM_ERROR);
			throw new Exception(e);
		}
		request.setAttribute(ConstantSettings.KEY_MESSAGE, message);
		request.setAttribute(ConstantSettings.KEY_PARAM, rp);
    	return "bank/loan_account_list";
    }
	
	@RequestMapping(value="/bank/loan_add", method=RequestMethod.GET)
	public String add_form(HttpServletRequest request) throws Exception {
		return "bank/loan_account_add";
	}
	
	@RequestMapping(value="/bank/banks", method=RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
	@ResponseBody()
	public String queryBanks(HttpServletRequest request) throws Exception {
		StringBuilder sbuf = new StringBuilder("{\"status\":");
		try{
			List<KeyValuePair> bankList = bankAccountService.queryBanks();
			Gson gson = new Gson();
			String jsonStr = gson.toJson(bankList);
			log.info("jsonStr:"+jsonStr);
			sbuf.append("\"1\",\"message\":\"\",\"data\":");
			sbuf.append(jsonStr);
			sbuf.append("}");
		}catch(ServiceException e) {
			log.error("queryBanks", e);
		}
		return sbuf.toString();
	}
	
	@RequestMapping(value="/bank/loan/add",method=RequestMethod.POST)
	public String addLoan(HttpServletRequest request) throws Exception {
		BankLoanDTO loanDTO = (BankLoanDTO)encapsulateSubmitDTO(request, BankLoanDTO.class);
		UserDTO user = (UserDTO)request.getSession(false).getAttribute(ConstantSettings.LOGIN_USER);
		String username = user.getUserName();
		loanDTO.setCardOwner(username);
		RequestParam rp = new RequestParam();
		rp.setRequestObject(loanDTO);
		rp.setLoginUserName(username);
		PaginatedDTO<BankLoanDTO> result = null;
		String message = null;
		saveRequestParam(request, rp);
		try{
			bankAccountService.saveLoan(rp);
			rp = getPreRequestParam(request);
			saveRequestParam(request, rp);
			log.info("----------------rp:"+rp);
			result = bankAccountService.queryCreditList(rp);
			result.setPageNo(rp.getPageNo());
			request.setAttribute(ConstantSettings.KEY_RESULT, result);
			request.setAttribute(ConstantSettings.KEY_PARAM, rp);
		}catch(ServiceException e) {
			log.error("addLoan", e);
			message = MessageUtils.getMessage(ConstantSettings.LABEL_SYSTEM_ERROR);
			throw new Exception(e);
		}
		request.setAttribute(ConstantSettings.KEY_MESSAGE, message);
		return "redirect:/bank/loan";
	}
	
	@RequestMapping(value="/bank/loan/delete",method=RequestMethod.GET)
	public String deleteLoan(HttpServletRequest request) throws Exception {
		BankLoanDTO loanDTO = (BankLoanDTO)encapsulateSubmitDTO(request, BankLoanDTO.class);
		UserDTO user = (UserDTO)request.getSession(false).getAttribute(ConstantSettings.LOGIN_USER);
		String username = user.getUserName();
		loanDTO.setCardOwner(username);
		RequestParam rp = new RequestParam();
		rp.setRequestObject(loanDTO);
		rp.setLoginUserName(username);
		PaginatedDTO<BankLoanDTO> result = null;
		String message = null;
		saveRequestParam(request, rp);
		try{
			bankAccountService.inValidLoan(rp);
			rp = getPreRequestParam(request);
			saveRequestParam(request, rp);
			log.info("----------------rp:"+rp);
			result = bankAccountService.queryCreditList(rp);
			result.setPageNo(rp.getPageNo());
			request.setAttribute(ConstantSettings.KEY_RESULT, result);
			request.setAttribute(ConstantSettings.KEY_PARAM, rp);
		}catch(ServiceException e) {
			log.error("deleteLoan", e);
			message = MessageUtils.getMessage(ConstantSettings.LABEL_SYSTEM_ERROR);
			throw new Exception(e);
		}
		request.setAttribute(ConstantSettings.KEY_MESSAGE, message);
		return "redirect:/bank/loan";
	}
}
