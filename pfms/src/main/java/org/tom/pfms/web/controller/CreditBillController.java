package org.tom.pfms.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tom.pfms.common.dto.BankLoanDTO;
import org.tom.pfms.common.dto.CreditBillDTO;
import org.tom.pfms.common.dto.PaginatedDTO;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.dto.UserDTO;
import org.tom.pfms.common.exception.ServiceException;
import org.tom.pfms.common.utils.ConstantSettings;
import org.tom.pfms.common.utils.MessageUtils;
import org.tom.pfms.service.CreditBillService;

@Controller
public class CreditBillController extends BaseController {
    
	@Resource
	private CreditBillService creditBillService;
	
	@RequestMapping("/bank/creditbill")
	public String queryCreditBill(HttpServletRequest request) throws Exception {
		String action = request.getParameter("Action");
		UserDTO user = (UserDTO)request.getSession(false).getAttribute(ConstantSettings.LOGIN_USER);
		String username = user.getUserName();
		CreditBillDTO creditBillDTO = (CreditBillDTO)encapsulateSubmitDTO(request, CreditBillDTO.class);
		String pageNo = request.getParameter("pageNo");
		int iPageNo = pageNo == null ? 1 : Integer.parseInt(pageNo);
		RequestParam rp = new RequestParam();
		rp.setRequestObject(creditBillDTO);
		rp.setPageNo(iPageNo);
		rp.setLoginUserName(username);
		saveRequestParam(request, rp);
		if(ConstantSettings.ACTION_BACK.equals(action)){
			rp = getPreRequestParam(request);
		}
		PaginatedDTO<CreditBillDTO> result = null;
		String message = null;
		try{
			result = creditBillService.queryCreditBill(rp);
			result.setPageNo(iPageNo);
			request.setAttribute(ConstantSettings.KEY_RESULT, result);
		}catch(ServiceException e) {
			log.error("showList", e);
			message = MessageUtils.getMessage(ConstantSettings.LABEL_SYSTEM_ERROR);
			throw new Exception(e);
		}
		request.setAttribute(ConstantSettings.KEY_MESSAGE, message);
		request.setAttribute(ConstantSettings.KEY_PARAM, rp);
    	return "bank/bank_credit_bill";
    }
	
	@RequestMapping("/bank/creditdetail")
	public String queryCreditBillDetail(HttpServletRequest request) throws Exception {
		String action = request.getParameter("Action");
		UserDTO user = (UserDTO)request.getSession(false).getAttribute(ConstantSettings.LOGIN_USER);
		String username = user.getUserName();
		CreditBillDTO creditBillDTO = (CreditBillDTO)encapsulateSubmitDTO(request, CreditBillDTO.class);
		RequestParam rp = new RequestParam();
		rp.setRequestObject(creditBillDTO);
		rp.setLoginUserName(username);
		saveRequestParam(request, rp);
		if(ConstantSettings.ACTION_BACK.equals(action)){
			rp = getPreRequestParam(request);
		}
		CreditBillDTO result = null;
		String message = null;
		try{
			result = creditBillService.queryCreditBillDetail(rp);
			request.setAttribute(ConstantSettings.KEY_RESULT, result);
		}catch(ServiceException e) {
			log.error("queryCreditBillDetail", e);
			message = MessageUtils.getMessage(ConstantSettings.LABEL_SYSTEM_ERROR);
			throw new Exception(e);
		}
		request.setAttribute(ConstantSettings.KEY_MESSAGE, message);
    	return "bank/bank_credit_detail";
    }
	
	@RequestMapping("/bank/clear")
	public String updateIsClear(HttpServletRequest request) throws Exception {
		String action = request.getParameter("Action");
		UserDTO user = (UserDTO)request.getSession(false).getAttribute(ConstantSettings.LOGIN_USER);
		String username = user.getUserName();
		CreditBillDTO creditBillDTO = (CreditBillDTO)encapsulateSubmitDTO(request, CreditBillDTO.class);
		creditBillDTO.setIsClear("Y");
		RequestParam rp = new RequestParam();
		rp.setRequestObject(creditBillDTO);
		rp.setLoginUserName(username);
		saveRequestParam(request, rp);
		if(ConstantSettings.ACTION_BACK.equals(action)){
			rp = getPreRequestParam(request);
		}
		String message = null;
		try{
			creditBillService.updateIsClear(rp);
		}catch(ServiceException e) {
			log.error("queryCreditBillDetail", e);
			message = MessageUtils.getMessage(ConstantSettings.LABEL_SYSTEM_ERROR);
			throw new Exception(e);
		}
		request.setAttribute(ConstantSettings.KEY_MESSAGE, message);
    	return "redirect:/bank/creditbill";
    }
}
