package org.tom.pfms.common.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.tom.pfms.common.dto.PCEquipmentDTO;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.exception.ServiceException;
import org.tom.pfms.service.ApplicationContextHelper;
import org.tom.pfms.service.PCEquipmentService;

public class CommonUtils {
	public static Map<String, String> pc_status = new HashMap<String, String>();
	public static Map<String, String> status_queue = new HashMap<String, String>();
	private static Logger log = Logger.getLogger(CommonUtils.class);
	private static boolean isRunning = false;
	
	public static String temporaryCache = "";
	
    public static String getUUID() {
    	UUID uuid = UUID.randomUUID();
    	return uuid.toString();
    	
    }
    
    public static void startReadPCStatus() {
    	if(isRunning)return;
    	PCEquipmentService pcService = (PCEquipmentService)ApplicationContextHelper.getBean("PCEquipmentServiceImpl");
    	RequestParam rp = new RequestParam();
    	rp.setLoginUserName("tom");
    	isRunning = true;
    	new Thread(new Runnable(){
			public void run() {
				
				List<PCEquipmentDTO> pcList = null;
				try {
					pcList = pcService.queryPcList(rp);
				} catch (ServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    loadPowerStatus(pcList);
			}
		}).start();
    }
    
    public static void loadPowerStatus(List<PCEquipmentDTO> pcList) {
		try{
			for(int i = 0; i < pcList.size(); i++) {
				final String ip = pcList.get(i).getEquIp();
				new Thread(new Runnable(){
					public void run(){
						while(true) {
							try {
								readPcStatusByIp(ip);
								Thread.sleep(500);
							} catch (Exception e) {
								log.error("loadPowerStatus", e);
							}
							
						}
					}
				}).start();;
			}
		}catch(Exception e) {
			log.error("loadPowerStatus", e);
		}
	}
		
	private static void readPcStatusByIp(String ip) throws Exception {
		Runtime rt = Runtime.getRuntime();
		String osName = System.getProperty("os.name");
		String commandLine = osName.startsWith("Windows") ? "cmd /c ping "+ip+" -n 1" : "ping -c 1 "+ip;
	    Process p = rt.exec(commandLine);
	    StringBuffer buf = new StringBuffer();
		InputStream is=p.getInputStream();
	    InputStreamReader isr=new InputStreamReader(is);
	    BufferedReader reader=new BufferedReader(isr);
	    String msg="";
	    while((msg=reader.readLine())!=null){
	    	buf.append(msg);
	    }   
	    reader.close();
	    p.waitFor();
	    p.destroy();
	    boolean result = !(buf.indexOf("Destination Host Unreachable") > -1 || buf.indexOf("无法访问目标主机") > -1 || buf.indexOf("请求超时") > -1 || buf.indexOf("0 received") > -1);
//	    log.info("result:::=="+buf+"=="+result);
	    String status = result ? "on" : "off";
	    String status2 = pc_status.get(ip);
	    if(!status.equals(status2)) {
	        status_queue.put(ip, result ? "on" : "off");
	        pc_status.put(ip, status);
	    }
	}
	
	public static void executeCommand(String line, String secLine) {
		StringBuffer buf = new StringBuffer();
		Runtime rt = Runtime.getRuntime();
		InputStream is=null;
	    InputStreamReader isr=null;
	    BufferedReader reader=null;
	    Process p = null;
		try{
			p = rt.exec(line);
			is=p.getInputStream();
		    isr=new InputStreamReader(is);
		    reader=new BufferedReader(isr);
		    String msg="";
		    while((msg=reader.readLine())!=null){
		    	buf.append(msg);
		    }   
		    reader.close();
		    p.waitFor();
		    p.destroy();
		    log.info("-----------------------------------------------------");
		    log.info("command := " + line);
            log.info("command := " + buf.toString());
		    log.info("-----------------------------------------------------");
		    if(null != secLine && secLine.length() > 0){
		    	if(buf.length() == 0) {
		    		p = rt.exec(secLine);
					is=p.getInputStream();
				    isr=new InputStreamReader(is);
				    reader=new BufferedReader(isr);
				    msg="";
				    while((msg=reader.readLine())!=null){
				    	buf.append(msg);
				    }   
				    reader.close();
				    p.waitFor();
				    p.destroy();
				    log.info("-----------------------------------------------------");
				    log.info("command2 := " + secLine);
		            log.info("command2 := " + buf.toString());
				    log.info("-----------------------------------------------------");
		    	}
		    }
		} catch(Exception e) {
			log.error("executeCommand", e);
		}finally {
			try {
				reader.close();
				p.waitFor();
			    p.destroy();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		}
	}
    
}
