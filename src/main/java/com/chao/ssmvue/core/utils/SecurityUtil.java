package com.chao.ssmvue.core.utils;


import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.util.Base64Utils;

import java.io.UnsupportedEncodingException;

public final class SecurityUtil {
	
	private final static String CHART_ENCODE = "utf-8";
	private final static String PREFIX = "$1$";
	private final static String SALT = PREFIX + "chao";

	private SecurityUtil() {
	}

	public static String encodeMd5(String origin) throws UnsupportedEncodingException{
		return Md5Crypt.md5Crypt(origin.getBytes(CHART_ENCODE),SALT);
	}
	
	public static String encodeBase64(String origin) throws UnsupportedEncodingException{
		 return Base64Utils.encodeToString(origin.getBytes(CHART_ENCODE));
	}
	
	public static String decodeBase64(String origin) throws UnsupportedEncodingException{
		byte[] originBytes = Base64Utils.decodeFromString(origin);
		return new String(originBytes,CHART_ENCODE);
	}
	
}
