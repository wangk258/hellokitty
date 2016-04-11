package common.util;

import org.springframework.stereotype.Service;

import common.bo.MailSenderInfo;
import common.bo.ResultFlag;
import common.bo.SimpleMailSender;

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
