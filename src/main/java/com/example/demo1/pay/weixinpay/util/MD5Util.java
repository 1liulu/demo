package com.example.demo1.pay.weixinpay.util;

import java.security.MessageDigest;

public class MD5Util {
	
	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",  
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	private static String byteArrayToHexString(byte b[]) {  
        StringBuffer resultSb = new StringBuffer();  
        for (int i = 0; i < b.length; i++)  
            resultSb.append(byteToHexString(b[i]));  
        return resultSb.toString();  
    }  
  
    private static String byteToHexString(byte b) {  
        int n = b;  
        if (n < 0)  
            n += 256;  
        int d1 = n / 16;  
        int d2 = n % 16;  
        return hexDigits[d1] + hexDigits[d2];  
    }  
    
    /**
     * 生成微信签名-MD5
     * @authour xilongfei
     * @2017年1月6日
     * 上午9:14:47
     * @param origin		字节
     * @param charsetname	格式  utf-8
     * @return   
     */
    public static String MD5Encode(String origin, String charsetname) {  
    	String resultString = null;  
    	try {  
    		resultString = new String(origin);  
    		MessageDigest md = MessageDigest.getInstance("MD5");  
    		if (charsetname == null || "".equals(charsetname))  
    			resultString = byteArrayToHexString(md.digest(resultString  
    					.getBytes()));  
    		else  
    			resultString = byteArrayToHexString(md.digest(resultString  
    					.getBytes(charsetname)));  
    	} catch (Exception exception) {  
    	}  
    	return resultString;  
    }  
  
    /**
     * 生成签名
     * @param buffer      
     * @return  返回签名
     */
    public final static String getMessageDigest(byte[] buffer) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(buffer);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
    }
 
    
}