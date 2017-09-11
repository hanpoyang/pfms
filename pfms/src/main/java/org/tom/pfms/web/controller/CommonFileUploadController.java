package org.tom.pfms.web.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommonFileUploadController extends BaseController {
	
	@Value("${upload.temp.dir}")
	String tempDir = null;
	
	@RequestMapping(value="/common/upload", produces = {"text/plain;charset=UTF-8"})
	@ResponseBody
    public String getUploadFileStr(HttpServletRequest request) 
    		throws Exception {
    	InputStream in = null;
    	String dataStr = null;
    	Map<String, String> importDataMap = new HashMap<String, String>();
    	try{
    		ServletFileUpload  fileUpload = new ServletFileUpload();
    		fileUpload.setFileSizeMax(10*1024*1024);
    		DiskFileItemFactory factory = new DiskFileItemFactory();
    		factory.setRepository(new File(tempDir));
    		fileUpload.setHeaderEncoding("UTF-8");
    		fileUpload.setFileItemFactory(factory);
    		fileUpload.setSizeMax(10*1024*1024);
    		List<FileItem> fileItems  = fileUpload.parseRequest(request);
    		Iterator<FileItem> i = fileItems.iterator();
    		while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if (fi.isFormField()) {
                	 String fieldName = fi.getFieldName();
                	 String fieldValue=fi.getString("utf-8");
                	 importDataMap.put(fieldName, fieldValue);
                } else {
                    in = fi.getInputStream();
                    dataStr = streamToString(in);
                    importDataMap.put("DetailData", dataStr);
                }
    		}
    		String sessionId = request.getSession(false).getId();
    		serieMap(importDataMap, sessionId);
    	}catch(Exception e) {
    		log.error("uploadFile", e);
    	} finally {
    		if(null != in) {
    			try{
    				in.close();
    			} catch(IOException e) {
    				log.error("getUploadFileStr", e);
    			}
    		}
    	}
    	dataStr = dataStr.endsWith("\r\n") ? dataStr.substring(0, dataStr.length() - 1) : dataStr;
    	return dataStr.replaceAll("\"", "");
    }
    
    /*
     * 工具方法，转换流到字符串
     */
    private String streamToString(InputStream in) throws IOException {
    	BufferedReader reader = null;
    	StringBuilder sbuff = new StringBuilder();
    	try{
    		reader = new BufferedReader(new InputStreamReader(in, "GBK"));
    		String line = null;
    		while((line = reader.readLine()) != null) {
    			sbuff.append(line).append("\r\n");
    		}
    	} catch(IOException e) {
    		log.error("streamToString", e);
    	} finally {
    		if(null != reader)reader.close();
    	}
    	return sbuff.toString();
    }
    
    private void serieMap(Object obj, String fileName){
    	OutputStream out = null;
    	ObjectOutputStream oOut = null;
    	try{
    		out = new FileOutputStream(tempDir + File.separator + fileName);
    		oOut = new ObjectOutputStream(out);
    		oOut.writeObject(obj);
    	}catch(IOException e) {
    		log.error("serieMap", e);
    	}finally {
    		try{
    			if(null != out) out.close();
    			if(null != oOut) oOut.close();
        	}catch(IOException e) {
        		log.error("serieMap", e);
        	}
    	}
    }
}
