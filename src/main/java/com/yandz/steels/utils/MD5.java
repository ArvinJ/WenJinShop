package com.yandz.steels.utils;

import java.nio.charset.Charset;
import java.security.MessageDigest;

import org.springframework.util.DigestUtils;

public class MD5 {

	/**
	 * MD5方法
	 * 
	 * @param text
	 *            明文
	 * @param key
	 *            密钥
	 * @return 密文
	 * @throws Exception
	 */
	public static String md5(String text, String key) throws Exception {
		// 加密后的字符串
		//DigestUtils.md5DigestAsHex(str.getBytes(Charset.forName("UTF-8")));
		
		String encodeStr = DigestUtils.md5DigestAsHex((text + key).getBytes(Charset.forName("UTF-8")));
		encodeStr =	DigestUtils.md5DigestAsHex(encodeStr.getBytes());
		System.out.println("MD5加密后的字符串为:encodeStr=" + encodeStr);
		return encodeStr;
	}

	/**
	 * MD5验证方法
	 * 
	 * @param text
	 *            明文
	 * @param key
	 *            密钥
	 * @param md5
	 *            密文
	 * @return true/false
	 * @throws Exception
	 */
	public static boolean verify(String text, String key, String md5) throws Exception {
		// 根据传入的密钥进行验证
		String md5Text = md5(text, key);
		if (md5Text.equalsIgnoreCase(md5)) {
			System.out.println("MD5验证通过");
			return true;
		}

		return false;
	}
	
	
	public static void main(String[] args) {
		
		try {
		 //System.err.println(md5("wenjin","123"));
		 verify("yandz","123456","ddd");
		 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
}
