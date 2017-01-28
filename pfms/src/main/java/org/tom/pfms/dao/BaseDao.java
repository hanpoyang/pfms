package org.tom.pfms.dao;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDao {
    protected Logger log = null;
    
    @Autowired
    protected SqlSessionFactoryBean sessionFactory = null;
    
    @Autowired
    protected SqlSessionTemplate sTemplate = null;
    
    public BaseDao() {
    	log = Logger.getLogger(this.getClass());
    }
    
    protected String getSql(String statementId) throws Exception {
        String sql = "";
        try{
        	sql = sessionFactory.getObject().getConfiguration().getMappedStatement(statementId).getBoundSql(null).getSql();
        } catch(Exception e) {
        	log.error("getSql" ,e);
        	throw e;
        }
        return sql;         
    }
}
