package com.primeton.pub.common.util;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class RandomUtilTest {

	/**
	 * 测试是否取得随机字符串
	 */
	@Test
	public void getRandomStringTest() {
		try {
			Assert.assertTrue(RandomUtil.getRandomString(2, 0).matches("^[0-9]*$"));
		} catch (Exception e) {
			Assert.fail();
		}
		
		try {
			Assert.assertTrue(RandomUtil.getRandomString(2, 1).matches("^[A-Za-z]+$"));
		} catch (Exception e) {
			Assert.fail();
		}
		
		try {
			Assert.assertTrue(RandomUtil.getRandomString(2, 2).matches("^[A-Za-z0-9]+$"));
		} catch (Exception e) {
			Assert.fail();
		}
		
		try {
			Assert.assertTrue(RandomUtil.getRandomString(2, 3).matches("^[A-Za-z0-9_]+$"));
		} catch (Exception e) {
			Assert.fail();
		}
		
		try {
			Assert.assertTrue(RandomUtil.getRandomString(2, 4).matches("(.|\n)*"));
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
	/**
	 * 测试是否能取得随机字符串
	 */
	@Test
	public void randomTest(){
		
		char[] DIGIT_LETTER = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_".toCharArray();
		try {
			Assert.assertEquals(RandomUtil.random(0, 0, 2, true, false, null, new Random()),"");
		} catch (Exception e) {
			Assert.fail();
		}
		
		try {
			RandomUtil.random(-1, 0, 2, true, false, null, new Random());
		} catch (Exception e) {
			Assert.assertEquals(e.toString(),"java.lang.IllegalArgumentException: Requested random string length -1 is less than 0.");
		}
		
		try {
			Assert.assertTrue(RandomUtil.random(2, 0, 0, false, true, null, new Random()).matches("^[0-9]*$"));
		} catch (Exception e) {
			Assert.fail();
		}
		
		try {
			Assert.assertTrue(RandomUtil.random(2, 0, 0, true, false, null, new Random()).matches("^[A-Za-z]+$"));
		} catch (Exception e) {
			Assert.fail();
		}
		
		try {
			Assert.assertTrue(RandomUtil.random(2, 0, 0, true, true, null, new Random()).matches("^[A-Za-z0-9]+$"));
		} catch (Exception e) {
			Assert.fail();
		}
		
		try {
			Assert.assertTrue(RandomUtil.random(2, 0, DIGIT_LETTER.length, false, false, DIGIT_LETTER, new Random()).matches("^[A-Za-z0-9_]+$"));
		} catch (Exception e) {
			Assert.fail();
		}
		
		try {
			Assert.assertTrue(RandomUtil.random(2, 0, 0, false, false, null, new Random()).matches("(.|\n)*"));
		} catch (Exception e) {
			Assert.fail();
		}
	}

}
