package org.tom.pfms.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.tom.pfms.common.dto.DebitCardDetailCurvesDTO;
import org.tom.pfms.common.dto.DebitDetailDTO;
import org.tom.pfms.common.dto.DebitDetailImportDTO;
import org.tom.pfms.common.dto.PaginatedDTO;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.exception.DaoException;
import org.tom.pfms.common.exception.ServiceException;
import org.tom.pfms.dao.DebitDetailDao;
import org.tom.pfms.service.BaseService;
import org.tom.pfms.service.DebitDetailService;

@Service
public class DebitDetailServiceImpl extends BaseService implements DebitDetailService {
    
	@Resource
	DebitDetailDao debitDetailDao;
	
	@Value("${upload.temp.dir}")
	String tempDir = null;
	
	@Override
	public PaginatedDTO<DebitDetailDTO> queryDebitDetails(RequestParam rp)
			throws ServiceException {
		try{
			return debitDetailDao.queryDebitDetails(rp);
		}catch(DaoException ex) {
			log.error("queryDebitDetails", ex);
			throw new ServiceException(ex);
		}
	}
	
	@Override
	public List<DebitCardDetailCurvesDTO> queryIncomes(RequestParam rp)
			throws ServiceException {
		try{
			List<DebitCardDetailCurvesDTO> resultList = debitDetailDao.queryIncomes(rp);
			return resultList;
		}catch(DaoException ex) {
			log.error("queryIncomes", ex);
			throw new ServiceException(ex);
		}
	}

	@Override
	public List<DebitCardDetailCurvesDTO> queryOutcomes(RequestParam rp)
			throws ServiceException {
		try{
			List<DebitCardDetailCurvesDTO> resultList = debitDetailDao.queryOutcomes(rp);
			return resultList;
		}catch(DaoException ex) {
			log.error("queryOutcomes", ex);
			throw new ServiceException(ex);
		}
	}
	
	@Override
	public Object[] getInAndOut(RequestParam rp)
		throws ServiceException {
		Object[] result = new Object[]{queryOutcomes(rp), queryIncomes(rp)};
		return result;
	}
	
	
	@Override
	public void importDebitDetails(String username, String fileName)
			throws ServiceException {
		try{
			Map<String, String> map = loadFile(fileName);
			String bankCode = map.get("bankCode");
			if("CMB".equals(bankCode)) {
				RequestParam rp = parseCmb(username, map);
				debitDetailDao.importDebitDetails(rp);
			}else if("PINGAN".equals(bankCode)) {
				RequestParam rp = parsePingAn(username, map);
				debitDetailDao.importDebitDetails(rp);
			}else if("ABC".equals(bankCode)) {
				RequestParam rp = parseAbc(username, map);
				debitDetailDao.importDebitDetails(rp);
			}
		}catch(DaoException ex) {
			log.error("importDebitDetails", ex);
			throw new ServiceException(ex);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private Map<String, String> loadFile(String fileName) {
    	ObjectInputStream oin = null;
    	InputStream in = null;
    	BufferedReader reader = null;
    	Map<String, String> resultMap = null;
    	File curFile = new File(tempDir + File.separator + fileName);
    	try{
    		in = new FileInputStream(curFile);
    		oin = new ObjectInputStream(in);
    		Object tmpObject = oin.readObject();
    		if(null != tmpObject) resultMap = (Map<String, String>)tmpObject;
    		return resultMap;
    	}catch(Exception e) {
    		log.error("loadFile", e);
    	} finally {
    		try{
    			if(null != reader) reader.close();
    			if(null != oin) oin.close();
    			if(null != in) in.close();
    			if(curFile.exists()) {
        			curFile.deleteOnExit();
        		}
    		}catch(IOException e) {
    			log.error("loadFile", e);
    		}
    	}
    	return null;
    }
	
	private RequestParam parseCmb(String loginUserName, Map<String, String> map){
		RequestParam rp = new RequestParam();
		String data = map.get("DetailData");
		data = data.replaceAll("\"", "");
		List<DebitDetailImportDTO> list = new ArrayList<DebitDetailImportDTO>();
		String[] lines = data.split("\\r\\n");
		for(int i = 1; i < lines.length; i++) {
			DebitDetailImportDTO importDTO = new DebitDetailImportDTO();
			String line = lines[i];
			String[] cells = line.split(",");
			importDTO.setDebitCardId(map.get("debitCardId"));
			importDTO.setPurchaseDate(cells[0]);
			importDTO.setPurchaseTime(cells[1]);
			importDTO.setIncome(cells[2]);
			importDTO.setOutcome(cells[3]);
			importDTO.setPurchaseType(cells[5]);
			importDTO.setBalance(cells[4]);
			importDTO.setPurchaseRemark(cells[6]);
			importDTO.setCurrencyType("CNY");
			list.add(importDTO);
		}
		rp.setRequestObject(list);
		rp.setLoginUserName(loginUserName);
		return rp;
	}
	
	private RequestParam parsePingAn(String loginUserName, Map<String, String> map){
		RequestParam rp = new RequestParam();
		String data = map.get("DetailData");
		data = data.replaceAll("\"", "");
		List<DebitDetailImportDTO> list = new ArrayList<DebitDetailImportDTO>();
		String[] lines = data.split("\\r\\n");
		for(int i = 1; i < lines.length; i++) {
			DebitDetailImportDTO importDTO = new DebitDetailImportDTO();
			String line = lines[i];
			String[] cells = line.split(",");
			String purchaseDate = cells[0];
			Date tmpDate = null;
			try {
				tmpDate = new SimpleDateFormat("yyyy-MM-dd").parse(purchaseDate);
				purchaseDate = new SimpleDateFormat("yyyyMMdd").format(tmpDate);
			} catch (ParseException e) {
				log.error("parseCmb", e);
			}
			importDTO.setDebitCardId(map.get("debitCardId"));
			importDTO.setPurchaseDate(purchaseDate);
			importDTO.setPurchaseTime("00:00:00");
			importDTO.setIncome("转入".equals(cells[3]) ? cells[4] : "");
			importDTO.setOutcome("转出".equals(cells[3]) ? cells[4] : "");
			importDTO.setPurchaseType(cells[3]);
			importDTO.setBalance(cells[5]);
			importDTO.setPurchaseRemark(cells[6]);
			importDTO.setCurrencyType("CNY");
			importDTO.setReceiveAccountCode(cells[2]);
			importDTO.setReceiveAccountName(cells[1]);
			list.add(importDTO);
		}
		rp.setRequestObject(list);
		rp.setLoginUserName(loginUserName);
		return rp;
	}
	
	private RequestParam parseAbc(String loginUserName, Map<String, String> map){
		RequestParam rp = new RequestParam();
		String data = map.get("DetailData");
		data = data.replaceAll("\"", "");
		List<DebitDetailImportDTO> list = new ArrayList<DebitDetailImportDTO>();
		String[] lines = data.split("\\r\\n");
		for(int i = 1; i < lines.length; i++) {
			DebitDetailImportDTO importDTO = new DebitDetailImportDTO();
			String line = lines[i];
			String[] cells = line.split(",");
			String purchaseTime = cells[1];
			Date tmpDate = null;
			try {
				tmpDate = new SimpleDateFormat("HHmmss").parse(purchaseTime);
				purchaseTime = new SimpleDateFormat("HH:mm:ss").format(tmpDate);
			} catch (ParseException e) {
				log.error("parseCmb", e);
			}
			importDTO.setDebitCardId(map.get("debitCardId"));
			importDTO.setPurchaseDate(cells[0]);
			importDTO.setPurchaseTime(purchaseTime);
			importDTO.setIncome(cells[2]);
			importDTO.setOutcome(cells[3]);
			importDTO.setPurchaseType(cells[8]);
			importDTO.setBalance(cells[4]);
			importDTO.setPurchaseRemark(cells[12]);
			importDTO.setCurrencyType("CNY");
			importDTO.setReceiveAccountCode(cells[5]);
			importDTO.setReceiveAccountName(cells[6]);
			importDTO.setReceiveBankName(cells[7]);
			list.add(importDTO);
		}
		rp.setRequestObject(list);
		rp.setLoginUserName(loginUserName);
		return rp;
	}

	
}
