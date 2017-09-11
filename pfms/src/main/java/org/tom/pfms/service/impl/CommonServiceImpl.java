package org.tom.pfms.service.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.tom.pfms.common.exception.ServiceException;
import org.tom.pfms.service.CommonService;

public class CommonServiceImpl implements CommonService {
	
	@Override
	public Object entryService(String json) throws ServiceException {
		
		 ScriptEngineManager manager = new ScriptEngineManager();
	     ScriptEngine engine = manager.getEngineByName("javascript");
	     InputStream in = null;
		 try {
			in = this.getClass().getClassLoader().getResourceAsStream("./test.js");
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			Object obj = engine.eval(reader);
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			logger.error("entryService", e);
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("entryService", e);
			}
		}
		return null;
	}
	
	public String getJs(String sourceId) {
		return "";
	}
	
	public static void main(String a[]) throws Exception  {
		new CommonServiceImpl().entryService("");
	}
}
