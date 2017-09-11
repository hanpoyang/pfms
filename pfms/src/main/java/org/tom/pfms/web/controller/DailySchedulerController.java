package org.tom.pfms.web.controller;

import java.util.Calendar;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tom.pfms.common.dto.DailySchedulerDTO;
import org.tom.pfms.common.dto.PaginatedDTO;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.dto.UserDTO;
import org.tom.pfms.common.exception.ServiceException;
import org.tom.pfms.common.utils.ConstantSettings;
import org.tom.pfms.common.utils.MessageUtils;
import org.tom.pfms.service.DailySchedulerService;

@Controller
public class DailySchedulerController extends BaseController {

	@Resource
	DailySchedulerService dailySchedulerService = null;
	
	@RequestMapping("/daily/queryschedulers")
	public String queryDailySchedulers(HttpServletRequest request)
	    throws Exception {
		String action = request.getParameter("Action");
		String year = request.getParameter("y");
		String month = request.getParameter("m");
		if(year == null) {
			year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		}
		
		if(month == null) {
			month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
		}
		UserDTO user = (UserDTO)request.getSession(false).getAttribute(ConstantSettings.LOGIN_USER);
		String username = user.getUserName();
		DailySchedulerDTO dailySchedulerDTO = (DailySchedulerDTO)encapsulateSubmitDTO(request, DailySchedulerDTO.class);
		dailySchedulerDTO.setSchedulerOwner(username);
		dailySchedulerDTO.setSchedulerYear(year);
		dailySchedulerDTO.setSchedulerMonth(month);
		String pageNo = request.getParameter("pageNo");
		int iPageNo = pageNo == null ? 1 : Integer.parseInt(pageNo);
		RequestParam rp = new RequestParam();
		rp.setLoginUserName(username);
		rp.setRequestObject(dailySchedulerDTO);
		rp.setPageNo(iPageNo);
		saveRequestParam(request, rp);
		if(ConstantSettings.ACTION_BACK.equals(action)){
			rp = getPreRequestParam(request);
		}
		PaginatedDTO<DailySchedulerDTO> result = null;
		String message = null;
		try{
			result = dailySchedulerService.queryDailySchedulers(rp);
			result.setPageNo(iPageNo);
			request.setAttribute(ConstantSettings.KEY_RESULT, result);
		}catch(ServiceException e) {
			log.error("queryDailySchedulers", e);
			message = MessageUtils.getMessage(ConstantSettings.LABEL_SYSTEM_ERROR);
			throw new Exception(e);
		}
		request.setAttribute(ConstantSettings.KEY_MESSAGE, message);
		request.setAttribute(ConstantSettings.KEY_PARAM, rp);
    	return "daily/daily_scheduler_list";
	}
	
	@RequestMapping("daily/showscheduleradd")
	public String showAddForm(){
		return "daily/daily_scheduler_add";
	}
	
	@RequestMapping(value="/daily/savescheduler", method=RequestMethod.POST)
	public String doAddScheduler(HttpServletRequest request) 
	    throws Exception {
		String message = "";
		UserDTO user = (UserDTO)request.getSession(false).getAttribute(ConstantSettings.LOGIN_USER);
		String username = user.getUserName();
		DailySchedulerDTO dailySchedulerDTO = (DailySchedulerDTO)encapsulateSubmitDTO(request, DailySchedulerDTO.class);
		dailySchedulerDTO.setSchedulerOwner(username);
		float duration = Float.parseFloat(dailySchedulerDTO.getSchedulerDuration());
		duration *= 60;
		dailySchedulerDTO.setSchedulerDuration(String.valueOf(duration));
		dailySchedulerDTO.setSchedulerStatus("Y");
		RequestParam rp = new RequestParam();
		rp.setLoginUserName(username);
		rp.setRequestObject(dailySchedulerDTO);
		request.setAttribute(ConstantSettings.KEY_PARAM, rp);
		try{
			dailySchedulerService.saveDailyScheduler(rp);
			message = "±£´æ³É¹¦";
			request.setAttribute(ConstantSettings.KEY_RESULT, "Y");
		}catch(ServiceException e) {
			log.error("doAddScheduler", e);
			request.setAttribute(ConstantSettings.KEY_RESULT, "N");
			request.setAttribute(ConstantSettings.KEY_URL, "/daily/showscheduleradd");
			message = MessageUtils.getMessage(ConstantSettings.LABEL_SYSTEM_ERROR);
		}
		request.setAttribute(ConstantSettings.KEY_MESSAGE, message);
		request.setAttribute(ConstantSettings.KEY_URL, "/daily/queryschedulers");
		return "daily/daily_common_message";
	}
	
	@RequestMapping("/daily/schedulerdetail")
	public String queryTaskDetail(HttpServletRequest request)
	    throws Exception {
		String action = request.getParameter("Action");
		UserDTO user = (UserDTO)request.getSession(false).getAttribute(ConstantSettings.LOGIN_USER);
		String username = user.getUserName();
		DailySchedulerDTO dailySchedulerDTO = (DailySchedulerDTO)encapsulateSubmitDTO(request, DailySchedulerDTO.class);
		dailySchedulerDTO.setSchedulerOwner(username);
		RequestParam rp = new RequestParam();
		rp.setLoginUserName(username);
		rp.setRequestObject(dailySchedulerDTO);
		rp.setPageNo(1);
		saveRequestParam(request, rp);
		if(ConstantSettings.ACTION_BACK.equals(action)){
			rp = getPreRequestParam(request);
		}
		PaginatedDTO<DailySchedulerDTO> result = null;
		String message = null;
		try{
			result = dailySchedulerService.queryDailySchedulers(rp);
			dailySchedulerDTO = result.getDataList().get(0);
			request.setAttribute(ConstantSettings.KEY_RESULT, dailySchedulerDTO);
		}catch(ServiceException e) {
			log.error("queryTaskDetail", e);
			message = MessageUtils.getMessage(ConstantSettings.LABEL_SYSTEM_ERROR);
			throw new Exception(e);
		}
		request.setAttribute(ConstantSettings.KEY_MESSAGE, message);
		request.setAttribute(ConstantSettings.KEY_PARAM, rp);
    	return "daily/daily_scheduler_detail";
	}
}
