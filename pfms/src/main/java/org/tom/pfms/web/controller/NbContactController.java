package org.tom.pfms.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tom.pfms.common.dto.NbContactDTO;
import org.tom.pfms.common.dto.PaginatedDTO;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.dto.UserDTO;
import org.tom.pfms.common.exception.ServiceException;
import org.tom.pfms.common.utils.ConstantSettings;
import org.tom.pfms.common.utils.MessageUtils;
import org.tom.pfms.service.NbContactService;

import com.google.gson.Gson;

@Controller
public class NbContactController extends BaseController {
    
	@Resource
	NbContactService nbContactService;
	
	@RequestMapping("/nb/querycontacts")
	public String queryNbContact(HttpServletRequest request) throws Exception {
		try{
			String action = request.getParameter("Action");
    		UserDTO user = (UserDTO)request.getSession(false).getAttribute(ConstantSettings.LOGIN_USER);
    		String username = user.getUserName();
    		NbContactDTO nbContactDTO = (NbContactDTO)encapsulateSubmitDTO(request, NbContactDTO.class);
    		String pageNo = request.getParameter("pageNo");
    		int iPageNo = pageNo == null ? 1 : Integer.parseInt(pageNo);
    		RequestParam rp = new RequestParam();
    		rp.setRequestObject(nbContactDTO);
    		rp.setPageNo(iPageNo);
    		rp.setLoginUserName(username);
    		
    		if(ConstantSettings.ACTION_BACK.equals(action)){
    			rp = getPreRequestParam(request);
    		}
    		saveRequestParam(request, rp);
    		PaginatedDTO<NbContactDTO> result = null;
    		String message = null;
    		try{
    			result = nbContactService.queryNbContact(rp);
    			result.setPageNo(iPageNo);
    			request.setAttribute(ConstantSettings.KEY_RESULT, result);
    		}catch(ServiceException e) {
    			log.error("queryNbContact", e);
    			message = MessageUtils.getMessage(ConstantSettings.LABEL_SYSTEM_ERROR);
    			throw new Exception(e);
    		}
    		request.setAttribute(ConstantSettings.KEY_MESSAGE, message);
    		request.setAttribute(ConstantSettings.KEY_PARAM, rp);
        	return "nb/nb_contact_list";
		} catch(Exception e) {
			log.error("queryNbContact", e);
			throw e;
		}
	}
	/**
	 * 新增表单
	 * @return
	 */
	@RequestMapping("/nb/contact_add_form")
	public String showAdd(){
		return "nb/contact_add_form";
	}
	
	/**
	 * 新增
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/nb/contact_add")
	public String add(HttpServletRequest request) throws Exception {
		try{
			String action = request.getParameter("Action");
    		UserDTO user = (UserDTO)request.getSession(false).getAttribute(ConstantSettings.LOGIN_USER);
    		String username = user.getUserName();
    		NbContactDTO nbContactDTO = (NbContactDTO)encapsulateSubmitDTO(request, NbContactDTO.class);
    		RequestParam rp = new RequestParam();
    		rp.setRequestObject(nbContactDTO);
    		rp.setLoginUserName(username);
    		saveRequestParam(request, rp);
    		if(ConstantSettings.ACTION_BACK.equals(action)){
    			rp = getPreRequestParam(request);
    		}
    		String message = null;
    		try{
    			nbContactService.insertNbContact(rp);
    			message = "保存成功";
    		}catch(ServiceException e) {
    			log.error("queryNbContact", e);
    			message = MessageUtils.getMessage(ConstantSettings.LABEL_SYSTEM_ERROR);
    			throw new Exception(e);
    		}
    		request.setAttribute(ConstantSettings.KEY_RESULT, "Y");
    		request.setAttribute(ConstantSettings.KEY_MESSAGE, message);
    		request.setAttribute(ConstantSettings.KEY_PARAM, rp);
    		request.setAttribute(ConstantSettings.KEY_URL, "/nb/querycontacts");
        	return "nb/nb_message";
		} catch(Exception e) {
			log.error("queryNbContact", e);
			request.setAttribute(ConstantSettings.KEY_MESSAGE, e.getMessage());
			request.setAttribute(ConstantSettings.KEY_RESULT, "N");
			request.setAttribute(ConstantSettings.KEY_URL, "/nb/querycontacts");
			return "nb/nb_message";
		}
	}
	
	@RequestMapping("/nb/contact_edit_form")
	public String showEdit(HttpServletRequest request){
		String action = request.getParameter("Action");
		UserDTO user = (UserDTO)request.getSession(false).getAttribute(ConstantSettings.LOGIN_USER);
		String username = user.getUserName();
		NbContactDTO nbContactDTO = (NbContactDTO)encapsulateSubmitDTO(request, NbContactDTO.class);
		RequestParam rp = new RequestParam();
		rp.setRequestObject(nbContactDTO);
		rp.setLoginUserName(username);
		saveRequestParam(request, rp);
		if(ConstantSettings.ACTION_BACK.equals(action)){
			rp = getPreRequestParam(request);
		}
		PaginatedDTO<NbContactDTO> result = null;
		String message = null;
		try{
			result = nbContactService.queryNbContact(rp);
			result.setPageNo(1);
			List<NbContactDTO> list = result.getDataList();
			NbContactDTO bean = list.get(0);
			request.setAttribute(ConstantSettings.KEY_RESULT, bean);
		}catch(ServiceException e) {
			log.error("showEdit", e);
			message = MessageUtils.getMessage(ConstantSettings.LABEL_SYSTEM_ERROR);
		}
		request.setAttribute(ConstantSettings.KEY_MESSAGE, message);
		return "nb/contact_edit_form";
	}
	
	
	/**
	 * 显示联系簿明细
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/nb/contact_text")
	public String showDetail(HttpServletRequest request){
		UserDTO user = (UserDTO)request.getSession(false).getAttribute(ConstantSettings.LOGIN_USER);
		String username = user.getUserName();
		NbContactDTO nbContactDTO = (NbContactDTO)encapsulateSubmitDTO(request, NbContactDTO.class);
		RequestParam rp = new RequestParam();
		rp.setRequestObject(nbContactDTO);
		rp.setLoginUserName(username);
		
		PaginatedDTO<NbContactDTO> result = null;
		String message = null;
		try{
			result = nbContactService.queryNbContact(rp);
			result.setPageNo(1);
			List<NbContactDTO> list = result.getDataList();
			NbContactDTO bean = list.get(0);
			request.setAttribute(ConstantSettings.KEY_RESULT, bean);
			rp = getPreRequestParam(request);
			saveRequestParam(request, rp);
		}catch(ServiceException e) {
			log.error("showDetail", e);
			message = MessageUtils.getMessage(ConstantSettings.LABEL_SYSTEM_ERROR);
		}
		request.setAttribute(ConstantSettings.KEY_MESSAGE, message);
		return "nb/contact_text";
	}
	
	
	@RequestMapping("/nb/contact_update")
	public String update(HttpServletRequest request) throws Exception {
		try{
			String action = request.getParameter("Action");
    		UserDTO user = (UserDTO)request.getSession(false).getAttribute(ConstantSettings.LOGIN_USER);
    		String username = user.getUserName();
    		NbContactDTO nbContactDTO = (NbContactDTO)encapsulateSubmitDTO(request, NbContactDTO.class);
    		RequestParam rp = new RequestParam();
    		rp.setRequestObject(nbContactDTO);
    		rp.setLoginUserName(username);
    		saveRequestParam(request, rp);
    		if(ConstantSettings.ACTION_BACK.equals(action)){
    			rp = getPreRequestParam(request);
    		}
    		String message = null;
    		try{
    			nbContactService.updateNbContact(rp);
    			message = "保存成功";
    		}catch(ServiceException e) {
    			log.error("queryNbContact", e);
    			message = MessageUtils.getMessage(ConstantSettings.LABEL_SYSTEM_ERROR);
    			throw new Exception(e);
    		}
    		request.setAttribute(ConstantSettings.KEY_MESSAGE, message);
    		request.setAttribute(ConstantSettings.KEY_PARAM, rp);
    		request.setAttribute(ConstantSettings.KEY_RESULT, "Y");
    		request.setAttribute(ConstantSettings.KEY_URL, "/nb/querycontacts");
    		return "nb/nb_message";
		} catch(Exception e) {
			log.error("queryNbContact", e);
			request.setAttribute(ConstantSettings.KEY_MESSAGE, e.getMessage());
			request.setAttribute(ConstantSettings.KEY_RESULT, "N");
			request.setAttribute(ConstantSettings.KEY_URL, "/nb/querycontacts");
			return "nb/nb_message";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/rest/nb/querycontacts", produces = {"application/json;charset=UTF-8"})
	public String queryNbContactRest(HttpServletRequest request) throws Exception {
		String responseBody = "";
		String message = null;
		Map<String, Object> responseMap = new HashMap<String, Object>();
		try{
			String action = request.getParameter("Action");
    		UserDTO user = (UserDTO)request.getSession(false).getAttribute(ConstantSettings.LOGIN_USER);
    		String username = user.getUserName();
    		NbContactDTO nbContactDTO = (NbContactDTO)encapsulateSubmitDTO(request, NbContactDTO.class);
    		String pageNo = request.getParameter("pageNo");
    		int iPageNo = pageNo == null ? 1 : Integer.parseInt(pageNo);
    		RequestParam rp = new RequestParam();
    		rp.setRequestObject(nbContactDTO);
    		rp.setPageNo(iPageNo);
    		rp.setLoginUserName(username);
    		saveRequestParam(request, rp);
    		if(ConstantSettings.ACTION_BACK.equals(action)){
    			rp = getPreRequestParam(request);
    		}
    		PaginatedDTO<NbContactDTO> result = null;
    		
    		try{
    			result = nbContactService.queryNbContact(rp);
    			result.setPageNo(iPageNo);
    			responseMap.put("flag", "success");
    			responseMap.put("message", "成功");
    			responseMap.put("data", result);
    		}catch(ServiceException e) {
    			log.error("queryNbContact", e);
    			message = MessageUtils.getMessage(ConstantSettings.LABEL_SYSTEM_ERROR);
    			responseMap.put("message", message);
    		}
		} catch(Exception e) {
			log.error("queryNbContact", e);
			message = MessageUtils.getMessage(ConstantSettings.LABEL_SYSTEM_ERROR);
			responseMap.put("message", message);
		}
		Gson gson = new Gson();
		responseBody = gson.toJson(responseMap);
		return responseBody;
	}
	
	@RequestMapping(value="/nb/contact_invalid", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String invalidContact(HttpServletRequest request){
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String action = request.getParameter("Action");
		NbContactDTO nbContactDTO = (NbContactDTO)encapsulateSubmitDTO(request, NbContactDTO.class);
		nbContactDTO.setContactStatus("N");
		UserDTO user = (UserDTO)request.getSession(false).getAttribute(ConstantSettings.LOGIN_USER);
		String username = user.getUserName();
		RequestParam rp = new RequestParam();
		rp.setRequestObject(nbContactDTO);
		rp.setLoginUserName(username);
		saveRequestParam(request, rp);
		if(ConstantSettings.ACTION_BACK.equals(action)){
			rp = getPreRequestParam(request);
		}
		try{
			nbContactService.invalidNbContact(rp);
			responseMap.put("flag", 1);
			responseMap.put("message", "成功");
		}catch(ServiceException e) {
			log.error("invalidContact", e);
			responseMap.put("flag", 0);
			String message = MessageUtils.getMessage(ConstantSettings.LABEL_SYSTEM_ERROR);
			responseMap.put("message", message);
		}
		Gson gson = new Gson();
		String responseBody = gson.toJson(responseMap);
		return responseBody;
	}
	
}
