package com.primeton.pub.common.util;

import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

/**2014-7-11
 * @author Administrator
 *属性文件工具类测试类
 *
 */
public class PropertiesUtilTest {
	
	
	
	/**
	 * 测试能否根据属性文件中的key得到相应的value值
	 */
	@Test
	public void getValueTest(){
		String str;
		try {
			str = PropertiesUtil.getValue("prop.properties", "aaa");
			Assert.assertEquals(str, "111");
		} catch (Exception e) {
			Assert.fail();
		}
			
	}
	
	/**
	 * 测试能否根据属性文件的名称得到属性对象
	 */
	@Test
	public void getPropTest() {
		Properties prop;
		try {
			prop = PropertiesUtil.getProp("prop.properties");
			Assert.assertEquals(prop.toString(), "{bbb=222, aaa=111, ccc=333}");
			try{
				PropertiesUtil.getProp(null);
			}catch(Exception e){
//				Assert.assertEquals(e.toString(), "java.lang.NullPointerException");
				Assert.assertTrue(e instanceof NullPointerException);
			}
		} catch (Exception e1) {
			Assert.fail();
		}
		
	}
	
	
	/**
	 * 测试能否根据属性文件名称装载属性文件
	 */
	@Test
	public void loadPropertiesTest() {
		try {
			PropertiesUtil.loadProperties(null);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof NullPointerException);}
		try {
			Properties prop = PropertiesUtil.loadProperties("prop.properties");
			Assert.assertEquals(prop.toString(), "{bbb=222, aaa=111, ccc=333}");
		} catch (Exception e) {
			Assert.assertTrue(e instanceof IllegalArgumentException);
		}
		try {
			Properties prop = PropertiesUtil.loadProperties("ss");
			Assert.assertEquals(prop.toString(), "{bbb=222, aaa=111, ccc=333}");
		} catch (Exception e) {
			Assert.assertTrue(e instanceof Exception);
		}
	}
	
}
