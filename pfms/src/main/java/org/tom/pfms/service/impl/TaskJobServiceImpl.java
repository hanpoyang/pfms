package org.tom.pfms.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.tom.pfms.common.dto.TaskJobBean;
import org.tom.pfms.common.exception.DaoException;
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
	
	@Value("${mailclient.username}")
	private String username;
	
	@Value("${mailclient.host}")
	private String host;
	
	@Value("${mailclient.password}")
	private String password;
	
	public void execute(){
		boolean hasNew = false;
		TaskJobBean taskJobBean = new TaskJobBean();
		try {
			taskJobBean = taskJobDao.queryTask("1");
			if("0".equals(taskJobBean.getSynFlag())){
				taskJobBean.setSynFlag("1");
				taskJobDao.updateTask(taskJobBean);
				Date lastSynDate = taskJobBean.getLastSynDate();
				List<Map<String, Object>> mailList = MailUtil.getMailList(host, username, password);
				for(Map<String, Object> mail : mailList) {
					Date mailDate = (Date)mail.get("ReceivedDate");
					if(mailDate.getTime() > lastSynDate.getTime()) {
					    creditMailDao.save(mail);	
					    hasNew = true;
					}
				}
				if(hasNew)taskJobDao.execParseMailProc();
				taskJobBean.setSynFlag("0");
				taskJobBean.setLastSynDate(Calendar.getInstance().getTime());
				taskJobDao.updateTask(taskJobBean);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("saveMails", e);
			try {
				taskJobBean.setSynFlag("0");
			    taskJobDao.updateTask(taskJobBean);
			} catch(DaoException ex) {
				log.error("saveMails", ex);
			}
		}
    }
}
