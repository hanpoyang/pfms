package org.tom.pfms.common.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;

public class MailUtil {
	private static final String[] folderNames = new String[]{"abc", "cgb", "spdb", "cmb"}; 
	
	public static List<Map<String, Object>> getMailList() throws Exception {
		String host = "imap.163.com"; // 【pop.mail.yahoo.com.cn】
		String username = "hanpoyang"; // 【wwp_1124】
		String password = "haton142401"; // 【........】
		List<Map<String, Object>> mailList = new ArrayList<Map<String, Object>>();
		Properties props = new Properties();
		Session session = MyAuthenticator.getSession(props, null);
		Store store = session.getStore("imap");
		store.connect(host, username, password);
		Folder[] folders = store.getDefaultFolder().list();
		for(Folder folder : folders) {
			folder.open(Folder.READ_WRITE);
			String folderName = folder.getFullName();
			Arrays.sort(folderNames);
			if(Arrays.binarySearch(folderNames, folderName) > -1) {
				Message message[] = folder.getMessages();
				for (int i = 0; i < message.length; i++) {
					String subject = message[i].getSubject();
					System.out.println(subject);
					Date receivedDate = message[i].getReceivedDate();
					if (subject.indexOf("电子账单") > -1 || subject.indexOf("电子对账单") > -1) {
						Map<String, Object> mailMap = new HashMap<String, Object>();
						String body = getMailContent(message[i]);
						mailMap.put("Subject", subject);
						mailMap.put("ReceivedDate", receivedDate);
						mailMap.put("Body", body);
						saveFile(subject, body);
						mailMap.put("Folder", folderName);
						mailList.add(mailMap);
					}
				}
			}
			
		}
		return mailList;
	}

	public static String getMailContent(Part part) throws Exception {
		StringBuffer bodytext = new StringBuffer();
		String contenttype = part.getContentType();
		if(contenttype == null) return "";
		int nameindex = contenttype.indexOf("name");
		boolean conname = false;
		if (nameindex != -1)
			conname = true;
		if (part.isMimeType("text/plain") && !conname) {
			bodytext.append((String) part.getContent());
		} else if (part.isMimeType("text/html") && !conname) {
			bodytext.append((String) part.getContent());
		} else if (part.isMimeType("multipart/*")) {
			Multipart multipart = (Multipart) part.getContent();
			int counts = multipart.getCount();
			for (int i = 0; i < counts; i++) {
				bodytext.append(getMailContent(multipart.getBodyPart(i)));
			}
		} else if (part.isMimeType("message/rfc822")) {
			bodytext.append(getMailContent((Part) part.getContent()));
		} else {
		}
		return bodytext.toString();
	}

	private static void saveFile(String fileName, String body) {
		OutputStream out = null;
		try{
			out = new FileOutputStream(fileName);
			out.write(body.getBytes());
		}catch(IOException e) {
			e.printStackTrace();
		} finally {
			try{
				if(null != null){
					out.close();
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String args[])throws Exception{
		List r = getMailList();
//		System.out.println();
	}

}


