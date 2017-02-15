package com.primeton.pub.common.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author zhangxu
 * 
 */
public class DateUtilTest {

	/**
	 * 测试是否可以获得一个日历
	 */
	@Test
	public void getMaxExpireDateTest() {
		try {
			DateUtil.getMaxExpireDate();
		} catch (Exception e) {
			Assert.fail();
		}
	}

	/**
	 * 测试是否能判断当前时间是否在指定的时间段内
	 */
	@Test
	public void betweenTest() {
		try {
			Assert.assertTrue(DateUtil.between(123456, 12345, 1234567));
		} catch (Exception e) {
			Assert.fail();
		}

		try {
			Assert.assertTrue(DateUtil.between(1234567, 12345, 12345678));
		} catch (Exception e) {
		}

	}

	/**
	 * 测试是否能获取指定时间的下一个小时起点
	 */
	@Test
	public void getNextHourTest() {
		try {
			Assert.assertEquals(DateUtil.getNextHour(1), 60 * 60 * 1000);
		} catch (Exception e) {
			Assert.fail();
		}
	}

	/**
	 * 测试是否能获取指定时间的下一个天起点
	 */
	@Test
	public void getNextDayTest() {
		try {
			Assert.assertEquals(DateUtil.getNextDay(1), 57600000);
		} catch (Exception e) {
			Assert.fail();
		}
	}

	/**
	 * 测试是否能获取指定时间的下一个周起点
	 */
	@Test
	public void getNextWeekTest() {
		try {
			Assert.assertEquals(DateUtil.getNextWeek(1), 230400000);
		} catch (Exception e) {
			Assert.fail();
		}
	}

	/**
	 * 测试是否能获取指定时间的下一个月起点
	 */
	@Test
	public void getNextMonthTest() {
		try {
			Assert.assertEquals(DateUtil.getNextMonth(1), (long) 736 * 60 * 60 * 1000);
		} catch (Exception e) {
			Assert.fail();
		}
	}

}
