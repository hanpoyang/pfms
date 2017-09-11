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
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
@ServerEndpoint("/websocket")
public class WebSocketHelper {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    public static int shutdown = 1;
    private static Logger log = Logger.getLogger(WebSocketHelper.class);

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArraySet<WebSocketHelper> webSocketSet = new CopyOnWriteArraySet<WebSocketHelper>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 连接建立成功调用的方法
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session){
    	onlineCount++;
    	log.info("+++++++++++++++++++++++++++++++++++++open+++++++++++++++++++++++++++++++++++++"+shutdown);
        this.session = session;
        webSocketSet.add(this);     //加入set中
        shutdown = 1;
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
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
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session, CloseReason reason){
    	log.info("+++++++++++++++++++++++++++++++++++++close+++++++++++++++++++++++++++++++++++++"+shutdown);
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减
        shutdown = 0;
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
        //群发消息
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
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
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