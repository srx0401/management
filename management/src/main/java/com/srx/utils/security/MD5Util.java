package com.srx.utils.security;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.srx.utils.string.StringUtil;
/**
 * MD5加密工具类
 * @author SIMON
 *
 */
public class MD5Util {
	/**
	 * 
	 */
	public static final String DEFAULT_CHARSET = "UTF-8";
	/**
	 * 默认密文长度
	 */
	public static final int DEFAULT_SIZE = 32;
	/**
	 * 密文长度最小值
	 */
	public static final int MIN_SIZE = 1;
	/**
	 * 密文长度最大值
	 */
	public static final int MAX_SIZE = 64;
	private static Log log = LogFactory.getLog(MD5Util.class);
	/**
	 * 	MD5加密
	 * @param content	待加密字符,不可为空
	 * @param key		密钥
	 * @param size		需要的密文长度,默认为[32]
	 * @param charset	字符编码,默认为[UTF-8]
	 * @return			返回加密后的指定长度的密文,异常时返回null
	 */
	public static String MD5(final String content,final String key,int size,final String charset) {
		if (StringUtil.isEmpty(content)) {
			return null;
		}
		String text = "";
		if (!StringUtil.isEmpty(key)) {
			StringBuffer lo = new StringBuffer(content.length() > key.length() ? content : key);
			StringBuffer sh = new StringBuffer(content.length() > key.length() ? key : content);
			for(int i = 0;i < sh.length();i ++){
				lo.insert(lo.length()/(i+1), sh.charAt(i));
			}
			text = lo.toString();
		}else{
			text = content;
		}
		
		size = size < MIN_SIZE || size > MAX_SIZE ? DEFAULT_SIZE : size;
		try {
			StringBuffer res = new StringBuffer("");
//			MessageDigest md = MessageDigest.getInstance("MD5");
//			md.update(text.getBytes(StringUtil.isEmpty(charset) ? DEFAULT_CHARSET : charset));
//			for(byte b : md.digest()){
//				int temp = b < 0 ? b + 256 : b;
//				res.append(temp < 16 ? "0" : "").append(Integer.toHexString(temp));
//			}
			res.append(DigestUtils.md5Hex(text.toString().getBytes(StringUtil.isEmpty(charset) ? DEFAULT_CHARSET : charset)));
			if(res.length() > size){
				int i = res.length() - size;
				return res.substring(i/2,i/2 + size).toUpperCase();
			}else{
				StringBuffer res1 = new StringBuffer(res);
				while(res1.length() < size){
					res1.append(res.charAt(size % res1.length()));
				}
				return res1.toString().toUpperCase();
			}

//		} catch (NoSuchAlgorithmException e) {
//			log.error(e.getMessage(),e);
//			return null;
		} catch (UnsupportedEncodingException e2) {
			log.error(e2.getMessage(),e2);
			return null;
		}
	}
	/**
	 * MD5加密
	 * @param content	待加密字符,不可为空
	 * @param size		需要的密文长度,默认为[32]
	 * @param charset	字符编码,默认为[UTF-8]
	 * @return			返回加密后的指定长度的密文,异常时返回null
	 */
	public static String MD5(final String content,int size,final String charset) {
		return MD5(content, null, size, charset);
	}
	/**
	 * MD5加密(默认以[UTF-8]解码)
	 * @param content	待加密字符,不可为空
	 * @param size		需要的密文长度,默认为[32]
	 * @return			返回加密后的指定长度的密文,异常时返回null
	 */
	public static String MD5(final String content,int size){
		return MD5(content,null,size,DEFAULT_CHARSET);
	}
	/**
	 * 	MD5加密(默认以[UTF-8]解码,返回密文长度为[32])
	 * @param content	待加密字符,不可为空
	 * @param key		密钥
	 * @return			返回加密后的32位密文,异常时返回null
	 */
	public static String MD5(final String content,final String key){
		return MD5(content,key,DEFAULT_SIZE,DEFAULT_CHARSET);
	}
	/**
	 * MD5加密(默认以[UTF-8]解码,返回密文长度为[32])
	 * @param content	待加密字符,不可为空
	 * @return			返回加密后的32位密文,异常时返回null
	 */
	public static String MD5(final String content){
		return MD5(content,null,DEFAULT_SIZE,DEFAULT_CHARSET);
	}
	
	/**
	 * 	验证指定的明文按指定的密钥和编码加密后是否和密文一致[true,false]
	 * @param ciphertext	加密后的密文字符串,为空返回[false]
	 * @param plaintext		等待验证的明文字符串,为空返回[false]
	 * @param key			加密时的密钥
	 * @param charset		字符编码,默认取值[UTF-8]
	 * @return				异常返回[false]
	 */
	public static boolean verify(final String ciphertext, final String plaintext
			, final String key,final String charset) {
		return ciphertext.equals(MD5(plaintext, key, ciphertext.length(),charset));
	}
	/**
	 * 	验证指定的明文按指定编码加密后是否和密文一致[true,false]
	 * @param ciphertext	加密后的密文字符串,为空返回[false]
	 * @param plaintext		等待验证的明文字符串,为空返回[false]
	 * @param charset		字符编码,默认取值[UTF-8]
	 * @return				异常返回[false]
	 */
	public static boolean verify(final String ciphertext, final String plaintext
			,final String charset) {
		return ciphertext.equals(MD5(plaintext, ciphertext.length(),charset));
	}
	/**
	 * 	验证指定的明文按默认编码[UTF-8]加密后是否和密文一致[true,false]
	 * @param ciphertext	加密后的密文字符串,为空返回[false]
	 * @param plaintext		等待验证的明文字符串,为空返回[false]
	 * @return				异常返回[false]
	 */
	public static boolean verify(final String ciphertext, final String plaintext) {
		return ciphertext.equals(MD5(plaintext, ciphertext.length()));
	}
	public static void main(String[] args) {
		/*insert into srx_user(id,age,isRoot,phone,username,password) values(0,29,1,'18516181496','admin','202CB962AC59075B964B07152D234B70');*/
		String p1 = "123";
		String c1 = MD5(p1);
		boolean r1 = verify(c1, p1);
		System.out.println("[" + p1 + "]加密后的密文为===>:   " + c1);
		System.out.println("密文[" + c1 + "]和明文[" + p1 + "]的验证结果===>:   " + r1);
		System.out.println(MD5(p1,"密钥"));
		System.out.println(MD5(p1,"", 32, "UTF-8"));
		System.out.println(MD5(p1,"密钥", 32, "UTF-8"));
		String p2 = "lss";
		String c2 = MD5(p2);
		System.out.println(c2);
	}
}
