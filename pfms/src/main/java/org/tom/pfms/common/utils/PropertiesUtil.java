package org.tom.pfms.common.utils;

import java.util.ResourceBundle;

public class PropertiesUtil {
	public static String getString(String key){
		ResourceBundle bundle = ResourceBundle.getBundle("pfms-config");
		return bundle.getString(key);
	}
}
