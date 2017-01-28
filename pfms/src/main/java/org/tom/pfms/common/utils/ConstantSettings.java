package org.tom.pfms.common.utils;

public class ConstantSettings {
    public static final String LOGIN_RESULT = "LOGIN_RESULT";
    public static final String LOGIN_USER = "LOGIN_USER";
    public static final String KEY_MESSAGE = "KEY_MESSAGE";
    public static final String KEY_RESULT = "KEY_RESULT";
    public static final String KEY_PARAM = "KEY_PARAM";
    
    public static final String LOGIN_SUCCESS = "1";
    public static final String LOGIN_INVALID_USER = "-1";
    public static final String LOGIN_ERROR_PASSWORD = "0";
    
    public static final String LABEL_LOGIN_SUCCESS = "LOGIN_SUCCESS";
    public static final String LABEL_LOGIN_INVALID_USER = "LOGIN_INVALID_USER";
    public static final String LABEL_LOGIN_ERROR_PASSWORD = "LOGIN_ERROR_PASSWORD";
    public static final String LABEL_SYSTEM_ERROR = "SYSTEM_ERROR";
    public static final String LABEL_BAN_REDO_WARNING = "BAN_REDO_WARNING";
    public static final String LABEL_NEWPASSWORD_NOTEMPTY = "NEWPASSWORD_NOTEMPTY";
    public static final String LABEL_REPEATNEWPASSWORD_NOTEMPTY = "REPEAT_NEWPASSWORD_NOTEMPTY";
    public static final String LABEL_NEWPASSWORD_NOTEMATCH = "NEWPASSWORD_NOTEMATCH";
    public static final String LABEL_PASSWORD_INCORRECT = "PASSWORD_INCORRECT";
    
    public static final String ACTION_BACK = "back";
    public static final String KEY_TOKEN = "KEY_TOKEN";
    
    public static final int PAGE_SIZE = 10;
    
    //SQL-ID
    public class SQL_ID {
    	public class OtherAccountsDao {
    		public static final String queryOtherAccountsList = "org.tom.pfms.dao.OtherAccountsDao.queryOtherAccountsList";
    		public static final String queryOtherAccountsCount = "org.tom.pfms.dao.OtherAccountsDao.queryOtherAccountsCount";
    		public static final String delete = "org.tom.pfms.dao.OtherAccountsDao.delete";
    		public static final String save = "org.tom.pfms.dao.OtherAccountsDao.save";
    		public static final String update = "org.tom.pfms.dao.OtherAccountsDao.update";
    	}
    	
    	public class UserDao {
    		public static final String queryUser = "org.tom.pfms.dao.SysUserDao.queryUser";
    		public static final String editUser = "org.tom.pfms.dao.SysUserDao.editUser";
    	}
    	
    	public class BankAccountDao {
    		public static final String queryCreditList = "org.tom.pfms.dao.BankAccountDao.queryCreditList";
    		public static final String queryCreditCount = "org.tom.pfms.dao.BankAccountDao.queryCreditCount";
    		public static final String save = "org.tom.pfms.dao.BankAccountDao.saveLoan";
    		public static final String update = "org.tom.pfms.dao.BankAccountDao.updateLoan";
    		public static final String queryBanks = "org.tom.pfms.dao.BankAccountDao.queryBanks";
    	}
    	
    	public class TaskJobDao {
    		public static final String queryTask = "org.tom.pfms.dao.TaskJobDao.queryTask";
    		public static final String updateTask = "org.tom.pfms.dao.TaskJobDao.updateTask";
    		public static final String execParseMailProc = "org.tom.pfms.dao.TaskJobDao.execParseMailProc";
    	}
    	
    	public class CreditMailDao {
    		public static final String save = "org.tom.pfms.dao.CreditMailDao.save";
    		public static final String query = "org.tom.pfms.dao.CreditMailDao.query";
    	}
    	
    	public class CreditBillDao{
    		public static final String queryCreditBill = "org.tom.pfms.dao.CreditBillDao.queryCreditBill";
    		public static final String queryCreditBillCount = "org.tom.pfms.dao.CreditBillDao.queryCreditBillCount";    		
    		public static final String updateIsClear = "org.tom.pfms.dao.CreditBillDao.updateIsClear";    		
    		public static final String queryCreditBillDetail = "org.tom.pfms.dao.CreditBillDao.queryCreditBillDetail";
    	}
    	
    	public class DebitCardDao {
    		public static final String queryDebitCards = "org.tom.pfms.dao.DebitCardDao.queryDebitCards";
    		public static final String queryDebitCount = "org.tom.pfms.dao.DebitCardDao.queryDebitCount";
    		//saveDebit
    		public static final String saveDebit = "org.tom.pfms.dao.DebitCardDao.saveDebit";
    		//updateDebit
    		public static final String updateDebit = "org.tom.pfms.dao.DebitCardDao.updateDebit";
    		//invalidDebit
    		public static final String invalidDebit = "org.tom.pfms.dao.DebitCardDao.invalidDebit";
    		
    		public static final String queryDebitSummary = "org.tom.pfms.dao.DebitCardDao.queryDebitSummary";
    	}
    }
}
