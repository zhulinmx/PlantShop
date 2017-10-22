package com.util;

public class IntegerUtils {
	
	public final static Integer StrToInteger(String str){
		if (str != null && !"".equals(str)) {
			return Integer.parseInt(str);
		} else {
			return null;
		}
		
	}

}
