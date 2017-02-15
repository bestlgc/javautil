package com.primeton.pub.common.util;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author zhangxu ---- zhangxu@primeton.com
 * 
 */
public class AsserUtilTest {

	/**
	 * 测试单个对象是否能为空
	 */
	@Test
	public void assertObjectNotNullTest() {
		try {
			AssertUtil.assertObjectNotNull(null, "test");
		} catch (Exception e) {
		}

		try {
			AssertUtil.assertObjectNotNull(new String("12345"), "test");
		} catch (Exception e) {
		}

		try {
			AssertUtil.assertObjectNotNull("", "test");
		} catch (Exception e) {
		}

		try {
			AssertUtil.assertObjectNotNull("test", "test");
		} catch (Exception e) {
			Assert.fail();
		}

	}

	/**
	 * 测试参数是否能为空
	 */
	@Test
	public void assertNotNullTest() {
		try {
			AssertUtil.assertNotNull();
		} catch (Exception e) {
		}

		try {
			AssertUtil.assertNotNull("test", null);
		} catch (Exception e) {
		}

		try {
			AssertUtil.assertNotNull("test", new String("12345"));
		} catch (Exception e) {
		}

		try {
			AssertUtil.assertNotNull("test", "");
		} catch (Exception e) {
		}

		try {
			AssertUtil.assertNotNull("test");
		} catch (Exception e) {
			Assert.fail();
		}
	}

	/**
	 * 测试前一个时间是否小于后一个时间
	 */
	@Test
	public void assertLessThenTest() {
		try {
			AssertUtil.assertLessThen(new Date(123456), new Date(12345));
		} catch (Exception e) {
		}

		try {
			AssertUtil.assertLessThen(new Date(12345), new Date(123456));
		} catch (Exception e) {
			Assert.fail();
		}
	}

	/**
	 * 测试复数参数是否为负数（含0）
	 */
	@Test
	public void assertNotNegativeTest() {
		try {
			AssertUtil.assertNotNegative();
		} catch (Exception e) {
		}

		try {
			AssertUtil.assertNotNegative(2, -1);
		} catch (Exception e) {
		}

		try {
			AssertUtil.assertNotNegative(2, 0);
		} catch (Exception e) {
			Assert.fail();
		}

		try {
			AssertUtil.assertNotNegative(2, 2);
		} catch (Exception e) {
			Assert.fail();
		}
	}

	/**
	 * 测试单一参数是否为负数
	 */
	@Test
	public void assertNotNegativeTest1() {
		try {
			AssertUtil.assertNotNegative(null, "test");
		} catch (Exception e) {
		}

		try {
			AssertUtil.assertNotNegative(-1, "test");
		} catch (Exception e) {
		}

		try {
			AssertUtil.assertNotNegative(0, "test");
		} catch (Exception e) {
			Assert.fail();
		}

		try {
			AssertUtil.assertNotNegative(2, "test");
		} catch (Exception e) {
			Assert.fail();
		}
	}

	/**
	 * 测试复数参数是否为正数
	 */
	@Test
	public void assertPositiveTest() {
		try {
			AssertUtil.assertPositive();
		} catch (Exception e) {
		}

		try {
			AssertUtil.assertPositive(2, -1);
		} catch (Exception e) {
		}

		try {
			AssertUtil.assertPositive(2, 0);
		} catch (Exception e) {
		}

		try {
			AssertUtil.assertPositive(2, 2);
		} catch (Exception e) {
			Assert.fail();
		}
	}

	/**
	 * 测试单一参数是否为正数
	 */
	@Test
	public void assertPositiveTest1() {
		try {
			AssertUtil.assertPositive(null, "test");
		} catch (Exception e) {
		}

		try {
			AssertUtil.assertPositive(-1, "test");
		} catch (Exception e) {
		}

		try {
			AssertUtil.assertPositive(0, "test");
		} catch (Exception e) {
		}

		try {
			AssertUtil.assertPositive(2, "test");
		} catch (Exception e) {
			Assert.fail();
		}
	}

	/**
	 * 测试两个数值是否相等
	 */
	@Test
	public void assertNotEqualsTest() {
		try {
			AssertUtil.assertNotEquals(1, 1);
		} catch (Exception e) {
		}

		try {
			AssertUtil.assertNotEquals(1, 2);
		} catch (Exception e) {
			Assert.fail();
		}
	}

}
