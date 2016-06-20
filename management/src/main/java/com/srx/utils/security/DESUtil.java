package com.srx.utils.security;

import java.security.InvalidKeyException;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
/**
 * DES可逆加密
 * @author SIMON
 *
 */
public class DESUtil {
	public static final String TYPE = "DES";
	/**
	 * 默认私钥
	 */
	private static String defaultKey = "NATIONAL";
	/**
	 * 默认字符编码
	 */
	private static String defaultCharset = "UTF-8";
	private static Cipher cipher = null;

	/**
	 * 将byte数组转换为表示16进制值的字符串
	 * 
	 * @param arr
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	private static String byteArr2HexStr(final byte[] arr, final String charset) throws Exception {
		int len = arr.length;
		StringBuffer sb = new StringBuffer(len * 2);
		for (int i = 0; i < len; i++) {
			int intTmp = arr[i];
			// 把负数转换为正数
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			// 小于0F的数需要在前面补0
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return new String(sb.toString().getBytes(),
				charset == null || charset.trim().length() < 1 ? defaultCharset : charset);
	}

	/**
	 * 将表示16进制值的字符串转换为byte数组
	 * 
	 * @param content
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	private static byte[] hexStr2ByteArr(final String content, final String charset) throws Exception {
		byte[] arr = content.getBytes(charset == null || charset.trim().length() < 1 ? defaultCharset : charset);
		// 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
		byte[] arrOut = new byte[arr.length / 2];
		for (int i = 0; i < arr.length; i = i + 2) {
			String strTmp = new String(arr, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

	/**
	 * 生成密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位
	 * 
	 * @param key
	 *            私钥,默认为NATIONAL
	 * @param charset
	 *            字符编码,默认为UTF-8
	 * @return
	 * @throws Exception
	 */
	private static Key getKey(final String key, final String charset) throws Exception {
		// 创建一个空的8位字节数组（默认值为0）
		byte[] arr = new byte[8];
		String k = key == null || key.trim().length() < 1 ? defaultKey : key;
		String c = charset == null || charset.trim().length() < 1 ? defaultCharset : charset;
		byte[] bytes = k.getBytes(c);
		// 将原始字节数组转换为8位
		for (int i = 0; i < bytes.length && i < arr.length; i++) {
			arr[i] = bytes[i];
		}
		return new SecretKeySpec(arr, TYPE);
	}
	/**
	 * 加密
	 * @param content	密文byte数组
	 * @param key		私钥,默认为NATIONAL
	 * @param charset	字符编码,默认为UTF-8
	 * @return
	 * @throws InvalidKeyException
	 * @throws Exception
	 */
	public static String encrypt(final byte[] content, final String key, final String charset)
			throws InvalidKeyException, Exception {
		cipher = Cipher.getInstance(TYPE);
		cipher.init(Cipher.ENCRYPT_MODE, getKey(key, charset));
		return byteArr2HexStr(cipher.doFinal(content), charset);
	}
	/**
	 * 解密
	 * @param content	密文byte数组
	 * @param key		私钥,默认为NATIONAL
	 * @param charset	字符编码,默认为UTF-8
	 * @return
	 * @throws InvalidKeyException
	 * @throws Exception
	 */
	public static String decrypt(final byte[] content, final String key, final String charset)
			throws InvalidKeyException, Exception {
		return decrypt(new String(content, charset), key, charset);
	}
	/**
	 * 加密
	 * @param content	明文,非空
	 * @param key		私钥,默认为NATIONAL
	 * @param charset	字符编码,默认为UTF-8
	 * @return
	 * @throws InvalidKeyException
	 * @throws Exception
	 */
	public static String encrypt(final String content, final String key, final String charset)
			throws InvalidKeyException, Exception {
		if (content == null || content.trim().length() < 1) {
			throw new NullPointerException("The parameter 'content' is not allowed be nulls.");
		}
		return encrypt(content.getBytes(), key, charset);
	}
	/**
	 * 解密
	 * @param content	密文,非空
	 * @param key		私钥,默认为NATIONAL
	 * @param charset	字符编码,默认为UTF-8
	 * @return
	 * @throws InvalidKeyException
	 * @throws Exception
	 */
	public static String decrypt(final String content, final String key, final String charset)
			throws InvalidKeyException, Exception {
		if (content == null || content.trim().length() < 1) {
			throw new NullPointerException("The parameter 'content' is not allowed be nulls.");
		}
		cipher = Cipher.getInstance(TYPE);
		cipher.init(Cipher.DECRYPT_MODE, getKey(key, charset));
		return new String(cipher.doFinal(hexStr2ByteArr(content, charset)),
				charset == null || charset.trim().length() < 1 ? defaultCharset : charset);
	}
	/**
	 * 测试
	 * @param args
	 * @throws InvalidKeyException
	 * @throws Exception
	 */
	public static void main(String[] args) throws InvalidKeyException, Exception {
		String msg = "你好啊";
		String key = "2015-11-28!@#中国";
		String encryptMsg = DESUtil.encrypt(msg, key, null);
		String decryptMsg = DESUtil.decrypt(encryptMsg, key, null);

		System.out.println("明文内容：" + msg);
		//	明文内容：你好啊
		System.out.println("加密内容：" + encryptMsg);
		//	加密内容：63334e6eb1e2e32eb1ed877c14549003
		System.out.println("解密内容：" + decryptMsg);
		//	解密内容：你好啊
		
	}
}
