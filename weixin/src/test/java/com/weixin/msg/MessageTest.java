package com.weixin.msg;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import org.junit.Test;

public class MessageTest {

	@Test
	public void testSendMsg() {

		try {
			PrintWriter out = null;
			BufferedReader in=null;
			String result="";

			URL realUrl = new URL(
					"http://fengsha.duapp.com/angel?signature=64d463730aa2f9c41c770d8b92c36e9c186cec3f&echostr=8788375066355163102&timestamp=1445482334&nonce=1101978556");
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//			conn.connect();
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数

			String param = "body=<xml><ToUserName><![CDATA[penganhuatian]]></ToUserName><FromUserName><![CDATA[penganhuatian]]></FromUserName><CreateTime>1445496171466</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[哈哈哈]]></Content><MsgId>1445496171466</MsgId></xml>";
//
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			
			in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
			
            String line;
            
            while ((line = in.readLine()) != null) {
                result += line;
            }
            System.out.println("result = " + result);
            
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

	}
	
	@Test
	public void testParseXml(){
//		String xml="<xml><URL><![CDATA[http://fengsha.duapp.com/angel]]></URL><ToUserName><![CDATA[penganhuatian]]></ToUserName><FromUserName><![CDATA[penganhuatian]]></FromUserName><CreateTime>1445496171466</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[哈哈哈]]></Content><MsgId>1445496171466</MsgId></xml>";
//		
//		SAXReader reader = new SAXReader();
//		
//		try {
//			reader.read(xml);
//		} catch (DocumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
