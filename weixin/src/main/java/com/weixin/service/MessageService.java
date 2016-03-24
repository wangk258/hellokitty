package com.weixin.service;

import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.weixin.resp.TextRespMessage;
import com.weixin.util.MessageUtils;

public class MessageService {
	
	public static String process(HttpServletRequest request) throws Exception {

		Map<String, String> requestMap  = MessageUtils.parseXML(request);
		
		XStream xs = new XStream(new XppDriver() {
			@Override
			public HierarchicalStreamWriter createWriter(Writer out) {
				return new PrettyPrintWriter(out) {
					@Override
					public void startNode(String name) {
						super.startNode(name);
					}

					@Override
					protected void writeText(QuickWriter writer, String text) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					}
				};
			}
		});
		
		String msgType = requestMap.get("MsgType");
		
		if("event".equals(msgType.toLowerCase())){
			
			String eventType = requestMap.get("Event");
			
			if("subscribe".equals(eventType.toLowerCase())){
				
				String content = "欢迎关注风沙娱乐微信公众号。祝您玩得愉快!";
				
				return sendTextMsg(content,xs,requestMap);
				
			}else if("location".equals(eventType.toLowerCase())){
				
			}
			
		}
		
		else if ("text".equals(msgType.toLowerCase())) {
			
			String content = "你发的文字信息！";
			
			return sendTextMsg(content,xs,requestMap);
			
		}
		
		else if("location".equals(msgType.toLowerCase())){
			
			String content = "欢迎共享您的位置信息！";
			
			return sendTextMsg(content,xs,requestMap);
		}
		
		else if("voice".equals(msgType.toLowerCase())){
			
			String content = "我暂时还不能识别你说的话呢！";
			
			return sendTextMsg(content,xs,requestMap);
		}
		
		else if("image".equals(msgType.toLowerCase())){

			String content = "图片虽美，我却不识！ 抱歉。";
			
			return sendTextMsg(content,xs,requestMap);
			
		}
		
		return "";

	}
	
	private static String sendTextMsg(String content,XStream xs,Map<String,String> requestMap){
		
		TextRespMessage resp = new TextRespMessage();

		resp.setContent(content);

		resp.setCreateTime(requestMap.get("CreateTime"));

		resp.setFromUserName(requestMap.get("ToUserName"));

		resp.setToUserName(requestMap.get("FromUserName"));

		resp.setMsgType("text");

		xs.alias("xml", TextRespMessage.class);

		return xs.toXML(resp);
	}
}
