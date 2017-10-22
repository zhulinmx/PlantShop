package com.util;

public class StringUtils {
	
	public final static String convertNull(String p0) {
        if (p0 == "" || p0 == null) {
            return null;
        } else {
            return p0.trim();
        }
    }
	
	/**
	 * 编码格式转换
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public final static String convertEncoding(String str) throws Exception {
		if (str != null && str != "") {
			String String = new String(str.getBytes("ISO8859-1"),"UTF-8");
			return String;
		}
		return "";
	}

}
