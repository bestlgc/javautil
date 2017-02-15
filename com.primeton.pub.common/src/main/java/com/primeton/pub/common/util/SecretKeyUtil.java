package com.primeton.pub.common.util;

import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * @author shencheng
 * 
 */

public class SecretKeyUtil {

	public static final String DE_SEDE = "DESede";
	public static final String HMAC_MD5 = "HmacMD5";
	public static final String MD5 = "MD5";

	/**
	 * 根据密钥类型生成密钥
	 * 
	 * @param KeyType
	 *            密钥类型
	 * @return Key 生成的密钥
	 */
	private static SecretKey generateKey(String KeyType) throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(KeyType);
		SecretKey secretKey = keyGenerator.generateKey();
		return secretKey;
	}

	/**
	 * 根据密钥长度，类型生成密钥
	 * 
	 * @param length
	 *            密钥长度
	 * @param KeyType
	 *            密钥类型
	 * @return Key 生成密钥
	 */
	private static SecretKey generateKey(int length, String KeyType) throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(KeyType);
		keyGenerator.init(length);
		SecretKey myKey = keyGenerator.generateKey(); // 生成密钥
		return myKey;
	}

	/**
	 * 生成3DES 密钥
	 * 
	 * @param length
	 *            密钥长度
	 * @return String 生成密钥
	 */
	public static String generate3DESSecretKey(int length) throws NoSuchAlgorithmException {
		if (length == 112 || length == 168) {
			Key myKey = generateKey(length, DE_SEDE); // 生成密钥
			return Base64Util.encodeString(myKey.getEncoded());
		} else {
			return null;
		}

	}

	/**
	 * 生成3DES密钥
	 * 
	 * @param length
	 *            密钥长度
	 * @return byte[] 生成密钥
	 */
	public static byte[] generate3DESSecretKeyByte(int length) throws NoSuchAlgorithmException {
		if (length == 112 || length == 168) {
			Key myKey = generateKey(length, DE_SEDE); // 生成密钥
			return myKey.getEncoded();
		} else {
			return null;
		}
	}

	/**
	 * 生成HMAC密钥
	 * 
	 * @param length
	 *            密钥长度
	 * @return String 生成密钥
	 */
	public static String generateHMACSecretKeyString() throws NoSuchAlgorithmException {
		Key myKey = generateKey(HMAC_MD5); // 生成密钥
		return myKey.getEncoded().toString();
	}

	/**
	 * 生成HMAC密钥
	 * 
	 * @param length
	 *            密钥长度
	 * @return byte[] 生成密钥
	 */
	public static byte[] generateHMACSecretKeyByte() throws NoSuchAlgorithmException {
		Key myKey = generateKey(HMAC_MD5); // 生成密钥
		return myKey.getEncoded();
	}
	
	/**
	 * 根据类型和长度生成密钥
	 * @param keyType
	 * @param length（部分算法可填null）
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] generateSecretKeyByte(String keyType, Integer length) throws NoSuchAlgorithmException {
		if (keyType == HMAC_MD5) {
			return generateHMACSecretKeyByte();
		} else if(keyType == DE_SEDE) {
			return generate3DESSecretKeyByte(length);
		}
		return null;
	}
}
