package com.primeton.pub.common.util;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author liuguocai
 * 
 */
public class IOUtilTest {

	private String path = this.getClass().getClassLoader().getResource("").getPath();

	private class Close {
	}

	@Test
	public void closeQuietly() {
		try {
			IOUtil.closeQuietly(new Close());
		} catch (Exception e) {
		}

		try {
			IOUtil.closeQuietly(new Closeable() {
				public void close() throws IOException {
				}
			});
		} catch (Exception e) {
			Assert.fail();
		}

		try {
			IOUtil.closeQuietly(null);
		} catch (Exception e) {
		}

		try {
			IOUtil.closeQuietly(new Object());
		} catch (Exception e) {
		}
	}

	/**
	 * 测试判断绝对路径函数 是windows环境下的路径
	 */
	@Test
	public void isAbsolutePathTest() {
		try {
			IOUtil.isAbsolutePath(null);
		} catch (Exception e) {

		}
		try {
			IOUtil.isAbsolutePath("");
		} catch (Exception e) {

		}

		Assert.assertFalse(IOUtil.isAbsolutePath("primeton/test"));

		Assert.assertTrue(IOUtil.isAbsolutePath("E:/primeton/test"));

	}

	/**
	 * 测试获取文件名 false表示有扩展名
	 */
	@Test
	public void getFileNameTest() {
		try {
			IOUtil.getFileName(null, false);
		} catch (Exception e) {

		}
		try {
			IOUtil.getFileName("", false);
		} catch (Exception e) {

		}

		try {
			IOUtil.getFileName(null, true);
		} catch (Exception e) {

		}
		try {
			IOUtil.getFileName("", true);
		} catch (Exception e) {

		}
		/*
		 * 测试有扩展名的
		 */
		Assert.assertEquals("test", IOUtil.getFileName("/primeton/test.jsp", false));

		Assert.assertEquals("test", IOUtil.getFileName("/primeton/test", false));

		/*
		 * 测试没有扩展名的
		 */
		Assert.assertEquals("test", IOUtil.getFileName("test", true));

		Assert.assertEquals("test", IOUtil.getFileName("/primeton/test", true));

	}

	/**
	 * 测试获取扩展名
	 */
	@Test
	public void getFileExtensionTest() {

		try {
			IOUtil.getFileExtension(null);
		} catch (Exception e) {
			Assert.fail();
		}

		try {
			IOUtil.getFileExtension("");
		} catch (Exception e) {
			Assert.fail();
		}

		Assert.assertEquals("jsp", IOUtil.getFileExtension("test.jsp"));

		Assert.assertEquals(null, IOUtil.getFileExtension("test"));
	}

	@Test
	public void isMatchTest() {
		// test the path is null or empty
		try {
			IOUtil.isMatch(null, null, true);
		} catch (Exception e) {
		}

		try {
			IOUtil.isMatch(null, null, false);
		} catch (Exception e) {
		}

		try {
			IOUtil.isMatch("", "", true);
		} catch (Exception e) {
		}

		try {
			IOUtil.isMatch("", "", false);
		} catch (Exception e) {
		}

		// test the character is case sensitive
		Assert.assertFalse(IOUtil.isMatch("ab", "abc", true));
		Assert.assertFalse(IOUtil.isMatch("ab", "aBc", true));
		Assert.assertTrue(IOUtil.isMatch("abc", "abc", true));
		Assert.assertFalse(IOUtil.isMatch("?abc", "abc", true));
		// test wheather there is '*'
		Assert.assertTrue(IOUtil.isMatch("abc*", "abc", true));

		// test the character is not case sensitive
		Assert.assertFalse(IOUtil.isMatch("ab", "aBc", false));
		Assert.assertTrue(IOUtil.isMatch("abc", "aBc", false));
		Assert.assertFalse(IOUtil.isMatch("?abc", "abc", false));
		// test if the path has character '*'

		Assert.assertTrue(IOUtil.isMatch("aBc*", "abc", false));

	}

	/**
	 * Test the getRelativePathTest() method
	 */

	@Test
	public void getRelativePathTest() {
		try {
			IOUtil.getRelativePath(null, null);
		} catch (Exception e) {

		}
		try {
			IOUtil.getRelativePath("", "");
		} catch (Exception e) {

		}
		// judge the source path is not started with root path
		try {
			IOUtil.getRelativePath("/ptimeton", "/primeton/test");
		} catch (Exception e) {
		}
		try {
			IOUtil.getRelativePath("/primeton/test", "test");
		} catch (Exception e) {
		}
		try {
			Assert.assertEquals("long", IOUtil.getRelativePath("test", "testlong"));
		} catch (Exception e) {
			Assert.fail();
		}
		Assert.assertEquals("test", IOUtil.getRelativePath("/primeton", "/primeton/test"));

	}

	/**
	 * test the function normalizeInUnixStyle
	 */
	@Test
	public void normalizeInUnixStyleTest() {
		try {
			IOUtil.normalizeInUnixStyle(null);
		} catch (Exception e) {

		}
		try {
			IOUtil.normalizeInUnixStyle("");
		} catch (Exception e) {

		}

		Assert.assertEquals("com/primeton/test", IOUtil.normalizeInUnixStyle("com//primeton/test"));
		Assert.assertEquals("com/primeton/test", IOUtil.normalizeInUnixStyle("com//primeton\\test\\"));
		Assert.assertEquals("com/primeton", IOUtil.normalizeInUnixStyle("com/primeton/jnlp/../"));
		Assert.assertEquals(".", IOUtil.normalizeInUnixStyle("./"));
		Assert.assertEquals(".", IOUtil.normalizeInUnixStyle("./../"));
	}

	@Test
	public void toPathTokensTest() {
		Assert.assertTrue(IOUtil.deleteQuietly(null, false));

		Assert.assertTrue(IOUtil.deleteQuietly(new String(), false));

		Assert.assertTrue(IOUtil.deleteQuietly(new File(new String("test")), false));

		Assert.assertTrue(IOUtil.deleteQuietly(new File(new String("")), false));

		try {
			Assert.assertTrue(IOUtil.deleteQuietly(new Object(), false));
		} catch (Exception e) {
		}

		Assert.assertTrue(IOUtil.deleteQuietly(new File(new String("test")), true));
	}

	/**
	 * test if the function listFile()
	 */
	@Test
	public void listFilesTest() {
		try {
			IOUtil.listFiles(null, null);
		} catch (Exception e) {

		}

		try {
			IOUtil.listFiles(new File(new String()), null);
		} catch (Exception e) {
		}
		List<File> fileList = new ArrayList<File>();
		// 当前resource下创建文件
		String resourcePath = IOUtilTest.class.getClassLoader().getResource("").getPath();
		String dirPath = resourcePath + "test";
		String fileAPath = dirPath + "/fileA";
		String fileBPath = dirPath + "/fileB";

		File dir = new File(dirPath);
		File fileA = new File(fileAPath);
		File fileB = new File(fileBPath);

		dir.mkdirs();
		try {
			fileA.createNewFile();
		} catch (IOException e) {
			Assert.fail();
		}
		try {
			fileB.createNewFile();
		} catch (IOException e) {
			Assert.fail();
		}

		fileList.add(fileA);
		fileList.add(fileB);
		Assert.assertEquals(fileList, IOUtil.listFiles(dir, null));
	}

	/**
	 * test function copy()
	 * 
	 * @throws FileNotFoundException
	 */

	@Test
	public void streamCopyTest() throws FileNotFoundException {
		// 输入流为空
		try {
			File file = new File(path + "tests.txt");
			file.createNewFile();
			IOUtil.copy(null, new FileOutputStream(new File("tests.txt")), 0, 0);
			file.delete();
		} catch (Exception e) {
			Assert.assertEquals("java.lang.IllegalArgumentException: InputStream is null!", e.toString());
		}
		// 输出流为空
		try {
			File file = new File(path + "tests.txt");
			file.createNewFile();
			IOUtil.copy(new FileInputStream(file), null, 0, 0);
			file.delete();
		} catch (Exception e) {
			Assert.assertEquals("java.lang.IllegalArgumentException: OutputStream is null!", e.toString());
		}

		// buffersize为20,函数执行成功。
		try {
			File file = new File(path + "sourceTest.txt");
			file.createNewFile();
			InputStream inputTest = new FileInputStream(path + "sourceTest.txt");
			OutputStream outputTest = new FileOutputStream(path + "destTest.txt");
			IOUtil.copy(inputTest, outputTest, 20, 100);
			file.delete();
		} catch (Exception e) {
			Assert.fail("copy failed");
		}
		// buffersize为-1,函数执行成功
		try {
			File file = new File(path + "sourceTest.txt");
			InputStream inputTest = new FileInputStream(path + "sourceTest.txt");
			OutputStream outputTest = new FileOutputStream(path + "destTest.txt");
			IOUtil.copy(inputTest, outputTest, -1, 100);
			file.delete();
		} catch (Exception e) {
			Assert.fail("copy failed");
		}

		try {
			File file = new File(path + "sourceTest.txt");
			InputStream inputTest = new FileInputStream(path + "sourceTest.txt");
			OutputStream outputTest = new FileOutputStream(path + "destTest.txt");
			IOUtil.copy(inputTest, outputTest, -1, -1);
			file.delete();
		} catch (IOException e) {
			Assert.fail("copy failed");
		}

	}

	/**
	 * test the function copy by path
	 */
	@Test
	public void pathCopyTest() {
		// 源文件为null
		try {
			IOUtil.copy(null, new File(path + "testText.txt"), null, false);
		} catch (Exception e) {
			Assert.assertEquals("java.lang.IllegalArgumentException: srcPath is null!", e.toString());
		}
		// 目标文件为null
		try {
			File file = new File(path + "sourceTest.txt");
			file.createNewFile();
			IOUtil.copy(file, null, null, false);
			file.delete();
		} catch (Exception e) {
			Assert.assertEquals("java.lang.IllegalArgumentException: destPath is null!", e.toString());
		}
		// 源文件是目录，抛异常
		try {
			IOUtil.copy(new File(path + "primeton"), new File(path + "sourceTest.txt"), null, true);
		} catch (Exception e) {
		}
		// 拷贝源文件到目录成功
		try {
			IOUtil.copy(new File(path + "sourceTest.txt"), new File(path + "primeton"), null, true);
		} catch (Exception e) {
			Assert.fail();
		}
		// 拷贝源文件到另一个文件
		try {
			IOUtil.copy(new File(path + "sourceTest.txt"), new File(path + "destTest.txt"), null, true);
		} catch (Exception e) {
			Assert.fail();
		}
		// 拷贝源文件到与源文件名相同的文件
		try {
			IOUtil.copy(new File(path + "sourceTest.txt"), new File(path + "sourceTest.txt"), null, true);
		} catch (Exception e) {
			Assert.fail();
		}
		// 将目录拷贝到文件，抛出异常
		try {
			IOUtil.copy(new File(path + "/primeton"), new File(path + "/primeton/sourceTest.txt"), null, true);
		} catch (Exception e) {

		}
	}

	/**
	 * test the function move by path
	 */
	@Test
	public void moveTest() {
		// 源文件为null，抛出参数异常
		try {
			IOUtil.move(null, new File(path + "primeton"), null);
		} catch (Exception e) {
			Assert.assertEquals("srcPath is null!", e.getMessage());
		}
		// 目标文件为null，抛出参数异常
		try {
			IOUtil.move(new File(path + "/primeton/sourceTest.txt"), null, null);
		} catch (Exception e) {
			Assert.assertEquals("destPath is null!", e.getMessage());
		}
		// 源文件不存在，抛出参数异常
		try {
			IOUtil.move(new File(path + "not exit"), new File(path + "sourceTest.txt"), null);
		} catch (Exception e) {

		}
		// 将文件放到目录,执行正确
		try {
			File file = new File(path + "sourceTest.txt");
			file.createNewFile();
			IOUtil.move(file, new File(path + "primeton"), null);
			file.delete();
		} catch (Exception e) {
			Assert.fail();
		}
		// 将文件move到目标文件，执行成功
		try {
			File file = new File(path + "testTextA.txt");
			file.createNewFile();
			IOUtil.move(file, new File(path + "destTest.txt"), null);
			file.delete();

		} catch (Exception e) {
			Assert.fail();
		}
		// 抛出异常
		try {
			IOUtil.copy(null, new File("test"), null, true);
		} catch (Exception e) {
			// System.out.println(e.toString());
			Assert.assertEquals(e.toString(), "java.lang.IllegalArgumentException: srcPath is null!");
		}
		// 将文件move到不存在的文件，执行成功，创建了目标文件。
		try {
			File file = new File(path + "testTextB.txt");
			file.createNewFile();
			IOUtil.move(new File(path + "testTextB.txt"), new File(path + "notExist/notExist.txt"), null);
			file.delete();
		} catch (Exception e) {

			Assert.fail();
		}
		// 将文件移动到同名文件，执行成功
		try {
			File file = new File(path + "sourceTestB.txt");
			file.createNewFile();
			IOUtil.move(new File(path + "sourceTestB.txt"), new File(path + "sourceTestB.txt"), null);
			file.delete();
		} catch (Exception e) {
			Assert.fail();
		}
		// 将目录移动到目录下的文件，抛出异常
		try {
			IOUtil.move(new File(path + "/primeton"), new File(path + "/primeton/sourceTest.txt"), null);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof IllegalArgumentException);
		}
	}

	/**
	 * 
	 */
	@Test
	public void zipTest() {
		// 测试输入文件为空,抛出参数异常
		try {
			IOUtil.zip(null, new File(path + "/zipTest/zipfile"), null, false);
		} catch (Exception e) {
			Assert.assertEquals("inputPath is null!", e.getMessage());
		}
		// 测试输入文件不存在，抛出参数异常
		try {
			IOUtil.zip(new File(path + "/zipTest/notExistFile"), new File(path + "/zipTest/zipfile"), null, false);
		} catch (Exception e) {

		}
		// 测试压缩文件为空，抛出异常
		try {
			IOUtil.zip(new File(path + "/dir"), null, null, false);
		} catch (Exception e) {
			Assert.assertEquals("zipFile is null!", e.getMessage());
		}
		// zip在input的路径下，报出参数异常
		try {
			IOUtil.zip(new File(path + "/zipTest"), new File(path + "/zipTest/inputfile.txt"), null, false);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof IllegalArgumentException);
		}
		// zip是目录，抛出参数异常
		try {
			IOUtil.zip(new File(path + "/zipTest/inputfile.txt"), new File(path + "/zipTest"), null, false);
		} catch (Exception e) {
			//
			Assert.assertTrue(e instanceof IllegalArgumentException);
		}

		// zip不存在，输入文件是目录。创建了执行文件，压缩函数执行成功
		try {
			IOUtil.zip(new File(path + "/dir"), new File(path + "/destZip"), null, false);
			new File(path + "/zipTest/destZip").delete();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}

		// zip不存在，输入文件不是目录，创建了压缩文件，压缩函数执行成功,成功后删除文件。
		try {
			IOUtil.zip(new File(path + "/dir/zip.txt"), new File(path + "/destZip"), null, false);
			new File(path + "/zipTest/destZip").delete();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		// zip存在，update值为true,输入文件是不是目录，目录文件夹下压缩成功
		try {
			IOUtil.zip(new File(path + "/dir/zip.txt"), new File(path + "/zipfile"), null, true);
		} catch (Exception e) {
			//
			Assert.fail();
		}
		// zip存在，update值为true,输入文件是目录，目录文件夹下压缩成功
		try {
			IOUtil.zip(new File(path + "/dir"), new File(path + "/zipfile"), null, true);
		} catch (Exception e) {
			//
			Assert.fail();
		}
		// zip存在，update为false，输入文件不是目录，目录文件夹下压缩成功
		try {
			IOUtil.zip(new File(path + "/dir/zip.txt"), new File(path + "/zipfile"), null, false);
		} catch (Exception e) {

			Assert.fail();
		}
		// zip存在，update值为false,输入文件是目录，目录文件夹下压缩成功
		try {
			IOUtil.zip(new File(path + "/dir/zip.txt"), new File(path + "/zipfile"), null, false);
		} catch (Exception e) {
			Assert.fail();
		}

	}

	/**
	 * 测试获取资源文件
	 */
	@Test
	public void getResourceTest() {
		// 资源的字符串为null,抛出参数异常
		try {
			IOUtil.getResource(null, null);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof IllegalArgumentException);
		}
		// 资源的的字符串长度为0，抛出参数异常
		try {
			IOUtil.getResource(null, new String());
		} catch (Exception e) {
			Assert.assertTrue(e instanceof IllegalArgumentException);
		}
		// 类名不为null,clazz.getResource(resource)为空，获取资源失败，
		try {
			Assert.assertNull(IOUtil.getResource(resouceTest.class, "notExist.not"));

		} catch (Exception e) {
			Assert.fail();
		}

		// 类名不为null,clazz.getResource(resource)不为空，获取资源成功。
		try {

			Assert.assertNotNull(IOUtil.getResource(resouceTest.class, "testText.txt"));

		} catch (Exception e) {
			Assert.fail();
		}
	}

	/**
	 * 测试获取资源文件
	 */
	@Test
	public void getALLResourceTest() {
		// 资源的字符串为null,抛出参数异常
		try {
			IOUtil.getAllResources(null, null);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof IllegalArgumentException);
		}
		// 资源的的字符串长度为0，抛出参数异常
		try {
			IOUtil.getAllResources(null, new String());
		} catch (Exception e) {
			Assert.assertTrue(e instanceof IllegalArgumentException);
		}
		// 类名不为null,clazz.getResource(resource)为空,获取资源失败
		try {
			Assert.assertNotNull(IOUtil.getAllResources(resouceTest.class, "notExist"));

		} catch (Exception e) {
			Assert.fail();
		}

		// 类名不为null,clazz.getResource(resource)不为空，获取资源成功
		try {

			Assert.assertNotNull(IOUtil.getAllResources(resouceTest.class, "testText.txt"));
		} catch (Exception e) {
			Assert.fail();
		}
	}

	/**
	 * 测试read方法
	 */
	@Test
	public void readTest() {
		try {
			File file = null;
			IOUtil.read(file);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof IllegalArgumentException);
		}
		try {
			IOUtil.read(new File(path + "notExist"));

		} catch (Exception e) {
			Assert.assertTrue(e instanceof IllegalArgumentException);
		}

		try {
			IOUtil.read(new File(path + "dir"));
		} catch (Exception e) {
			Assert.assertTrue(e instanceof IllegalArgumentException);
		}

		try {
			IOUtil.read(new File(path + "testText.txt"));
		} catch (Exception e) {
			Assert.fail();
		}
	}

	@Test
	public void read0Test() {

		try {
			InputStream input = null;
			IOUtil.read(input);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof IllegalArgumentException);
		}

		try {
			File file = new File(path + "testText.txt");
			InputStream input = new FileInputStream(file);
			IOUtil.read(input);
		} catch (Exception e) {
			Assert.fail();
		}
	}

	@Test
	public void writeTest() {
		// 输入文件为null,报参数异常错误
		try {
			IOUtil.write(null, null, true);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof IllegalArgumentException);
		}
		// 文件是目录，报出参数异常
		try {
			IOUtil.write(new File(path + "dir"), null, true);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof IllegalArgumentException);
		}
		// 文件不存在，
		try {
			IOUtil.write(new File(path + "tempTest.txt"), null, true);
			new File(path + "tempTest.txt").delete();
		} catch (Exception e) {
			Assert.fail();
		}
		// 文件存在，执行成功，添加了字符'a'
		try {
			byte[] bytes = new byte[5];
			bytes[0] = 'a';
			IOUtil.write(new File(path + "sourceTest.txt"), bytes, true);
		} catch (Exception e) {
			Assert.fail();
		}
	}

	/**
	 * 测试连接两个文件，将src文件添加到dest文件后面
	 * 
	 * @throws IOException
	 */
	@Test
	public void concatTest() throws IOException {
		// dest文件为空，抛出参数异常
		try {

			File destFile = new File(path + "destFile");
			File srcFile = new File(path + "srcFile");
			destFile.createNewFile();
			srcFile.createNewFile();

			IOUtil.concat(null, null, srcFile);

			destFile.delete();
			srcFile.delete();
		} catch (Exception e) {
			Assert.assertTrue(e instanceof IllegalArgumentException);
		}

		// dest文件为不存在,，则创建dest文件
		try {

			File destFile = new File(path + "destFile");
			File srcFile = new File(path + "srcFile");
			destFile.createNewFile();
			srcFile.createNewFile();
			destFile.delete();
			IOUtil.concat(destFile, null, srcFile);

			srcFile.delete();
		} catch (Exception e) {
			Assert.assertTrue(e instanceof IllegalArgumentException);
		}
		// dest文件是目录，抛出参数异常
		try {

			File destFile = new File(path + "destFile");
			File srcFile = new File(path + "srcFile");
			destFile.mkdir();
			srcFile.createNewFile();

			IOUtil.concat(destFile, null, srcFile);

			destFile.delete();
			srcFile.delete();
		} catch (Exception e) {
			Assert.assertTrue(e instanceof IllegalArgumentException);
		}
		// src文件为空，抛出参数异常
		try {

			File destFile = new File(path + "destFile");
			destFile.createNewFile();

			IOUtil.concat(destFile, null, null);
			destFile.delete();

		} catch (Exception e) {
			Assert.assertTrue(e instanceof IllegalArgumentException);
		}
		// src不存在，抛出参数异常
		try {

			File destFile = new File(path + "destFile");
			File srcFile = new File(path + "srcFile");
			destFile.createNewFile();
			srcFile.delete();

			IOUtil.concat(destFile, null, srcFile);

			destFile.delete();
			srcFile.delete();
		} catch (Exception e) {
			Assert.assertTrue(e instanceof IllegalArgumentException);
		}
		// src文件是目录，抛出参数异常
		try {

			File destFile = new File(path + "destFile");
			File srcFile = new File(path + "srcFile");
			destFile.createNewFile();
			srcFile.mkdir();

			IOUtil.concat(destFile, null, srcFile);

			destFile.delete();
			srcFile.delete();
		} catch (Exception e) {
			Assert.assertTrue(e instanceof IllegalArgumentException);
		}
		// src文件存在，dest文件存在，函数执行成功
		try {
			byte[] list = new byte[1];
			list[0] = 'a';
			File destFile = new File(path + "destFile.txt");
			File srcFile = new File(path + "srcFile.txt");
			destFile.createNewFile();
			srcFile.createNewFile();

			IOUtil.concat(destFile, list, srcFile);

			destFile.delete();
			srcFile.delete();
		} catch (Exception e) {
			Assert.fail();
		}
	}

	@Test
	public void toCharSetTest() {
		// byte为null,抛出参数异常
		try {
			IOUtil.toCharSet(null, "utf-8", "gbk");
		} catch (Exception e) {
			Assert.assertTrue(e instanceof IllegalArgumentException);
		}
		// 默认字符集为空，抛出参数异常
		try {
			byte[] bytes = new byte[1];
			bytes[0] = 'a';
			IOUtil.toCharSet(bytes, null, "utf-8");
		} catch (Exception e) {
			Assert.assertTrue(e instanceof IllegalArgumentException);
		}
		// 要转换的字符集为空,抛出参数异常
		try {
			byte[] bytes = new byte[1];
			bytes[0] = 'a';
			IOUtil.toCharSet(bytes, "gbk", null);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof IllegalArgumentException);
		}
		// 输入正常参数，函数执行成功
		try {
			byte[] bytes = new byte[1];
			bytes[0] = 'a';
			IOUtil.toCharSet(bytes, "gbk", "utf-8");
		} catch (Exception e) {
			Assert.fail();
		}
	}

	@Test
	public void toCharSetTest0() {
		// 字符串为null,抛出参数异常
		try {
			IOUtil.toCharSet(null, "utf-8");
		} catch (Exception e) {
			Assert.assertTrue(e instanceof IllegalArgumentException);
		}
		// 字符串为长度为0，抛出参数异常
		try {

			IOUtil.toCharSet(new String(), "utf-8");
		} catch (Exception e) {
			Assert.assertTrue(e instanceof IllegalArgumentException);
		}
		// 要转换的字符集为空,抛出参数异常
		try {

			IOUtil.toCharSet("test", null);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof IllegalArgumentException);
		}
		// 参数正常，执行成功
		try {

			IOUtil.toCharSet("test", "utf-8");
		} catch (Exception e) {
			Assert.fail();
		}
	}

	@Test
	public void getAbsoluteClassPathTest() {
		// clazz为空,默认取ioutil的路径
		try {
			IOUtil.getAbsoluteClassPath(null);
		} catch (Exception e) {
			Assert.fail();
		}
		// class不为空
		try {
			IOUtil.getAbsoluteClassPath(resouceTest.class);
		} catch (Exception e) {
			Assert.fail();
		}
	}
}

/**
 * 测试getResource方法时创建的测试类类
 * 
 */
class resouceTest {
}