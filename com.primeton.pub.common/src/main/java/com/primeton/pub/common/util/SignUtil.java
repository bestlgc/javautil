package com.primeton.pub.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * 报文签名工具类
 * 
 * @author yueyw
 * 
 */
public class SignUtil {

	private static final String ALGORITHM_MD5 = "md5";

	/**
	 * @param keyStr
	 * @param message
	 * @param strEncrypt
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] signByHmac(byte[] key, String message) throws NoSuchAlgorithmException {

		// 加密用散列函数的分割数据块字长
		int length = 64;

		byte[] ipad = new byte[length];
		byte[] opad = new byte[length];
		for (int i = 0; i < 64; i++) {
			ipad[i] = 0x36;
			opad[i] = 0x5C;
		}
		byte[] data = message.getBytes();
		// Hash算法的密文
		byte[] actualKey = key;

		byte[] keyArr = new byte[length]; // Key bytes of 64 bytes length

		// 如果密钥长度，大于64字节，就使用哈希算法，计算其摘要，作为真正的密钥。
		if (key.length > length) {
			actualKey = encryptByMD5(key);
		}
		for (int i = 0; i < actualKey.length; i++) {
			keyArr[i] = actualKey[i];
		}

		// 如果密钥长度不足64字节，就使用0x00补齐到64字节。
		if (actualKey.length < length) {
			for (int i = actualKey.length; i < keyArr.length; i++)
				keyArr[i] = 0x00;
		}

		// 使用密钥和ipad进行异或运算。
		byte[] kIpadXorResult = new byte[length];
		for (int i = 0; i < length; i++) {
			kIpadXorResult[i] = (byte) (keyArr[i] ^ ipad[i]);
		}

		// 将待加密数据追加到K XOR ipad计算结果后面。
		byte[] firstAppendResult = new byte[kIpadXorResult.length + data.length];
		for (int i = 0; i < kIpadXorResult.length; i++) {
			firstAppendResult[i] = kIpadXorResult[i];
		}

		for (int i = 0; i < data.length; i++) {
			firstAppendResult[i + keyArr.length] = data[i];
		}

		// 使用哈希算法计算上面结果的摘要。
		byte[] firstHashResult = encryptByMD5(firstAppendResult);

		// 使用密钥和opad进行异或运算。
		byte[] kOpadXorResult = new byte[length];
		for (int i = 0; i < length; i++) {
			kOpadXorResult[i] = (byte) (keyArr[i] ^ opad[i]);
		}

		// 将H(K XOR ipad, text)结果追加到K XOR opad结果后面

		byte[] secondAppendResult = new byte[kOpadXorResult.length + firstHashResult.length];
		for (int i = 0; i < kOpadXorResult.length; i++) {
			secondAppendResult[i] = kOpadXorResult[i];
		}
		for (int i = 0; i < firstHashResult.length; i++) {
			secondAppendResult[i + keyArr.length] = firstHashResult[i];
		}

		// 对上面的数据进行哈希运算。
		byte[] hmacMd5Bytes = encryptByMD5(secondAppendResult);
		return hmacMd5Bytes;
	}

	/**
	 * 对指定数组利用MD5加密
	 * 
	 * @param str
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	private static byte[] encryptByMD5(byte[] str) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance(ALGORITHM_MD5);
		return md.digest(str);
	}

	public static String byte2hex(byte[] b) {
		StringBuilder hs = new StringBuilder();
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0XFF);
			if (stmp.length() == 1) {
				hs.append("0").append(stmp);
			} else {
				hs.append(stmp);
			}
		}
		return hs.toString().toUpperCase();
	}
}
