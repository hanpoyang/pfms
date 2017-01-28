package org.tom.pfms.common.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class MyAuthenticator extends Authenticator {
    String userName=null;   
    String password=null; 
    private static MyAuthenticator auth = null;
    private static Session session = null;
        
    public MyAuthenticator(){   
    }   
    public MyAuthenticator(String username, String password) {    
        this.userName = username;    
        this.password = password;    
    }    
    protected PasswordAuthentication getPasswordAuthentication(){   
        return new PasswordAuthentication(userName, password);   
    }   
    
    public static MyAuthenticator getInstance(String userName, String password){   
        if(auth == null) {
            auth = new MyAuthenticator(userName, password);
        }
        return auth;
    } 
    
    public static Session getSession(Properties pro, Authenticator authenticator) {
        if(session == null) {
        	session = Session.getDefaultInstance(pro,authenticator);
        }
        return session;
    }
}
