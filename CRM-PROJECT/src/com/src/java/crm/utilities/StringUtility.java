package com.src.java.crm.utilities;

public class StringUtility {
	public static String getSubString(String str, int noc) {
		return (!isStringEmptyORNull(str) && str.length() >= noc ? str.substring(0, noc) + "..." : str );
	}
	
	public static boolean isStringEmptyORNull(String str) {
		return (str == null || str.length() == 0 ?  true : false);
	}
}
