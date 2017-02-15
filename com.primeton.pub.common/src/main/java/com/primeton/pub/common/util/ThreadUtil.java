package com.primeton.pub.common.util;

/**
 * 线程工具类
 * 
 */
public class ThreadUtil {
	/**
	 * 让线程睡眠多少毫秒
	 * 
	 * @param ms
	 */
	public static void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (Exception e) {
		}
	}
}
