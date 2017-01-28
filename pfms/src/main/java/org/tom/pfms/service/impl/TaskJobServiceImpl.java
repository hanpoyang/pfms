package org.tom.pfms.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tom.pfms.common.dto.TaskJobBean;
import org.tom.pfms.common.utils.MailUtil;
import org.tom.pfms.dao.CreditMailDao;
import org.tom.pfms.dao.TaskJobDao;
import org.tom.pfms.service.BaseService;

@Service
public class TaskJobServiceImpl extends BaseService {
    
	@Autowired
	private CreditMailDao creditMailDao;
	
	@Resource
	private TaskJobDao taskJobDao;
	
	public void execute(){
		boolean hasNew = false;
		try {
			TaskJobBean taskJobBean = taskJobDao.queryTask("1");
			if("0".equals(taskJobBean.getSynFlag())){
				taskJobBean.setSynFlag("1");
				taskJobDao.updateTask(taskJobBean);
				Date lastSynDate = taskJobBean.getLastSynDate();
				List<Map<String, Object>> mailList = MailUtil.getMailList();
				for(Map<String, Object> mail : mailList) {
					Date mailDate = (Date)mail.get("ReceivedDate");
					if(mailDate.getTime() > lastSynDate.getTime()) {
					    creditMailDao.save(mail);	
					    hasNew = true;
					}
				}
				taskJobBean.setSynFlag("0");
				taskJobDao.updateTask(taskJobBean);
				if(hasNew)taskJobDao.execParseMailProc();
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("saveMails", e);
		}
    }
}
