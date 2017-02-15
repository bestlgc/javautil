package com.primeton.pub.common.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * 2014-7-8
 * 
 */
public class ThreadUtilTest {

	/**
	 * 让线程睡眠多少毫秒
	 */
	@Test
	public void sleepTest() {
		try {
			ThreadUtil.sleep(1000);
		} catch (Exception e) {
			Assert.fail();
		}

	}

}
