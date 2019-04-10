package com.example.demo1.pay.weixinpay.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtils extends org.apache.commons.lang3.StringUtils{

	/**
	 * 使用参数替换字符串中以{}包含的参数
	 * @param str
	 * @param value1
	 * @return
	 */
	public static String format(String str,Object value1){
		return str.replace("{1}",value1.toString());
	}
	/**
	 * 使用参数替换字符串中以{}包含的参数
	 * @param str
	 * @param value1
	 * @param value2
	 * @return
	 */
	public static String format(String str,Object value1,Object value2){
		Object[] obj=new Object[]{value1,value2};
		return StringUtils.format(str,obj);
	}
	/**
	 * 使用参数替换字符串中以{}包含的参数
	 * @param str
	 * @param value1
	 * @param value2
	 * @param value3
	 * @return
	 */
	public static String format(String str,Object value1,Object value2,Object value3){
		Object[] obj=new Object[]{value1,value2,value3};
		return StringUtils.format(str,obj);
	}
	/**
	 * 使用参数替换字符串中以{}包含的参数
	 * @param str
	 * @param value1
	 * @param value2
	 * @param value3
	 * @param value4
	 * @return
	 */
	public static String format(String str,Object value1,Object value2,Object value3,Object value4){
		Object[] obj=new Object[]{value1,value2,value3,value4};
		return StringUtils.format(str,obj);
	}
	/**
	 * 使用参数替换字符串中以{}包含的参数
	 * @param str
	 * @param value1
	 * @param value2
	 * @param value3
	 * @param value4
	 * @param value5
	 * @return
	 */
	public static String format(String str,Object value1,Object value2,Object value3,Object value4,Object value5){
		Object[] obj=new Object[]{value1,value2,value3,value4,value5};
		return StringUtils.format(str,obj);
	}
	/**
	 * 使用参数替换字符串中以{}包含的参数
	 * @param str
	 * @param value1
	 * @param value2
	 * @param value3
	 * @param value4
	 * @param value5
	 * @param value6
	 * @return
	 */
	public static String format(String str,Object value1,Object value2,Object value3,Object value4,Object value5,Object value6){
		Object[] obj=new Object[]{value1,value2,value3,value4,value5,value6};
		return StringUtils.format(str,obj);
	}
	/**
	 * 使用参数替换字符串中以{}包含的参数
	 * @param str
	 * @param values
	 * @return
	 */
	public static String format(String str,Object[] values){
		for(int i=0;i<values.length;i++){
			str=str.replace("{"+i+"}",values[i].toString());
		}
		return str;
	}
	/**
	 * 判断字符串是否数字 有问题
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		try {
			if (null == str)
				return false;
			new Double(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	public final static String cutStr(String str,int n){
		return StringUtils.eCutStr(str, n*2);
	}
	/**
	 * 扩展后的按字节取字符�?
	 * @param str�?��字符�?
	 * @param n�?��节数
	 * @return�?��取后的字�?
	 */
	public static String eCutStr(String str,int n){
		String temp="";
		if(n==0) return str;
		if(n<3){n=3;}
		try{
			char[] chr=str.toCharArray();
			for(int i=0;i<chr.length;i++){
				temp+=chr[i];
				if(temp.getBytes("GBK").length>=n)break;
			}
			if(temp.length()<str.length())
				temp=temp+"...";
		}
		catch(Exception e){
			
		}
		return temp;
	}
	/*
	public static String eCutStr(String str,int n){
		String temp="";
		if(n==0||str.getBytes().length<=n) return str;
		int i=n/2;
		temp=str.substring(0,i);
		int bytes=temp.getBytes().length;
		int b;
		while(bytes<n){
			b=(temp+str.substring(i,i+((n-bytes)==1?2:(n-bytes))/2)).getBytes().length;
			if(b<=n){
				temp+=str.substring(i,i+((n-bytes)==1?2:(n-bytes))/2);
			}
			i=i+((n-bytes)==1?2:(n-bytes))/2;
			bytes=b;
		}
		if(i<str.length())
			temp+="...";
		return temp;
	}
	*/
	  public static String trans(String chi){
	    String result="";
	    byte temp[];
	    try{
	            temp=chi.getBytes("iso-8859-1");
	            result=new String(temp);
	    }
	    catch(Exception e){
	      e.printStackTrace();
	    }
	    return result;
	  }
	  public static String transUTF8(String chi){
		    String result="";
		    byte temp[];
		    try{
		            temp=chi.getBytes("iso-8859-1");
		            result=new String(temp,"UTF-8");
		    }
		    catch(Exception e){
		      e.printStackTrace();
		    }
		    return result;
	  }
	  
	  /**
	   * 自动转换get提交的参数编码，只支持utf-8 和iso-8859-1
	   * @param str
	   * @return
	   */
	  public static String transURI(String str){
		  if(str==null||"".equals(str)){
			  return str;
		  }
		  if(StringUtils.isIso(str)){
				return StringUtils.transUTF8(str);
		  }
		  else{
			  return str;
		  }
	  }
	  
	  public static boolean isIso(String str){
		  try{
			  //byte[] b=str.getBytes();
			  //if(isValidUtf8(b,b.length)) return "UTF-8";
			  byte[] temp1  =  str.getBytes("iso-8859-1");  
			  String compstring1  =  new  String(temp1,"iso-8859-1");
			  if(str.equals(compstring1)){
				  return true;
			  }
			  else{
				  return false;
			  }
		  }
		  catch(Exception e){
		      e.printStackTrace();
		      return false;
		 } 
	  }
	  
	  public static boolean isValidUtf8(byte[] b,int aMaxCount){
	      int lLen=b.length,lCharCount=0;
	      for(int i=0;i<lLen && lCharCount<aMaxCount;++lCharCount){
	             byte lByte=b[i++];
	             if(lByte>=0) continue;
	             if(lByte<(byte)0xc0 || lByte>(byte)0xfd) return false;
	             int lCount=lByte>(byte)0xfc?5:lByte>(byte)0xf8?4
	                    :lByte>(byte)0xf0?3:lByte>(byte)0xe0?2:1;
	             if(i+lCount>lLen) return false;
	             for(int j=0;j<lCount;++j,++i) if(b[i]>=(byte)0xc0) return false;
	      }
	      return true;
	  }
	  
	  public static String clobToString(java.sql.Clob clob){
		  String str="";
		  try{
			  if ( clob != null )
		      {
			        java.io.Reader is = clob.getCharacterStream ();
			        BufferedReader br = new BufferedReader ( is );
			        String s;
			        s=br.readLine();
			        if(s!=null){
			        	str+=s;
			        }
			        while ((s=br.readLine ())!= null )
			        {
			        	str +="\r\n"+s ;
			        }
		      }
			  else{
				  str=null;
			  }
		  }
		  catch(Exception e){
			  str=null;
		  }
		  return str;
	  }
	  
	  public static String blobToString(java.sql.Blob blob){
		  String str="";
		  try{
			  if ( blob != null )
		      {
			        InputStream is = blob.getBinaryStream();
			        byte[] bytes=new byte[(int)blob.length()];
			        is.read(bytes);
			        str=new String(bytes,"UTF-8");
		      }
			  else{
				  str=null;
			  }
		  }
		  catch(Exception e){
			  str=null;
		  }
		  return str;
	  }
	  
	  public static String filterHTML(String str){
		  if(str==null){return str;}
		  str=str.replaceAll("&((quot;)|(amp;)|(lt;)|(gt;)|(euro;|)(nbsp;))","&amp;$1");
		  return str.replace("<","&lt;").replace(">","&gt;");
	  }
	  
	  public static Date String2Date(String str,String format){
		  SimpleDateFormat sdf=new SimpleDateFormat(format);
		  Date d;
		try {
			d = sdf.parse(str);
			return d;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	  }

	  /**
	   * 将url参数进行编码
	   * @param inStr
	   * @return
	   */
	public static String encodeParameter(String inStr) {
		String reg = "([^=]+=)([^&]*)";
		Pattern p = Pattern.compile(reg, Pattern.DOTALL
				+ Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(inStr);
		StringBuffer tempStr = new StringBuffer();
		try {
			while (m.find()) {
				m.appendReplacement(tempStr, m.group(1)
						+ java.net.URLEncoder.encode(m.group(2), "utf-8"));
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m.appendTail(tempStr);
		return tempStr.toString();
	}
	
	public static String getRandomString(Integer length){
		char[] numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz" +
                "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
		Random randGen = new Random();
		char [] randBuffer = new char[length];
        for (int i=0; i<randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
            //randBuffer[i] = numbersAndLetters[randGen.nextInt(35)];
        }
        return new String(randBuffer);
	}
	
	  /**
	   * 把物理地址转换成经纬度
	   * @param inStr
	   * @return
	   */	
	public static String[] getCoordinate(String addr){  
        String address = null;  
        try {  
            address = java.net.URLEncoder.encode(addr,"UTF-8");  
        } catch (UnsupportedEncodingException e1) {  
            e1.printStackTrace();  
        };  
        String output = "csv";  
        String key = "abc";  
        String url = String.format("http://maps.google.com/maps/geo?q=%s&output=%s&key=%s", address, output, key);  
        URL myURL = null;  
        URLConnection httpsConn = null;  
        //进行转码   
        try {  
            myURL = new URL(url);  
        } catch (MalformedURLException e) {  
              
        }  
        try {  
            httpsConn = (URLConnection) myURL.openConnection();  
            if (httpsConn != null) {  
                InputStreamReader insr = new InputStreamReader(  
                        httpsConn.getInputStream(), "UTF-8");  
                BufferedReader br = new BufferedReader(insr);  
                String data = null;  
                if ((data = br.readLine()) != null) {  
                    String[] retList = data.split(",");  
                     
                    return retList;  
                }  
                insr.close();  
            }  
        } catch (IOException e) {  
              
        }  
        return null;              
    }  

}
