package org.tom.pfms.common.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;

import com.google.gson.Gson;


/**
 * @ServerEndpoint ע����һ�����ε�ע�⣬���Ĺ�����Ҫ�ǽ�Ŀǰ���ඨ���һ��websocket��������,
 * ע���ֵ�������ڼ����û����ӵ��ն˷���URL��ַ,�ͻ��˿���ͨ�����URL�����ӵ�WebSocket��������
 */
@ServerEndpoint("/websocket")
public class WebSocketHelper {
    //��̬������������¼��ǰ������������Ӧ�ð�����Ƴ��̰߳�ȫ�ġ�
    private static int onlineCount = 0;
    public static int shutdown = 1;
    private static Logger log = Logger.getLogger(WebSocketHelper.class);

    //concurrent�����̰߳�ȫSet���������ÿ���ͻ��˶�Ӧ��MyWebSocket������Ҫʵ�ַ�����뵥һ�ͻ���ͨ�ŵĻ�������ʹ��Map����ţ�����Key����Ϊ�û���ʶ
    private static CopyOnWriteArraySet<WebSocketHelper> webSocketSet = new CopyOnWriteArraySet<WebSocketHelper>();

    //��ĳ���ͻ��˵����ӻỰ����Ҫͨ���������ͻ��˷�������
    private Session session;

    /**
     * ���ӽ����ɹ����õķ���
     * @param session  ��ѡ�Ĳ�����sessionΪ��ĳ���ͻ��˵����ӻỰ����Ҫͨ���������ͻ��˷�������
     */
    @OnOpen
    public void onOpen(Session session){
    	onlineCount++;
    	log.info("+++++++++++++++++++++++++++++++++++++open+++++++++++++++++++++++++++++++++++++"+shutdown);
        this.session = session;
        webSocketSet.add(this);     //����set��
        shutdown = 1;
        System.out.println("�������Ӽ��룡��ǰ��������Ϊ" + getOnlineCount());
        new Thread(new Runnable(){
        	public void run(){
        		if(!CommonUtils.pc_status.isEmpty()) {
	        		List<Map<String, String>> mapsAll = new ArrayList<Map<String, String>>();
	        		for(Map.Entry<String, String> entry : CommonUtils.pc_status.entrySet()) {
	        			Map<String, String> item = new HashMap<String, String>();
	        			item.put("ip", entry.getKey());
	        			item.put("status", entry.getValue());
	        			mapsAll.add(item);
	        		}
	        		CommonUtils.status_queue.clear();
	        		if(mapsAll.size() > 0) {
		        		Gson gson = new Gson();
		        		String jsonStr = gson.toJson(mapsAll);
		        		log.info("Reading...........all..status............................"+jsonStr);
		        	    sendMsg(jsonStr);
	        		}
        		}
		        while(shutdown == 1){
		        	if(!CommonUtils.status_queue.isEmpty()) {
		        		List<Map<String, String>> maps = new ArrayList<Map<String, String>>();
		        		for(Map.Entry<String, String> entry : CommonUtils.status_queue.entrySet()) {
		        			Map<String, String> item = new HashMap<String, String>();
		        			item.put("ip", entry.getKey());
		        			item.put("status", entry.getValue());
		        			maps.add(item);
		        		}
		        		CommonUtils.status_queue.clear();
		        		if(maps.size() > 0) {
			        		Gson gson = new Gson();
			        		String jsonStr = gson.toJson(maps);
			        		System.out.println("Reading..............status...............................");
			        	    sendMsg(jsonStr);
		        		}
		        	}
		        	try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
		        }
        	}
        }).start();
    }

    /**
     * ���ӹرյ��õķ���
     */
    @OnClose
    public void onClose(Session session, CloseReason reason){
    	log.info("+++++++++++++++++++++++++++++++++++++close+++++++++++++++++++++++++++++++++++++"+shutdown);
        webSocketSet.remove(this);  //��set��ɾ��
        subOnlineCount();           //��������
        shutdown = 0;
        System.out.println("��һ���ӹرգ���ǰ��������Ϊ" + getOnlineCount());
    }

    /**
     * �յ��ͻ�����Ϣ����õķ���
     * @param message �ͻ��˷��͹�������Ϣ
     * @param session ��ѡ�Ĳ���
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("���Կͻ��˵���Ϣ:" + message);
        //Ⱥ����Ϣ
        for(WebSocketHelper item: webSocketSet){
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    /**
     * ��������ʱ����
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("��������");
        error.printStackTrace();
    }

    /**
     * ������������漸��������һ����û����ע�⣬�Ǹ����Լ���Ҫ��ӵķ�����
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException{
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }


    public static synchronized void subOnlineCount() {
    	WebSocketHelper.onlineCount--;
    }
    
    public static synchronized void sendMsg(String msg){
    	for(WebSocketHelper item: webSocketSet){
            try {
                item.sendMessage(msg);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }
}