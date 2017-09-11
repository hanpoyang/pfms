package org.tom.pfms.service.impl;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.tom.pfms.common.dto.PCEquipmentDTO;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.exception.DaoException;
import org.tom.pfms.common.exception.ServiceException;
import org.tom.pfms.common.utils.AutoPowerOnPC;
import org.tom.pfms.common.utils.CommonUtils;
import org.tom.pfms.dao.PCEquipmentsDao;
import org.tom.pfms.service.BaseService;
import org.tom.pfms.service.PCEquipmentService;

@Service
public class PCEquipmentServiceImpl extends BaseService implements PCEquipmentService {
    
	@Resource
	private PCEquipmentsDao dao = null;
	
	public PCEquipmentServiceImpl(){
		String commandLine = "chmod 777 " +
		PCEquipmentServiceImpl.class.getClassLoader().getResource("/").getPath() + "ssh_expect.sh";
		CommonUtils.executeCommand(commandLine, null);
	}
	
	@Override
	public List<PCEquipmentDTO> queryPcList(RequestParam rp)
			throws ServiceException {
		try{
			List<PCEquipmentDTO> pcList = dao.queryPCEquipments(rp);
			return pcList;
		}catch(DaoException e) {
			log.error("queryPcList", e);
		}
		return null;
	}
	
	@Override
	public void wakePC(String ip, String mac) {
		PCEquipmentDTO pc = new PCEquipmentDTO();
		pc.setEquIp(ip);
		RequestParam rp = new RequestParam();
		rp.setLoginUserName("tom");
		rp.setRequestObject(pc);
		List<PCEquipmentDTO> pcList = null;
		try{
			pcList = queryPcList(rp);
		}catch(ServiceException e) {
			log.error("wakePC", e);
		}
		if(null != pc && pcList.size() > 0) {
			pc = pcList.get(0);
		}
		if("COMPUTER".equals(pc.getEquCategory())){
		    AutoPowerOnPC.wakePc(mac);
		} else if("COMPUTER_EXT".equals(pc.getEquCategory())){
			AutoPowerOnPC.wakePc();
		}
	}
	
	@Override
	public void shutdown(String userName, String ip) {
		try{
			PCEquipmentDTO pce = new PCEquipmentDTO();
			pce.setEquIp(ip);
			RequestParam rp = new RequestParam();
			rp.setLoginUserName(userName);
			rp.setRequestObject(pce);
			List<PCEquipmentDTO> pcList = dao.queryPCEquipments(rp);
			if(pcList!=null && pcList.size() > 0) {
				pce = pcList.get(0);
			}
			String osName = System.getProperty("os.name");
			String localIp = getMyIp();
			log.info("localIp:="+localIp);
			String commandLine = "";
			if(osName.startsWith("Windows")){
				commandLine = "cmd /c net use \\\\"+ip+"\\ipc$ "+pce.getSuperPassKey()+" /user:"+pce.getSuperUser();
				CommonUtils.executeCommand(commandLine, "");
				commandLine = "cmd /c shutdown /s /m \\\\"+ip+" -t 1";
				CommonUtils.executeCommand(commandLine, "");
			} else {
				if(ip.equals(localIp)) {
					commandLine = "shutdown -h now";
					CommonUtils.executeCommand(commandLine, "");
					return;
				}
				commandLine = "net rpc shutdown -I "+ip+" -U "+pce.getSuperUser()+"%"+pce.getSuperPassKey();
				String otherLine = PCEquipmentServiceImpl.class.getClassLoader().getResource("/").getPath() + "ssh_expect.sh "+pce.getSuperUser()
						+ "@"
						+ip
						+ " halt "
						+ pce.getSuperPassKey();
				CommonUtils.executeCommand(commandLine, otherLine);
			}
		}catch(Exception e){
			log.error("shutdown", e);
		}
	}
	
	@SuppressWarnings("rawtypes")
    public static String getMyIp() {
        String localip = null;// 本地IP，如果没有配置外网IP则返回它
        String netip = null;// 外网IP
        try {
            Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            boolean finded = false;// 是否找到外网IP
            while (netInterfaces.hasMoreElements() && !finded) {
                NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
                Enumeration address = ni.getInetAddresses();
                while (address.hasMoreElements()) {
                    ip = (InetAddress) address.nextElement();
                    if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 外网IP
                        netip = ip.getHostAddress();
                        finded = true;
                        break;
                    } else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 内网IP
                        localip = ip.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

        if (netip != null && !"".equals(netip)) {
            return netip;
        } else {
            return localip;
        }
    }

}
