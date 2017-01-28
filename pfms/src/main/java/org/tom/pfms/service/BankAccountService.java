package org.tom.pfms.service;

import java.util.List;

import org.tom.pfms.common.dto.BankLoanDTO;
import org.tom.pfms.common.dto.KeyValuePair;
import org.tom.pfms.common.dto.PaginatedDTO;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.exception.DaoException;
import org.tom.pfms.common.exception.ServiceException;

public interface BankAccountService {
	/**
	 * ��ѯ���ÿ��б�
	 * @param rp
	 * @return
	 * @throws ServiceException
	 */
	public PaginatedDTO<BankLoanDTO> queryCreditList(RequestParam rp) throws ServiceException;
	
	/**
	 * ʧЧ
	 * @param rp
	 * @throws DaoException
	 */
	public void inValidLoan(RequestParam rp) throws ServiceException;
	
	/**
	 * ����
	 * @param rp
	 * @throws DaoException
	 */
	public void saveLoan(RequestParam rp) throws ServiceException;
	
	/**
	 * ��ȡ�����б�
	 */
	public List<KeyValuePair> queryBanks() throws ServiceException;
}
