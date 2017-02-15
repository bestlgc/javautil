package com.primeton.pub.common.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * 2014-7-8
 * 
 * 字符串处理工具类测试类
 * 
 */

public class StringUtilTest {

	/**
	 * 判断是否为null
	 */
	@Test
	public void isNullTest() {
		Assert.assertFalse(StringUtil.isNull(""));
		Assert.assertTrue(StringUtil.isNull(null));
	}

	/**
	 * 判断不为null
	 */
	@Test
	public void isNotNullTest() {
		Assert.assertTrue(StringUtil.isNotNull(""));
		Assert.assertFalse(StringUtil.isNotNull(null));
	}

	/**
	 * 判断为空，null、空字符串
	 */
	@Test
	public void isBlankTest() {
		Assert.assertTrue(StringUtil.isBlank(""));
		Assert.assertFalse(StringUtil.isBlank("test"));
		Assert.assertTrue(StringUtil.isBlank(""));
	}

	/**
	 * 判断不为空
	 */
	@Test
	public void isNotBlankTest() {
		Assert.assertFalse(StringUtil.isNotBlank(""));
		Assert.assertTrue(StringUtil.isNotBlank("test"));
		Assert.assertFalse(StringUtil.isNotBlank(null));
	}

	/**
	 * 把数字格式化为指定的字符串风格。 比如：10个零，前面补零
	 */
	@Test
	public void formatSeqTest1() {
		String formattedStr = StringUtil.formatSeq(0, "2323");
		Assert.assertEquals(formattedStr, "0000002323");
	}

	/**
	 * 把数字格式化为指定的字符串风格。
	 */
	@Test
	public void formatSeqTest2() {
		String formattedStr = StringUtil.formatSeq("-", "24436");
		Assert.assertEquals(formattedStr, "-----24436");
	}
}
