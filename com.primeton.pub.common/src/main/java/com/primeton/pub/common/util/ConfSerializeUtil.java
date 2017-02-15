package com.primeton.pub.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * 配置序列化工具
 * 
 * @author Administrator
 * 
 */
public class ConfSerializeUtil {
	private String path;

	/**
	 * 获取Jar所在目录
	 */
	public ConfSerializeUtil() {
		// path =
		// getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		path = this.getClass().getClassLoader().getResource("").getPath();
	}

	/**
	 * 把map写入到文件中
	 * 
	 * @param filename
	 * @param conf
	 */
	public void writeConfig(String filename, Map<String, String> conf) {
		String file = path + filename;
		try {
			Properties prop = new Properties();// 属性集合对象
			Iterator<String> keys = conf.keySet().iterator();
			while (keys.hasNext()) {
				String key = keys.next();// key
				String value = conf.get(key);// 上面key对应的value
				prop.setProperty(key, value);
			}
			// 文件输出流
			FileOutputStream fos = new FileOutputStream(file);
			// 将Properties集合保存到流中
			prop.store(fos, "Copyright (c) Primeton Open Platform");
			fos.close();// 关闭流
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String read(String filename) {
		String file = path + filename;
		if (!this.hasConfigFile(filename)) {
			return null;
		}
		try {
			FileInputStream fis = new FileInputStream(file);
			BufferedReader rd = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
			String tempLine = rd.readLine();
			StringBuffer tempStr = new StringBuffer();
			while (tempLine != null) {
				tempStr.append(tempLine);
				tempStr.append("\n");
				tempLine = rd.readLine();
			}
			String responseContent = tempStr.toString();
			rd.close();
			fis.close();
			return responseContent;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 从文件中读取配置
	 * 
	 * @param filename
	 * @return
	 */
	public Map<String, String> readConfig(String filename) {
		String file = path + filename;
		if (!this.hasConfigFile(filename)) {
			return null;
		}

		Map<String, String> retMap = new HashMap<String, String>();
		try {
			Properties prop = new Properties();// 属性集合对象
			FileInputStream fis = new FileInputStream(file);// 属性文件流
			prop.load(fis);// 将属性文件流装载到Properties对象中
			Iterator<Object> keys = prop.keySet().iterator();
			while (keys.hasNext()) {
				String key = (String) keys.next();// key
				String value = (String) prop.get(key);// 上面key对应的value
				retMap.put(key, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retMap;
	}

	/**
	 * 从绝对路径文件中读取配置
	 * 
	 * @param filename
	 * @return
	 */
	public Map<String, String> readConfig(String path, String filename) {
		String file = path + filename;
		if (!this.hasConfigFile(filename)) {
			return null;
		}

		Map<String, String> retMap = new HashMap<String, String>();
		try {
			Properties prop = new Properties();// 属性集合对象
			FileInputStream fis = new FileInputStream(file);// 属性文件流
			prop.load(fis);// 将属性文件流装载到Properties对象中
			Iterator<Object> keys = prop.keySet().iterator();
			while (keys.hasNext()) {
				String key = (String) keys.next();// key
				String value = (String) prop.get(key);// 上面key对应的value
				retMap.put(key, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retMap;
	}

	/**
	 * 判断配置文件是否存在
	 * 
	 * @param filepath
	 * @return
	 */
	public boolean hasConfigFile(String filename) {
		File file = new File(path + filename);
		return file.exists();
	}

	/**
	 * 绝对路径中判断配置文件是否存在
	 * 
	 * @param filepath
	 * @return
	 */
	public boolean hasConfigFile(String path, String filename) {
		File file = new File(path + filename);
		return file.exists();
	}

}
