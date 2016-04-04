package com.weixin.core;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.weixin.service.MessageService;
import com.weixin.util.SourceValidate;

public class CoreServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");

		PrintWriter printer = resp.getWriter();

		try {
			if (SourceValidate.validate(signature, timestamp, nonce)) {
				printer.print(echostr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		printer.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Logger logger = Logger.getLogger(CoreServlet.class);

//		final InputStream stream = req.getInputStream();
		
		String signature = req.getParameter("signature");
//		
//		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
//		
//		String result="",line;
//		
//		while((line = reader.readLine())!=null){
//			result+=line;
//		}
//		System.out.println(result);
		
		
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		
		resp.setCharacterEncoding("utf-8");
		
		req.setCharacterEncoding("utf-8");

		PrintWriter writer = resp.getWriter();

		try {
			if (SourceValidate.validate(signature, timestamp, nonce)) {

				String respXml = MessageService.process(req);

				logger.info(respXml);

				writer.write(respXml);

			}
		} catch (Exception e) {

			e.printStackTrace();

			writer.write("");
		}

		writer.close();
	}
}
