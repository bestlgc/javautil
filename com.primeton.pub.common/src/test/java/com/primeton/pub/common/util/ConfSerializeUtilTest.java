package com.primeton.pub.common.util;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class ConfSerializeUtilTest {

	/**
	 * 测试是否能把map写入到文件中
	 */
	@Test
	public void writeConfigTest() {
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("a", "1");
			new ConfSerializeUtil().writeConfig("testText.txt", map);
		} catch (Exception e) {
			Assert.fail();
		}
	}

	/**
	 * 测试是否能从文件中读取配置
	 */
	@Test
	public void readConfigTest() {
		try {
			Map<String, String> retMap = new HashMap<String, String>();
			retMap.put("a", "1");
			Assert.assertEquals(new ConfSerializeUtil().readConfig("testText.txt"), retMap);
		} catch (Exception e) {
			Assert.fail();
		}
	}

	/**
	 * 测试是否能判断配置文件是否存在
	 */
	@Test
	public void hasConfigFileTest() {
		try {
			Assert.assertTrue(new ConfSerializeUtil().hasConfigFile("testText.txt"));
		} catch (Exception e) {
			Assert.fail();
		}
	}

}
