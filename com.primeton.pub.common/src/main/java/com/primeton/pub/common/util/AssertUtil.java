package com.primeton.pub.common.util;

import java.util.Date;

public class AssertUtil {

	/**
	 * 断言单个对象不为空
	 * 
	 * @param object
	 * @param name
	 */
	public static void assertObjectNotNull(Object object, String name) {
		if (null == object) {
			throw new IllegalArgumentException("Argument[" + name + "] is null");
		} else if (object instanceof String && "".equals(object)) {
			throw new IllegalArgumentException("String[" + name + "] is empty");
		}
	}

	/**
	 * 断言参数不为null
	 * 
	 * @param objects
	 * @throws QuotaException
	 */
	public static void assertNotNull(Object... objects) {
		if (null == objects) {
			throw new IllegalArgumentException("Argument can not be null");
		} else {
			for (Object obj : objects) {
				if (null == obj) {
					throw new IllegalArgumentException("Argument can not be null");
				} else if (obj instanceof String && "".equals(obj)) {
					throw new IllegalArgumentException("String can not be empty");
				}
			}
		}
	}

	/**
	 * 断言前一个时间小于后一个时间
	 * 
	 * @param date1
	 *            时间1
	 * @param date2
	 *            时间2
	 * @throws QuotaException
	 */
	public static void assertLessThen(Date date1, Date date2) {
		assertNotNull(date1, date2);
		if (date1.after(date2)) {
			throw new IllegalArgumentException("date1 cannot be greater than date2");
		}
	}

	/**
	 * 断言参数为非负数（含0）
	 * 
	 * @param nums
	 */
	public static void assertNotNegative(Number... nums) {
		if (null == nums) {
			throw new IllegalArgumentException("Argument can not be null");
		} else {
			for (Number num : nums) {
				if (num.longValue() < 0) {
					throw new IllegalArgumentException("Number can't less than zero,value is " + num + "");
				}
			}
		}
	}

	/**
	 * 断言参数是否为非负数
	 * 
	 * @param nums
	 */
	public static void assertNotNegative(Number num, String name) {
		if (null == num) {
			throw new IllegalArgumentException("Argument[" + name + "] is null");
		} else {
			if (num.longValue() < 0) {
				throw new IllegalArgumentException("Number " + name + " can't less than zero,value is " + num + "");
			}
		}
	}

	/**
	 * 断言参数为正数
	 * 
	 * @param nums
	 */
	public static void assertPositive(Number... nums) {
		if (null == nums) {
			throw new IllegalArgumentException("Argument can not be null");
		} else {
			for (Number num : nums) {
				if (num.longValue() <= 0) {
					throw new IllegalArgumentException("Number must greater than zero,value is " + num + "");
				}
			}
		}
	}

	/**
	 * 断言参数为正数
	 * 
	 * @param num
	 * @param name
	 */
	public static void assertPositive(Number num, String name) {
		if (null == num) {
			throw new IllegalArgumentException("Argument[" + name + "] is null");
		} else {
			if (num.longValue() <= 0) {
				throw new IllegalArgumentException("Number " + name + " must greater than zero,value is " + num + "");
			}
		}
	}

	/**
	 * 断言两个数值不相等
	 * 
	 * @param num1
	 * @param num2
	 */
	public static void assertNotEquals(Number num1, Number num2) {
		if (num1 == num2) {
			throw new IllegalArgumentException("Argument[" + num1 + "] == [" + num2 + "]");
		}
	}

}
