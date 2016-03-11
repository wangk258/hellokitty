package org.util;

import org.bo.MailSenderInfo;
import org.bo.ResultFlag;
import org.bo.SimpleMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendMailUtil{

	public ResultFlag sendTextMail(MailSenderInfo info) throws Exception {
		try {
			SimpleMailSender.sendTextMail(info);
			return new ResultFlag(true);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	public ResultFlag sendHtmlMail(MailSenderInfo info) throws Exception {
		try {
			SimpleMailSender.sendHtmlMail(info);
			return new ResultFlag(true);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
}
