package com.primeton.pub.common.util;

import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author zhangxu
 * 
 */
public class Base64UtilTest {

	/**
	 * 测试 Base64转码
	 */
	@Test
	public void encodeTest() {
		try {
			Assert.assertArrayEquals(Base64Util.encode(new byte[1]), Base64.encodeBase64(new byte[1]));
		} catch (Exception e) {
			Assert.fail();
		}
	}

	/**
	 * 测试 Base64解码
	 */
	@Test
	public void decodeTest() {
		try {
			Assert.assertArrayEquals(Base64Util.decode(new byte[1]), Base64.decodeBase64(new byte[1]));
		} catch (Exception e) {
			Assert.fail();
		}
	}

	/**
	 * 测试 Base64解码
	 */
	@Test
	public void decodeTest1() {
		try {
			Assert.assertArrayEquals(Base64Util.decode("test"), Base64.decodeBase64("test"));
		} catch (Exception e) {
			Assert.fail();
		}
	}

	/**
	 * 测试 Base64转码
	 */
	@Test
	public void encodeStringTest() {
		try {
			Assert.assertEquals(Base64Util.encodeString(new byte[1]), Base64.encodeBase64String(new byte[1]));
		} catch (Exception e) {
			Assert.fail();
		}
	}

	/**
	 * 测试 是否是base64编码
	 */
	@Test
	public void isBase64Test() {
		try {
			Assert.assertTrue(Base64Util.isBase64(Base64Util.encodeString("test".getBytes())));
		} catch (Exception e) {
			Assert.fail();
		}
	}
}
