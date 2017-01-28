package org.tom.pfms.common.utils;

import java.util.UUID;

public class CommonUtils {
    public static String getUUID() {
    	UUID uuid = UUID.randomUUID();
    	return uuid.toString();
    	
    }
    
}
