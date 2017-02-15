package com.primeton.pub.common.util;

import java.security.MessageDigest;

public class MessageDigestUtil {

	/**
	 * 对消息进行MD5摘要（UTF-8编码）
	 * 
	 * @param message
	 *            要进行摘要的消息
	 * @return 摘要后的字符串
	 */
	public static String getMD5(String message) {
		if (message != null) {
			try {
				MessageDigest alga = MessageDigest.getInstance("MD5");
				alga.update(message.getBytes("UTF-8"));
				return byte2hex(alga.digest());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private static String byte2hex(byte[] b) {
		StringBuilder hs = new StringBuilder();
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0XFF);
			if (stmp.length() == 1) {
				hs.append("0").append(stmp);
			} else {
				hs.append(stmp);
			}
		}
		return hs.toString().toUpperCase();
	}

}