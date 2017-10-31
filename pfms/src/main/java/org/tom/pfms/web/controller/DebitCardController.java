package org.tom.pfms.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tom.pfms.common.dto.DebitCardDTO;
import org.tom.pfms.common.dto.DebitSummaryDTO;
import org.tom.pfms.common.dto.KeyValuePair;
import org.tom.pfms.common.dto.PaginatedDTO;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.dto.UserDTO;
import org.tom.pfms.common.exception.ServiceException;
import org.tom.pfms.common.utils.ConstantSettings;
import org.tom.pfms.common.utils.MessageUtils;
import org.tom.pfms.service.DebitCardService;

import com.google.gson.Gson;

@Controller
public class DebitCardController extends BaseController {
	@Autowired
	private DebitCardService debitCardService = null;
	
	@RequestMapping("/bank/debitcards")
	public String queryDebitCards(HttpServletRequest request)
	    throws Exception {
		String action = request.getParameter("Action");
		UserDTO user = (UserDTO)request.getSession(false).getAttribute(ConstantSettings.LOGIN_USER);
		String username = user.getUserName();
		DebitCardDTO debitCardDTO = (DebitCardDTO)encapsulateSubmitDTO(request, DebitCardDTO.class);
		debitCardDTO.setCardOwner(username);
		String pageNo = request.getParameter("pageNo");
		int iPageNo = pageNo == null ? 1 : Integer.parseInt(pageNo);
		RequestParam rp = new RequestParam();
		rp.setLoginUserName(username);
		rp.setRequestObject(debitCardDTO);
		rp.setPageNo(iPageNo);
		saveRequestParam(request, rp);
		if(ConstantSettings.ACTION_BACK.equals(action)){
			rp = getPreRequestParam(request);
		}
		PaginatedDTO<DebitCardDTO> result = null;
		String message = null;
		try{
			result = debitCardService.queryDebitCards(rp);
			result.setPageNo(iPageNo);
			request.setAttribute(ConstantSettings.KEY_RESULT, result);
		}catch(ServiceException e) {
			log.error("queryDebitCards", e);
			message = MessageUtils.getMessage(ConstantSettings.LABEL_SYSTEM_ERROR);
			throw new Exception(e);
		}
		request.setAttribute(ConstantSettings.KEY_MESSAGE, message);
		request.setAttribute(ConstantSettings.KEY_PARAM, rp);
    	return "bank/debit_cards_list";
	}
	
	
	@RequestMapping(value="/bank/debit_add", method=RequestMethod.GET)
	public String addForm(HttpServletRequest request) throws Exception {
		return "bank/debit_cards_add";
	}
	
	
	@RequestMapping(value="/bank/debit/add",method=RequestMethod.POST)
	public String addDebot(HttpServletRequest request) throws Exception {
		DebitCardDTO debitCardDTO = (DebitCardDTO)encapsulateSubmitDTO(request, DebitCardDTO.class);
		UserDTO user = (UserDTO)request.getSession(false).getAttribute(ConstantSettings.LOGIN_USER);
		String username = user.getUserName();
		debitCardDTO.setCardOwner(username);
		RequestParam rp = new RequestParam();
		rp.setRequestObject(debitCardDTO);
		rp.setLoginUserName(username);
		String message = null;
		//saveRequestParam(request, rp);
		try{
			debitCardService.saveDebit(rp);
			rp = getPreRequestParam(request);
			request.setAttribute(ConstantSettings.KEY_PARAM, rp);
		}catch(ServiceException e) {
			log.error("addForm", e);
			message = MessageUtils.getMessage(ConstantSettings.LABEL_SYSTEM_ERROR);
			throw new Exception(e);
		}
		request.setAttribute(ConstantSettings.KEY_MESSAGE, message);
		return "redirect:/bank/debitcards";
	}
	
	@RequestMapping(value="/bank/debitedit", method=RequestMethod.GET)
	public String editForm(HttpServletRequest request) throws Exception {
		UserDTO user = (UserDTO)request.getSession(false).getAttribute(ConstantSettings.LOGIN_USER);
		String username = user.getUserName();
		DebitCardDTO debitCardDTO = (DebitCardDTO)encapsulateSubmitDTO(request, DebitCardDTO.class);
		debitCardDTO.setCardOwner(username);
		String pageNo = request.getParameter("pageNo");
		int iPageNo = pageNo == null ? 1 : Integer.parseInt(pageNo);
		RequestParam rp = new RequestParam();
		rp.setLoginUserName(username);
		rp.setRequestObject(debitCardDTO);
		rp.setPageNo(iPageNo);
		DebitCardDTO result = null;
		String message = null;
		try{
			PaginatedDTO<DebitCardDTO> tmp = null;
			tmp = debitCardService.queryDebitCards(rp);
			if(tmp != null) {
			    result = tmp.getDataList().get(0);
			}
			request.setAttribute(ConstantSettings.KEY_RESULT, result);
		}catch(ServiceException e) {
			log.error("editForm", e);
			message = MessageUtils.getMessage(ConstantSettings.LABEL_SYSTEM_ERROR);
			throw new Exception(e);
		}
		request.setAttribute(ConstantSettings.KEY_MESSAGE, message);
		request.setAttribute(ConstantSettings.KEY_PARAM, rp);
    	return "bank/debit_cards_edit";
	}
	
	@RequestMapping(value="/bank/debit/update",method=RequestMethod.POST)
	public String uploadDebit(HttpServletRequest request) throws Exception {
		DebitCardDTO debitCardDTO = (DebitCardDTO)encapsulateSubmitDTO(request, DebitCardDTO.class);
		UserDTO user = (UserDTO)request.getSession(false).getAttribute(ConstantSettings.LOGIN_USER);
		String username = user.getUserName();
		debitCardDTO.setCardOwner(username);
		RequestParam rp = new RequestParam();
		rp.setRequestObject(debitCardDTO);
		rp.setLoginUserName(username);
		String message = null;
		//saveRequestParam(request, rp);
		try{
			debitCardService.updateDebit(rp);
			rp = getPreRequestParam(request);
			request.setAttribute(ConstantSettings.KEY_PARAM, rp);
		}catch(ServiceException e) {
			log.error("updateDebit", e);
			message = MessageUtils.getMessage(ConstantSettings.LABEL_SYSTEM_ERROR);
			throw new Exception(e);
		}
		request.setAttribute(ConstantSettings.KEY_MESSAGE, message);
		return "redirect:/bank/debitcards?Action=back";
	}
	
	@RequestMapping(value="/bank/debit/delete",method=RequestMethod.GET)
	public String invalid(HttpServletRequest request) throws Exception {
		DebitCardDTO debitCardDTO = (DebitCardDTO)encapsulateSubmitDTO(request, DebitCardDTO.class);
		UserDTO user = (UserDTO)request.getSession(false).getAttribute(ConstantSettings.LOGIN_USER);
		String username = user.getUserName();
		debitCardDTO.setCardOwner(username);
		RequestParam rp = new RequestParam();
		rp.setRequestObject(debitCardDTO);
		rp.setLoginUserName(username);
		String message = null;
		saveRequestParam(request, rp);
		try{
			debitCardService.invalidDebit(rp);
			rp = getPreRequestParam(request);
			request.setAttribute(ConstantSettings.KEY_PARAM, rp);
		}catch(ServiceException e) {
			log.error("updateDebit", e);
			message = MessageUtils.getMessage(ConstantSettings.LABEL_SYSTEM_ERROR);
			throw new Exception(e);
		}
		request.setAttribute(ConstantSettings.KEY_MESSAGE, message);
		return "redirect:/bank/debitcards";
	}
	
	@RequestMapping("/bank/debitsummary")
	public String showDebitSummary(HttpServletRequest request) throws Exception {
		return "bank/debit_summary";
	}
	
	@RequestMapping(value="/bank/debit_summary_data",method=RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String queryDebitSummaryData(HttpServletRequest request) throws Exception {
		List<DebitSummaryDTO> result = null;
		String message = null;
		try{
			result = debitCardService.queryDebitSummary();
		}catch(ServiceException e) {
			log.error("updateDebit", e);
			message = MessageUtils.getMessage(ConstantSettings.LABEL_SYSTEM_ERROR);
			throw new Exception(e);
		}
		Gson gsonUtil = new Gson();
		String json = gsonUtil.toJson(result);
		return json;
	}
	
	
	@RequestMapping(value="/bank/querydebits",method=RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String queryDebits(HttpServletRequest request) 
	    throws Exception {
		List<KeyValuePair> result = null;
		Gson gsonUtil = new Gson();
		String message = null;
		String json = "";
		String bankCode = request.getParameter("bankcode");
		UserDTO user = (UserDTO)request.getSession(false).getAttribute(ConstantSettings.LOGIN_USER);
		String username = user.getUserName();
		RequestParam rp = new RequestParam();
		rp.setLoginUserName(username);
		rp.setRequestObject(bankCode);
		try{
			result = debitCardService.queryDebits(rp);
			json = gsonUtil.toJson(result);
		}catch(ServiceException e) {
			log.error("updateDebit", e);
			message = MessageUtils.getMessage(ConstantSettings.LABEL_SYSTEM_ERROR);
			throw new Exception(e);
		}
		if(message == null) message = "";
		json = "{\"status\":\"1\", \"message\":\""+message+"\",\"data\":"+json+"}";
		return json;
	}
}
