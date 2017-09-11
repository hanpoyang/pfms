package org.tom.pfms.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tom.pfms.common.dto.DebitDetailDTO;
import org.tom.pfms.common.dto.PaginatedDTO;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.dto.UserDTO;
import org.tom.pfms.common.exception.ServiceException;
import org.tom.pfms.common.utils.ConstantSettings;
import org.tom.pfms.common.utils.MessageUtils;
import org.tom.pfms.service.DebitDetailService;

import com.google.gson.Gson;

@Controller
public class DebitDetailController extends BaseController {
	
	@Resource
	DebitDetailService debitDetailService;
	
    @RequestMapping("/bank/debitdetails")
	public String queryDebitDetails(HttpServletRequest request) 
		throws Exception {
    	try{
    		String action = request.getParameter("Action");
    		UserDTO user = (UserDTO)request.getSession(false).getAttribute(ConstantSettings.LOGIN_USER);
    		String username = user.getUserName();
    		DebitDetailDTO debitDetailDTO = (DebitDetailDTO)encapsulateSubmitDTO(request, DebitDetailDTO.class);
    		String pageNo = request.getParameter("pageNo");
    		int iPageNo = pageNo == null ? 1 : Integer.parseInt(pageNo);
    		RequestParam rp = new RequestParam();
    		rp.setRequestObject(debitDetailDTO);
    		rp.setPageNo(iPageNo);
    		rp.setLoginUserName(username);
    		saveRequestParam(request, rp);
    		if(ConstantSettings.ACTION_BACK.equals(action)){
    			rp = getPreRequestParam(request);
    		}
    		PaginatedDTO<DebitDetailDTO> result = null;
    		String message = null;
    		try{
    			result = debitDetailService.queryDebitDetails(rp);
    			result.setPageNo(iPageNo);
    			request.setAttribute(ConstantSettings.KEY_RESULT, result);
    		}catch(ServiceException e) {
    			log.error("showList", e);
    			message = MessageUtils.getMessage(ConstantSettings.LABEL_SYSTEM_ERROR);
    			throw new Exception(e);
    		}
    		request.setAttribute(ConstantSettings.KEY_MESSAGE, message);
    		request.setAttribute(ConstantSettings.KEY_PARAM, rp);
        	return "bank/debit_detail_list";
    	} catch(Exception e) {
    		log.error("queryDebitDetails", e);
    		throw e;
    	}
    }
    
    @RequestMapping("/bank/debitimport")
   	public String showImport(HttpServletRequest request) 
   		throws Exception {
    	return "bank/debit_detail_import";
    }
    
    @RequestMapping("/bank/dodebitimport")
	public String doImport(HttpServletRequest request) 
   		throws Exception {
    	String message = "";
    	UserDTO user = (UserDTO)request.getSession(false).getAttribute(ConstantSettings.LOGIN_USER);
		String username = user.getUserName();
    	try{
    		HttpSession session = request.getSession(false);
    		String sessionId = session.getId();
			debitDetailService.importDebitDetails(username, sessionId);
			message = "Ã÷Ï¸µ¼Èë";
		}catch(ServiceException e) {
			log.error("showList", e);
			message = MessageUtils.getMessage(ConstantSettings.LABEL_SYSTEM_ERROR);
			throw new Exception(e);
		}
    	request.setAttribute(ConstantSettings.KEY_MESSAGE, message);
    	return "bank/debit_detail_success";
    }
    
    @RequestMapping(value="/bank/debit_in_out", method=RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String queryInOut(HttpServletRequest request)  {
    	String message = "";
    	UserDTO user = (UserDTO)request.getSession(false).getAttribute(ConstantSettings.LOGIN_USER);
		String username = user.getUserName();
		RequestParam rp = new RequestParam();
		Map<String, String> rMap = new HashMap<String, String>();
		String json = "";
		Gson gson = new Gson();
		rp.setLoginUserName(username);
		rp.setRequestObject(rMap);
    	try{
			Object[] result = debitDetailService.getInAndOut(rp);
			json = gson.toJson(result);
			json = "{\"flag\":\"1\", \"message\":\"\",\"data\":"+json+"}";
		}catch(ServiceException e) {
			log.error("queryInOut", e);
			message = MessageUtils.getMessage(ConstantSettings.LABEL_SYSTEM_ERROR);
			json = "{\"flag\":\"0\", \"message\":\""+message+"\"}";
		}
    	return json;
    }
    
    @RequestMapping("/bank/debit_detail_curves")
    public String showDetailCurves(){
    	return "bank/debit_inout_curv";
    }
}
