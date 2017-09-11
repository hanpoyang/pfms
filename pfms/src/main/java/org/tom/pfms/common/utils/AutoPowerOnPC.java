package org.tom.pfms.common.utils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

import org.apache.log4j.Logger;

public class AutoPowerOnPC {
	
	private static Logger log = Logger.getLogger(AutoPowerOnPC.class);
	
	 private int port = 6144;
//   private String mac = "00-E0-66-F0-09-3F";
   private String mac = "30-5A-3A-06-63-3C";
   private String ipAddress = "255.255.255.255";

   public AutoPowerOnPC()
   {
       // TODO Auto-generated constructor stub
   }
    
   private void setMac(String mac)
   {
       this.mac = mac;
   }
    
   public AutoPowerOnPC(int port, String mac, String ipAddress)
   {
       this.port = port;
       this.mac = mac;
       this.ipAddress = ipAddress;
   }

   public void sendOpenOS()
   {
       InetAddress destHost = null;
       DatagramSocket ds = null;
       try
       {
           destHost = InetAddress.getByName(ipAddress);
           byte[] destMac = getMacBytes(mac);
           byte[] magicBytes = new byte[102];

           // �����ݰ���ǰ��λ����0xFF����FF���Ķ�����
           // ��һ��BroadCast�����������ݰ����������ݾͿ����ˡ�
           // FF FF FF FF FF FF��6��FF�����ݵĿ�ʼ��
           // ������16��MAC��ַ�Ϳ����ˡ�
           for (int i = 0; i < 16; i++)
           {
               for (int j = 0; j < destMac.length; j++)
               {
                   magicBytes[6 + destMac.length * i + j] = destMac[j];
                   magicBytes[j] = (byte) 0xFF;
               }
           }

            
           DatagramPacket dp = null;
           try
           {
               dp = new DatagramPacket(magicBytes, magicBytes.length, destHost, port);
               ds = new DatagramSocket();
               ds.send(dp);
           }
           catch (Exception e)
           {
               // TODO: handle exception
               e.printStackTrace();
           }
       }
       catch (Exception e)
       {
           // TODO: handle exception
           e.printStackTrace();
       }
       finally
       {
           if (null != ds)
           {
               //�ر����ݱ���
               ds.close();
           }
       }
   }

   // Զ�̹ػ�
   public void sendCloseOS()
   {

   }
    
   /**
    *  ��16���Ƶ�mac��ַת��Ϊ������ 
    * @param macStr
    * @return 
    * @throws IllegalArgumentException
    */
   private static byte[] getMacBytes(String macStr) throws IllegalArgumentException
   {
       byte[] bytes = new byte[6];
       String[] hex = macStr.split("(\\:|\\-)");
       if (hex.length != 6)
       {
           throw new IllegalArgumentException("mac ��ַ�����⣬�㿴���Բ��ԣ�Invalid MAC address.");
       }
       try
       {
           for (int i = 0; i < 6; i++)
           {
               bytes[i] = (byte) Integer.parseInt(hex[i], 16);
           }
       }
       catch (NumberFormatException e)
       {
           throw new IllegalArgumentException("Invalid hex digit in MAC address.");
       }
       return bytes;
   }
   
   public static void wakePc(String mac) {
	   try{
		   AutoPowerOnPC instance = new AutoPowerOnPC();
		   instance.setMac(mac);
		   instance.sendOpenOS();
	   }catch(Exception e) {
		   log.error("wakePc", e);
	   }
   }
   
   
   public static void wakePc() {
	   OutputStream out = null;
	   InputStream in = null;
	   DataOutputStream dos = null;
	   Socket s = null;
	   try{
		   String openLine = "on1:01";
	       s = new Socket("192.168.0.105", 5000);
	       out = s.getOutputStream();
	       dos = new DataOutputStream(out);
	       dos.write(openLine.getBytes());
	       in = s.getInputStream();
	       byte[] buf = new byte[in.available()];
	       in.read(buf, 0 , in.available());
	       String tmp = new String(buf);
	       if(tmp.length() == 0) {
	    	   wakePc();
	       }
	   }catch(Exception e) {
		   log.error("wakePc", e);
	   } finally {
		   try{
			   if(out != null) out.close();
			   if(dos != null) dos.close();
			   if(s != null) s.close();
		   }catch(IOException ex) {
			   log.error("wakePc", ex);
		   }
	   }
   }
}
