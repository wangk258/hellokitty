package com.weixin.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class MessageUtils {

	public static Map<String, String> parseXML(HttpServletRequest request)
			throws Exception {

//		BufferedReader reader1 = new BufferedReader(new InputStreamReader(request.getInputStream()));
//
//		String result = "", line;
//
//		while ((line = reader1.readLine()) != null) {
//			result += line;
//		}
//		System.out.println("cc="+result);

		Map<String, String> requestMap = new HashMap<String, String>();

		SAXReader reader = new SAXReader();

		Document document = reader.read(request.getInputStream());

		Element root = document.getRootElement();

		@SuppressWarnings("unchecked")
		List<Element> elements = root.elements();

		for (Element element : elements) {
			requestMap.put(element.getName(), element.getText());
		}
		
		return requestMap;
	}

}
