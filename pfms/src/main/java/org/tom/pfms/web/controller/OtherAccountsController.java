package org.tom.pfms.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tom.pfms.common.dto.OtherAccountDTO;
import org.tom.pfms.common.dto.PaginatedDTO;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.dto.UserDTO;
import org.tom.pfms.common.exception.ServiceException;
import org.tom.pfms.common.utils.AES256;
import org.tom.pfms.common.utils.ConstantSettings;
import org.tom.pfms.common.utils.MessageUtils;
import org.tom.pfms.service.OtherAccountsService;

@Controller
public class OtherAccountsController extends BaseController {
	@Autowired
    private OtherAccountsService otherAccountService;
	
	@RequestMapping("/otheraccounts/accounts")
	public String showList(HttpServletRequest request) throws Exception {
		String action = request.getParameter("Action");
		UserDTO user = (UserDTO)request.getSession(false).getAttribute(ConstantSettings.LOGIN_USER);
		String username = user.getUserName();
		OtherAccountDTO otherAccountsDTO = (OtherAccountDTO)encapsulateSubmitDTO(request, OtherAccountDTO.class);
		otherAccountsDTO.setAccountOwner(username);
		String pageNo = request.getParameter("pageNo");
		int iPageNo = pageNo == null ? 1 : Integer.parseInt(pageNo);
		RequestParam rp = new RequestParam();
		rp.setRequestObject(otherAccountsDTO);
		rp.setPageNo(iPageNo);
		saveRequestParam(request, rp);
		if(ConstantSettings.ACTION_BACK.equals(action)){
			rp = getPreRequestParam(request);
		}
		PaginatedDTO<OtherAccountDTO> result = null;
		String message = null;
		try{
			result = otherAccountService.queryOtherAccountsList(rp);
			List<OtherAccountDTO> accountList = result.getDataList();
			for(OtherAccountDTO account : accountList) {
				account.setUsername(AES256.decrypt(account.getUsername()));
				account.setPassword(AES256.decrypt(account.getPassword()));
			}
			result.setPageNo(iPageNo);
			request.setAttribute(ConstantSettings.KEY_RESULT, result);
		}catch(ServiceException e) {
			log.error("showList", e);
			message = MessageUtils.getMessage(ConstantSettings.LABEL_SYSTEM_ERROR);
			throw new Exception(e);
		}
		request.setAttribute(ConstantSettings.KEY_MESSAGE, message);
		request.setAttribute(ConstantSettings.KEY_PARAM, rp);
		return "otheraccounts/accounts";
	}
	
	@RequestMapping("/otheraccounts/delete")
	public String delete(HttpServletRequest request) throws Exception {
		log.info("enter....delete");
        OtherAccountDTO otherAccountsDTO = (OtherAccountDTO)encapsulateSubmitDTO(request, OtherAccountDTO.class);
		RequestParam rp = new RequestParam();
		rp.setRequestObject(otherAccountsDTO);
		PaginatedDTO<OtherAccountDTO> result = null;
		String message = null;
		saveRequestParam(request, rp);
		try{
			otherAccountService.delete(rp);
		}catch(ServiceException e) {
			log.error("delete", e);
			message = MessageUtils.getMessage(ConstantSettings.LABEL_SYSTEM_ERROR);
			throw new Exception(e);
		}
		request.setAttribute(ConstantSettings.KEY_MESSAGE, message);
		request.setAttribute(ConstantSettings.KEY_PARAM, rp);
		return "redirect:/otheraccounts/accounts";
	}
	
	@RequestMapping("/otheraccounts/addform")
	public String gotoAdd(){
		return "otheraccounts/add";
	}
	
	@RequestMapping(value="/otheraccounts/add", method=RequestMethod.POST)
	public String saveAdd(HttpServletRequest request) throws Exception {
		OtherAccountDTO otherAccountsDTO = (OtherAccountDTO)encapsulateSubmitDTO(request, OtherAccountDTO.class);
		otherAccountsDTO.setUsername(AES256.encrypt(otherAccountsDTO.getUsername()));
		otherAccountsDTO.setPassword(AES256.encrypt(otherAccountsDTO.getPassword()));
		UserDTO user = (UserDTO)request.getSession(false).getAttribute(ConstantSettings.LOGIN_USER);
		String username = user.getUserName();
		otherAccountsDTO.setAccountOwner(username);
		RequestParam rp = new RequestParam();
		rp.setRequestObject(otherAccountsDTO);
		rp.setLoginUserName(username);
		PaginatedDTO<OtherAccountDTO> result = null;
		String message = null;
		saveRequestParam(request, rp);
		try{
			otherAccountService.saveAdd(rp);
			rp = getPreRequestParam(request);
			saveRequestParam(request, rp);
			log.info("----------------rp:"+rp);
			result = otherAccountService.queryOtherAccountsList(rp);
			List<OtherAccountDTO> accountList = result.getDataList();
			for(OtherAccountDTO account : accountList) {
				account.setUsername(AES256.decrypt(account.getUsername()));
				account.setPassword(AES256.decrypt(account.getPassword()));
			}
			result.setPageNo(rp.getPageNo());
			request.setAttribute(ConstantSettings.KEY_RESULT, result);
			request.setAttribute(ConstantSettings.KEY_PARAM, rp);
		}catch(ServiceException e) {
			log.error("saveAdd", e);
			message = MessageUtils.getMessage(ConstantSettings.LABEL_SYSTEM_ERROR);
			throw new Exception(e);
		}
		return "otheraccounts/accounts";
	}
	
	@RequestMapping("/otheraccounts/editform")
	public String gotoEdit(HttpServletRequest request){
		UserDTO user = (UserDTO)request.getSession(false).getAttribute(ConstantSettings.LOGIN_USER);
		String username = user.getUserName();
		OtherAccountDTO otherAccountsDTO = (OtherAccountDTO)encapsulateSubmitDTO(request, OtherAccountDTO.class);
		otherAccountsDTO.setAccountOwner(username);
		RequestParam rp = new RequestParam();
		rp.setRequestObject(otherAccountsDTO);
		rp.setPageNo(1);
		PaginatedDTO<OtherAccountDTO> result = null;
		String message = null;
		saveRequestParam(request, rp);
		OtherAccountDTO account = null;
		try{
			System.out.println(4);
			result = otherAccountService.queryOtherAccountsList(rp);
			List<OtherAccountDTO> li = result.getDataList();
			if(li != null && li.size()> 0){
				account = li.get(0);
				account.setUsername(AES256.decrypt(account.getUsername()));
				log.info(account);
				request.setAttribute(ConstantSettings.KEY_RESULT, account);
			} else {
				message = MessageUtils.getMessage(ConstantSettings.LABEL_SYSTEM_ERROR);
			}
		}catch(ServiceException e) {
			log.error("gotoEdit", e);
			message = MessageUtils.getMessage(ConstantSettings.LABEL_SYSTEM_ERROR);
		}
		
		request.setAttribute(ConstantSettings.KEY_MESSAGE, message);
		return "otheraccounts/edit";
	}
	
	@RequestMapping(value="/otheraccounts/update", method=RequestMethod.POST)
	public String saveUpdate(HttpServletRequest request) throws Exception {
		String oldPassword = request.getParameter("old-password");
		OtherAccountDTO otherAccountsDTO = (OtherAccountDTO)encapsulateSubmitDTO(request, OtherAccountDTO.class);
		otherAccountsDTO.setUsername(AES256.encrypt(otherAccountsDTO.getUsername()));
		String newPassword = otherAccountsDTO.getPassword();
		if("".equals(newPassword)) {
			 otherAccountsDTO.setPassword(oldPassword);
		} else {
		    otherAccountsDTO.setPassword(AES256.encrypt(newPassword));
		}
		UserDTO user = (UserDTO)request.getSession(false).getAttribute(ConstantSettings.LOGIN_USER);
		String username = user.getUserName();
		RequestParam rp = new RequestParam();
		rp.setRequestObject(otherAccountsDTO);
		rp.setLoginUserName(username);
		PaginatedDTO<OtherAccountDTO> result = null;
		String message = null;
		try{
			otherAccountService.saveUpdate(rp);
			rp = getPreRequestParam(request);
			saveRequestParam(request, rp);
			log.info("----------------rp:"+rp);
			result = otherAccountService.queryOtherAccountsList(rp);
			List<OtherAccountDTO> accountList = result.getDataList();
			for(OtherAccountDTO account : accountList) {
				account.setUsername(AES256.decrypt(account.getUsername()));
				account.setPassword(AES256.decrypt(account.getPassword()));
			}
			result.setPageNo(rp.getPageNo());
			request.setAttribute(ConstantSettings.KEY_RESULT, result);
			request.setAttribute(ConstantSettings.KEY_PARAM, rp);
		}catch(ServiceException e) {
			log.error("saveUpdate", e);
			message = MessageUtils.getMessage(ConstantSettings.LABEL_SYSTEM_ERROR);
			throw new Exception(e);
		}
		return "otheraccounts/accounts";
	}
	
	
	@RequestMapping(value="/otheraccounts/indeciate", method=RequestMethod.POST, produces = {"application/plain;charset=UTF-8"})
	@ResponseBody
	public String indeciate(HttpServletRequest request) throws Exception {
		UserDTO user = (UserDTO)request.getSession(false).getAttribute(ConstantSettings.LOGIN_USER);
		String accountId = request.getParameter("account_id");
		String username = user.getUserName();
		String password = request.getParameter("password");
		String _password = user.getPassWord();
		RequestParam rp = new RequestParam();
		OtherAccountDTO oat = new OtherAccountDTO();
		oat.setAccountId(accountId);
		oat.setAccountOwner(username);
		rp.setRequestObject(oat);
		rp.setPageNo(1);
		try{
			if(StringUtils.isNotEmpty(password) && _password.equals(AES256.encrypt(password))) {
				PaginatedDTO<OtherAccountDTO> result = otherAccountService.queryOtherAccountsList(rp);
				List<OtherAccountDTO> list = result.getDataList();
				if(list.size() <= 0) {
					return("参数错误");
				} else {
					oat = list.get(0);
					String accountPassKey = oat.getPassword();
					return AES256.decrypt(accountPassKey);
				}
				 
			} else {
				return("口令不正确");
			}
		}catch(ServiceException e) {
			log.error("saveAdd", e);
			throw new Exception(e);
		}
	}
	
}
