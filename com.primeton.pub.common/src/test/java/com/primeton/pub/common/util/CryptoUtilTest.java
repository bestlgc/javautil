package com.primeton.pub.common.util;

import org.junit.Assert;
import org.junit.Test;

public class CryptoUtilTest {

	/**
	 * 测试是否能加密
	 */
	@Test
	public void encryptTest() {
		try {
			CryptoUtil.encrypt(null, SecretKeyUtil.DE_SEDE, null);
		} catch (Exception e) {
			Assert.assertEquals(e.toString(), "java.lang.IllegalArgumentException: Encrypt dataString is null!");
		}

		try {
			Assert.assertNotNull(CryptoUtil.encrypt("test", SecretKeyUtil.DE_SEDE, null));
		} catch (Exception e) {
			Assert.fail();
		}
	}

	/**
	 * 测试是否能解密
	 */
	@Test
	public void decryptTest() {
		try {
			CryptoUtil.decrypt(null, SecretKeyUtil.DE_SEDE, null);
		} catch (Exception e) {
			Assert.assertEquals(e.toString(), "java.lang.IllegalArgumentException: Decrypt dataString is null!");
		}

		try {
			CryptoUtil.decrypt("test", SecretKeyUtil.DE_SEDE, null);
		} catch (Exception e) {
			Assert.assertEquals(e.toString(), "java.lang.RuntimeException: decrypt error!");
		}

		try {
			Assert.assertEquals(CryptoUtil.decrypt(CryptoUtil.encrypt("test", "DES", null), "DES", null),"test");
		} catch (Exception e) {
			Assert.fail();
		}
	}

	/**
	 * 测试是否能生成摘要
	 */
	@Test
	public void digestTest() {
		try {
			CryptoUtil.digest(null, SecretKeyUtil.MD5);
		} catch (Exception e) {
			Assert.assertEquals(e.toString(), "java.lang.IllegalArgumentException: Digest dataString is null!");
		}

		try {
			Assert.assertNotNull(CryptoUtil.digest("test", SecretKeyUtil.MD5));
		} catch (Exception e) {
			Assert.fail();
		}
	}

	/**
	 * 测试是否能验证摘要
	 */
	@Test
	public void verifyDigestTest() {
		try {
			CryptoUtil.verifyDigest(null, "test", SecretKeyUtil.MD5);
		} catch (Exception e) {
			Assert.assertEquals(e.toString(), "java.lang.IllegalArgumentException: Very Digest dataString is null!");
		}

		try {
			CryptoUtil.verifyDigest("test", null, SecretKeyUtil.MD5);
		} catch (Exception e) {
			Assert.assertEquals(e.toString(), "java.lang.IllegalArgumentException: Very Digest digestedString is null!");
		}

		try {
			Assert.assertEquals(CryptoUtil.verifyDigest("test", CryptoUtil.digest("test", SecretKeyUtil.MD5), SecretKeyUtil.MD5), true);
		} catch (Exception e) {
			Assert.fail();
		}
	}

	/**
	 * 测试是否能生成数字签名
	 */
	@Test
	public void signTest() {
		try {
			CryptoUtil.sign(null, "DSA");
		} catch (Exception e) {
			Assert.assertEquals(e.toString(), "java.lang.IllegalArgumentException: Signaturer dataString is null!");
		}

		try {
			Assert.assertNotNull(CryptoUtil.sign("test", "DSA"));
		} catch (Exception e) {
			Assert.fail();
		}
	}

	/**
	 * 测试是否能验证数字签名
	 */
	@Test
	public void verifySignTest() {
		try {
			CryptoUtil.verifySign(null, "test", "DSA");
		} catch (Exception e) {
			Assert.assertEquals(e.toString(), "java.lang.IllegalArgumentException: Signaturer dataString is null!");
		}

		try {
			CryptoUtil.verifySign("test", null, "DSA");
		} catch (Exception e) {
			Assert.assertEquals(e.toString(), "java.lang.IllegalArgumentException: Signaturer signedString is null!");
		}

		try {
			Assert.assertEquals(CryptoUtil.verifySign("test", CryptoUtil.sign("test", "DSA"), "DSA"), true);
		} catch (Exception e) {
			Assert.fail();
		}
	}
}
