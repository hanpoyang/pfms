package org.tom.pfms.common.utils;

import java.util.ResourceBundle;

public class MessageUtils {
	
	public static String getMessage(String key){
		ResourceBundle bundle = ResourceBundle.getBundle("message");
		return bundle.getString(key);
	}

}
