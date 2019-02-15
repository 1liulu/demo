package com.example.demo1.util;


import com.ksyun.ks3.utils.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Wechat2 {
		//拼接url
		public static String getAuthorityUrl(String param,String state ,String scope){
			 
			String redirecturl = URLEncoder.encode(param);
			
			StringBuilder url = new StringBuilder("https://open.weixin.qq.com/connect/oauth2/authorize?appid=");
			url.append(WechatConfig.AppId);
			url.append("&redirect_uri=");
			url.append(redirecturl);
			url.append("&response_type=code&scope=");
			url.append(scope);
			url.append("&state=");
			url.append(state);
			url.append("#wechat_redirect");
			return url.toString();
		}
		//拼接url
		public static String getTokenAndOpenidUrl(String code){
			StringBuilder url = new StringBuilder("https://api.weixin.qq.com/sns/oauth2/access_token?appid=");
			url.append(WechatConfig.AppId);
			url.append("&secret=");
			url.append(WechatConfig.secret);
			url.append("&code=");
			url.append(code);
			url.append("&grant_type=authorization_code");
			return url.toString();
			
		}
		//发送请求
		public static JSONObject getReturmParam(String url){
			 JSONObject demoJson = null;
			  try {		 
				  URL getUrl=new URL(url);
				  HttpURLConnection http=(HttpURLConnection)getUrl.openConnection();
				  http.setRequestMethod("GET"); 
				  http.setRequestProperty("Content-Type",
				  "application/x-www-form-urlencoded");
				  http.setDoOutput(true);
				  http.setDoInput(true);
				  http.connect();
				  InputStream is = http.getInputStream(); 
				  int size = is.available(); 	 
				  byte[] b = new byte[size];
				  is.read(b);
				  String message = new String(b, "UTF-8");	 
				  demoJson = new JSONObject(message);	 
				} catch (MalformedURLException e) {
				  e.printStackTrace();
			  } catch (IOException e) {
				  e.printStackTrace();
			  }
			  return demoJson;
		}

}
