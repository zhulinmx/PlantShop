package framework;

import com.util.StringUtils;

public class test {
	public static void main(String[] args) {
		
		/*
		 * 错误写法！！！
		 */
		System.out.println(Integer.parseInt(StringUtils.convertNull(null))) ;
	}
	
}
