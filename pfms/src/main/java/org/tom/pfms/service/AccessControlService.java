package org.tom.pfms.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tom.pfms.common.dto.UserDTO;

public interface AccessControlService {
	
	/**
	 * ��֤�û�
	 * @param user
	 * @return 
	 *       0: ��֤�ɹ�
	 *       1: �û�������
	 *       2: ������ȷ
	 *       3: �û��ѹ���
	 */
	public Map<String, Object> validateUser(UserDTO user);

}
