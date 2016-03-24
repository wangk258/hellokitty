package com.weixin.util;

import java.security.MessageDigest;
import java.util.Arrays;

public class SourceValidate {

	public static boolean validate(String signature, String timestamp,
			String nonce) throws Exception {

		String token = "32767";

		String[] paramArray = { token, timestamp, nonce };

		Arrays.sort(paramArray);

		String param = paramArray[0]
				.concat(paramArray[1].concat(paramArray[2]));

		MessageDigest md = MessageDigest.getInstance("SHA-1");

		byte[] secret = md.digest(param.getBytes());

		String s = "";

		for (int i = 0; i < secret.length; i++) {
			s += byte2str(secret[i]);
		}

		return signature.toUpperCase().equals(s.toUpperCase());
	}

	private static String byte2str(byte b) {

		char[] degist = new char[] { '0','1', '2', '3', '4', '5', '6', '7', '8',
				'9', 'A', 'B', 'C', 'D', 'E', 'F' };

		char[] c = new char[2];

		c[0] = degist[(b >>> 4) & 0x0F];

		c[1] = degist[(b & 0x0F)];

		return new String(c);
	}

}
