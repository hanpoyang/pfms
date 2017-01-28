package org.tom.pfms.dao;

import java.util.List;

import org.tom.pfms.common.dto.BankLoanDTO;
import org.tom.pfms.common.dto.KeyValuePair;
import org.tom.pfms.common.dto.PaginatedDTO;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.exception.DaoException;

public interface BankAccountDao {
    
	/**
	 * ��ѯ
	 * @param rp
	 * @return
	 * @throws DaoException
	 */
	public PaginatedDTO<BankLoanDTO> queryCreditList(RequestParam rp) throws DaoException;
	
	/**
	 * ɾ��
	 * @param rp
	 * @throws DaoException
	 */
	public void inValidLoan(RequestParam rp) throws DaoException;
	
	/**
	 * ɾ��
	 * @param rp
	 * @throws DaoException
	 */
	public void saveLoan(RequestParam rp) throws DaoException;
	
	/**
	 * ��ȡ�����б�
	 */
	public List<KeyValuePair> queryBanks() throws DaoException;
}
