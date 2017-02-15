package com.primeton.pub.common.util;

import org.junit.Assert;
import org.junit.Test;

public class ReflectUtilTest {

	/**
	 *测试是否能利用反射获取指定对象的指定属性
	 */
	@Test
	public void getFieldValueTest() {
		try {
			Assert.assertEquals(ReflectUtil.getFieldValue(new Object(), null),null);
		} catch (Exception e) {
			Assert.fail();
		}
		
		try {
			Assert.assertEquals(ReflectUtil.getFieldValue(new TestClass() ,"str"),"primeton");
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
	@Test
	public void setFieldValueTest(){
		//属性值是null,抛出参数异常
		/*try {
			ReflectUtil.setFieldValue(new TestClass(), "notExist", "primeton");
		} catch (Exception e) {
			Assert.assertTrue(e instanceof IllegalArgumentException);
		}
		//输入对象是null,抛出参数异常
		try {
			ReflectUtil.setFieldValue(null, "str", "primeton");
			} catch (Exception e) {
			Assert.assertTrue(e instanceof IllegalArgumentException);
			}*/
		//输入正确参数，执行成功
		try {
			ReflectUtil.setFieldValue(new TestClass(), "str", "primeton");
		} catch (Exception e) {
			Assert.fail();
		}
	}
}


class TestClass{
	private String str = "primeton" ;
	public String getStr() {
		return str;
	}
} 