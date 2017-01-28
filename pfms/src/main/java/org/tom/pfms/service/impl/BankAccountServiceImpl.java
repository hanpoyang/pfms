package org.tom.pfms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.tom.pfms.common.dto.BankLoanDTO;
import org.tom.pfms.common.dto.KeyValuePair;
import org.tom.pfms.common.dto.PaginatedDTO;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.exception.DaoException;
import org.tom.pfms.common.exception.ServiceException;
import org.tom.pfms.dao.BankAccountDao;
import org.tom.pfms.service.BankAccountService;
import org.tom.pfms.service.BaseService;

@Service
public class BankAccountServiceImpl extends BaseService implements BankAccountService{
    
	@Resource
	private BankAccountDao bankAccountDao = null;
	
	@Override
	public PaginatedDTO<BankLoanDTO> queryCreditList(RequestParam rp)
			throws ServiceException {
		try{
			return bankAccountDao.queryCreditList(rp);
		}catch(DaoException ex) {
			log.error("queryCreditList", ex);
			throw new ServiceException(ex);
		}
	}
	
	@Override
	public void inValidLoan(RequestParam rp) throws ServiceException {
		try {
			bankAccountDao.inValidLoan(rp);
		} catch (DaoException e) {
			log.error("inValid", e);
			throw new ServiceException(e);
		}
		
	}

	@Override
	public void saveLoan(RequestParam rp) throws ServiceException {
		try {
			BankLoanDTO loanDTO = (BankLoanDTO)rp.getRequestObject();
			String bankCode = loanDTO.getBankCode();
			String bankName = "";
			List<KeyValuePair> bankList = queryBanks();
			for(KeyValuePair kvp : bankList) {
				if(kvp.getKey().equals(bankCode)){
					bankName = kvp.getValue();
					break;
				}
			}
			loanDTO.setBankName(bankName);
			rp.setRequestObject(loanDTO);
			bankAccountDao.saveLoan(rp);
		} catch (DaoException e) {
			log.error("save", e);
			throw new ServiceException(e);
		}
		
	}
	
	@Override
	public List<KeyValuePair> queryBanks() throws ServiceException {
		try {
			return bankAccountDao.queryBanks();
		} catch (DaoException e) {
			log.error("queryBanks", e);
			throw new ServiceException(e);
		}
		
	}

}
