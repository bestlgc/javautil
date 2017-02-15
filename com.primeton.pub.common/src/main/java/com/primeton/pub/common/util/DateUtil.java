package com.primeton.pub.common.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static Date getMaxExpireDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2099);
		calendar.set(Calendar.MONTH, 11);
		calendar.set(Calendar.DATE, 31);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * 判断当前时间是否在指定的时间段内
	 * 
	 * @param currentTime
	 * @param beforeTime
	 * @param afterTime
	 * @return
	 */
	public static boolean between(long currentTime, long beforeTime, long afterTime) {
		if (currentTime >= beforeTime && currentTime < afterTime) {
			return true;
		}
		return false;
	}

	/**
	 * 获取指定时间的下一个秒起点
	 * 
	 * @param cur
	 * @return
	 */
	public static long getNextSecond(long cur) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(cur);
		cal.add(Calendar.SECOND, 1);
		cal.set(Calendar.MILLISECOND, 0);
		long expire = cal.getTimeInMillis();
		return expire;
	}

	/**
	 * 获取指定时间的下一个分起点
	 * 
	 * @param cur
	 * @return
	 */
	public static long getNextMinute(long cur) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(cur);
		cal.add(Calendar.MINUTE, 1);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		long expire = cal.getTimeInMillis();
		return expire;
	}

	/**
	 * 获取指定时间的下一个小时起点
	 * 
	 * @param cur
	 * @return
	 */
	public static long getNextHour(long cur) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(cur);
		cal.add(Calendar.HOUR_OF_DAY, 1);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		long expire = cal.getTimeInMillis();
		return expire;
	}

	/**
	 * 获取指定时间的下一个天起点
	 * 
	 * @param cur
	 * @return
	 */
	public static long getNextDay(long cur) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(cur);
		cal.add(Calendar.DAY_OF_YEAR, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		long expire = cal.getTimeInMillis();
		return expire;
	}

	/**
	 * 获取指定时间的下一个周起点
	 * 
	 * @param cur
	 * @return
	 */
	public static long getNextWeek(long cur) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(cur);
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		long expire = cal.getTimeInMillis();
		return expire;
	}

	/**
	 * 获取指定时间的下一个月起点
	 * 
	 * @param cur
	 * @return
	 */
	public static long getNextMonth(long cur) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(cur);
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		long expire = cal.getTimeInMillis();
		return expire;
	}
}
