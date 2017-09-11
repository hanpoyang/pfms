package org.tom.pfms.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tom.pfms.common.dto.PCEquipmentDTO;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.dto.UserDTO;
import org.tom.pfms.common.exception.ServiceException;
import org.tom.pfms.common.utils.ConstantSettings;
import org.tom.pfms.common.utils.MessageUtils;
import org.tom.pfms.service.PCEquipmentService;

import com.google.gson.Gson;

@Controller
public class WOLController extends BaseController{
	
	@Resource
	private PCEquipmentService service = null;
	
	@ResponseBody
	@RequestMapping(value="/rest/pclist", produces = {"application/json;charset=UTF-8"})
	public String queryPcList(HttpServletRequest request) throws Exception {
		String responseBody = "";
		String message = null;
		Map<String, Object> responseMap = new HashMap<String, Object>();
		try{
    		UserDTO user = (UserDTO)request.getSession(false).getAttribute(ConstantSettings.LOGIN_USER);
    		String username = user.getUserName();
    		RequestParam rp = new RequestParam();
    		rp.setLoginUserName(username);
    		List<PCEquipmentDTO> result = null;
    		try{
    			result = service.queryPcList(rp);
    			responseMap.put("flag", "success");
    			responseMap.put("message", "成功");
    			responseMap.put("data", result);
    		}catch(ServiceException e) {
    			log.error("queryPcList", e);
    			message = MessageUtils.getMessage(ConstantSettings.LABEL_SYSTEM_ERROR);
    			responseMap.put("message", message);
    		}
		} catch(Exception e) {
			log.error("queryPcList", e);
			message = MessageUtils.getMessage(ConstantSettings.LABEL_SYSTEM_ERROR);
			responseMap.put("message", message);
		}
		Gson gson = new Gson();
		responseBody = gson.toJson(responseMap);
		return responseBody;
	}
	
	@RequestMapping("/pc/pclist")
	public String displayPCList() {
		return "equ/pc_list";
	}
	
	@RequestMapping(value="/rest/pc/poweron", produces = {"application/json; charset=utf-8"})
	@ResponseBody
	public String wakeUp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> resultMap = new HashMap<String, String>();
		String mac = request.getParameter("equMac");
		String ip = request.getParameter("equIp");
		service.wakePC(ip, mac); 
		resultMap.put("flag", "success");
		resultMap.put("message", "成功");
		Gson gson = new Gson();
		return gson.toJson(resultMap);
	}
	
	@RequestMapping(value="/rest/pc/poweroff", produces = {"application/json; charset=utf-8"})
	@ResponseBody
	public String shutdown(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> resultMap = new HashMap<String, String>();
		UserDTO user = (UserDTO)request.getSession(false).getAttribute(ConstantSettings.LOGIN_USER);
		String username = user.getUserName();
		String ip = request.getParameter("equIp");
		service.shutdown(username, ip); 
		resultMap.put("flag", "success");
		resultMap.put("message", "成功");
		Gson gson = new Gson();
		return gson.toJson(resultMap);
	}
	
}
