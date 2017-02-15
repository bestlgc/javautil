package com.primeton.pub.common.util;

/**
 * 字符串处理工具类
 * 
 * @author Administrator
 * 
 */
public class StringUtil {

	/**
	 * 判断是否为null
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str) {
		return str == null;
	}

	/**
	 * 判断不为null
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotNull(String str) {
		return !isNull(str);
	}

	/**
	 * 判断为空，null、空字符串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		return !isNotBlank(str);
	}

	/**
	 * 判断不为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {
		return str != null && !"".equals(str);
	}

	// 格式化字串，10个零
	public static final String FORMAT_SEQ = "0000000000";

	/**
	 * 把数字格式化为指定的字符串风格。 比如：10个零，前面补零
	 * 
	 * @param i
	 * @param format
	 * @return
	 */
	public static String formatSeq(int i, String format) {

		StringBuffer str = new StringBuffer(format);
		while (str.length() < 10) {
			str.insert(0, i);
		}
		return str.toString();

	}

	/**
	 * 把数字格式化为指定的字符串风格。
	 * 
	 * @param i
	 * @param format
	 * @return
	 */
	public static String formatSeq(String i, String format) {
		StringBuffer str = new StringBuffer(format);
		while (str.length() < 10) {
			str.insert(0, i);
		}
		return str.toString();
	}
}
