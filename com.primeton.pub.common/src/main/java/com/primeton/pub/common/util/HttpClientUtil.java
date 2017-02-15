package com.primeton.pub.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

public final class HttpClientUtil {

	public static String doPost(String reqUrl, Map<String, String> parameters) {
		HttpURLConnection urlConn = null;
		try {
			urlConn = sendPost(reqUrl, parameters);
			String responseContent = getContent(urlConn);
			return responseContent.trim();
		} finally {
			if (urlConn != null) {
				urlConn.disconnect();
				urlConn = null;
			}
		}
	}
	
	
	public static String doPut(String reqUrl,byte[]data) {
		HttpURLConnection urlConn = null;
		try {
			urlConn = sendPut(reqUrl,data);
			String responseContent = getContent(urlConn);
			return responseContent.trim();
		} catch(Exception e){
			
		}finally {
			if (urlConn != null) {
				urlConn.disconnect();
				urlConn = null;
			}
		}
		return null;
	}
	
	public static HttpURLConnection sendPut(String reqUrl,byte[]data) throws Exception {
		HttpURLConnection urlConn = null;
		OutputStream urlOutputStream = null;
		try {
			URL url = new URL(reqUrl);
			urlConn = (HttpURLConnection) url.openConnection();
			urlConn.setDoInput(true);  
			urlConn.setDoOutput(true);  
			urlConn.setRequestMethod("PUT");  
	        urlOutputStream = urlConn.getOutputStream();  
	        urlOutputStream.write(data);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
			
		}finally{
			if (urlOutputStream !=null) {
				urlOutputStream.flush();
				urlOutputStream.close();
			}
		}
		return urlConn;
	}
	
	public static String doFilePost(String reqUrl,String name,byte[] data) {
		HttpURLConnection urlConn = null;
		try {
			urlConn = sendFilePost(reqUrl,name, data);
			String responseContent = getContent(urlConn);
			return responseContent.trim();
		} finally {
			if (urlConn != null) {
				urlConn.disconnect();
				urlConn = null;
			}
		}
	}
	
	public static HttpURLConnection sendFilePost(String reqUrl,String name,byte[] data) {
		HttpURLConnection urlConn = null;
		try {

			String params = name + "=";
			URL url = new URL(reqUrl);
			urlConn = (HttpURLConnection) url.openConnection();
			urlConn.setRequestMethod("POST");
			urlConn.setConnectTimeout(5000);// （单位：毫秒）jdk
			urlConn.setReadTimeout(5000);// （单位：毫秒）jdk 1.5换成这个,读操作超时
			urlConn.setDoOutput(true);
			byte[] b = params.getBytes("UTF-8");
			byte[] sum = byteMerger(b, data);
			urlConn.getOutputStream().write(sum, 0, sum.length);
			urlConn.getOutputStream().flush();
			urlConn.getOutputStream().close();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return urlConn;
	}
	private static String getContent(HttpURLConnection urlConn) {
		try {
			String responseContent = null;
			InputStream in = null;
			if (urlConn.getResponseCode() == 200) {
				in = urlConn.getInputStream();
			} else {
				in = urlConn.getErrorStream();
			}
			BufferedReader rd = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String tempLine = rd.readLine();
			StringBuffer tempStr = new StringBuffer();
			String crlf = System.getProperty("line.separator");
			while (tempLine != null) {
				tempStr.append(tempLine);
				tempStr.append(crlf);
				tempLine = rd.readLine();
			}
			responseContent = tempStr.toString();
			rd.close();
			in.close();
			return responseContent;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static byte[] getBytes(String reqUrl) {
		return getBytes(reqUrl, null);
	}

	public static byte[] getBytes(String reqUrl, Map<String, String> parameters) {
		HttpURLConnection conn = sendGet(reqUrl, parameters);
		return getBytes(conn);
	}

	private static byte[] getBytes(HttpURLConnection urlConn) {
		try {
			InputStream in = urlConn.getInputStream();
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			for (int i = 0; (i = in.read(buf)) > 0;)
				os.write(buf, 0, i);
			in.close();
			return os.toByteArray();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	private static HttpURLConnection sendPost(String reqUrl, Map<String, String> parameters) {
		HttpURLConnection urlConn = null;
		try {

			String params = generatorParamString(parameters);
			URL url = new URL(reqUrl);
			urlConn = (HttpURLConnection) url.openConnection();
			urlConn.setRequestMethod("POST");
			urlConn.setConnectTimeout(5000);// （单位：毫秒）jdk
			urlConn.setReadTimeout(5000);// （单位：毫秒）jdk 1.5换成这个,读操作超时
			urlConn.setDoOutput(true);
			byte[] b = params.getBytes("UTF-8");
			urlConn.getOutputStream().write(b, 0, b.length);
			urlConn.getOutputStream().flush();
			urlConn.getOutputStream().close();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return urlConn;
	}

	private static HttpURLConnection sendGet(String reqUrl, Map<String, String> parameters) {
		HttpURLConnection urlConn = null;
		try {

			String params = generatorParamString(parameters);
			URL url = new URL(reqUrl);
			urlConn = (HttpURLConnection) url.openConnection();
			urlConn.setRequestMethod("GET");
			// urlConn
			// .setRequestProperty(
			// "User-Agent",
			// "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.3) Gecko/20100401 Firefox/3.6.3");
			urlConn.setConnectTimeout(5000);// （单位：毫秒）jdk
			urlConn.setReadTimeout(5000);// （单位：毫秒）jdk 1.5换成这个,读操作超时
			urlConn.setDoOutput(true);
			if (params != null && !"".equals(params)) {
				byte[] b = params.getBytes();
				urlConn.getOutputStream().write(b, 0, b.length);
				urlConn.getOutputStream().flush();
				urlConn.getOutputStream().close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return urlConn;
	}

	/**
	 * 将parameters中数据转换成用"&"链接的http请求参数形式
	 * 
	 * @param parameters
	 * @return
	 */
	public static String generatorParamString(Map<String, String> parameters) {
		StringBuffer params = new StringBuffer();
		if (parameters != null) {
			for (Iterator<String> iter = parameters.keySet().iterator(); iter.hasNext();) {
				String name = iter.next();
				String value = parameters.get(name);
				params.append(name + "=");
				try {
					params.append(URLEncoder.encode(value, "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					throw new RuntimeException(e.getMessage(), e);
				} catch (Exception e) {
					String message = String.format("'%s'='%s'", name, value);
					throw new RuntimeException(message, e);
				}
				if (iter.hasNext())
					params.append("&");
			}
		}
		return params.toString();
	}

	static long abc = 0L;

	/**
	 * 
	 * @param link
	 * @param charset
	 * @return
	 */
	public static String doGet(String link, String charset) {
		HttpURLConnection conn = null;
		try {
			URL url = new URL(link);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			BufferedInputStream in = new BufferedInputStream(conn.getInputStream());
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			for (int i = 0; (i = in.read(buf)) > 0;) {
				out.write(buf, 0, i);
			}
			in.close();
			out.flush();
			out.close();
			String s = new String(out.toByteArray(), charset);
			return s;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
	}
	
	public static byte[] doFileGet (String link) {
		HttpURLConnection conn = null;
		byte[] result = null;
		try {
			URL url = new URL(link);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			BufferedInputStream in = new BufferedInputStream(conn.getInputStream());
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			for (int i = 0;(i = in.read(buf)) > 0 ;) {
				out.write(buf, 0, i);
			}
			out.flush();
			result = out.toByteArray();
			return result;
		} catch (Exception e) {
			return null;
		}finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
	}
	/**
	 * UTF-8编码
	 * 
	 * @param link
	 * @return
	 */
	public static String doGet(String link) {
		return doGet(link, "UTF-8");
	}

	public static int getIntResponse(String link) {
		String str = doGet(link);
		return Integer.parseInt(str.trim());
	}
	
	 public static byte[] byteMerger(byte[] byte_1, byte[] byte_2){  
	        byte[] byte_3 = new byte[byte_1.length+byte_2.length];  
	        System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);  
	        System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);  
	        return byte_3;  
	    }  
}