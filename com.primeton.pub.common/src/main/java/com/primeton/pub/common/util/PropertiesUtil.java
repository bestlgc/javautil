package com.primeton.pub.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * 属性文件工具类
 * 
 * @author Administrator
 * 
 */
public class PropertiesUtil {

	/**
	 * 根据属性文件中的key得到相应的value值
	 * 
	 * @param propertiesName
	 * @param key
	 * @return
	 * @throws Exception 
	 */
	public static String getValue(String propertiesName, String key) throws Exception {
		Properties prop = getProp(propertiesName);
		return prop.getProperty(key);
	}

	/**
	 * 根据属性文件的名称得到属性对象
	 * 
	 * @param propName
	 * @return
	 * @throws Exception 
	 */
	public static Properties getProp(String propName) throws Exception {
		Properties prop = new Properties();
		// ClassLoader cl = PropertiesUtil.class.getClassLoader();
		try {
			prop = loadProperties(propName);
			if (prop == null) {
				throw new RuntimeException("Unable to load the configuration file");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

	/**
	 * 根据属性文件名称装载属性文件
	 * 
	 * @param cl
	 * @param name
	 * @return
	 * @throws Exception 
	 */
	public static Properties loadProperties(String name) throws Exception {
		InputStream is = null;
		Properties prop = null;
		try {
			if (name.endsWith(".properties")) {
				if (!name.startsWith("/")) {
					name = "/" + name;
				}
				URL url = PropertiesUtil.class.getResource(name);
				// URL url=null;
				if (url != null) {
					is = url.openStream();
					prop = new Properties();
					prop.load(is);
				} else {
					throw new IllegalArgumentException("Could not load properties from " + name + ": " + "not properties file");
				}
			} else {
				throw new Exception("Could not load properties from " + name + ": " + "not properties file");
			}
		} finally {
			if (is != null) {
				is.close();
			}
		}
		return prop;
	}
}
