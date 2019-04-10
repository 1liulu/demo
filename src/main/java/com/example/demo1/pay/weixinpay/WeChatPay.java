package com.example.demo1.pay.weixinpay;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import com.example.demo1.pay.weixinpay.bean.WeixinBean;
import com.example.demo1.pay.weixinpay.config.WeixinConfig;
import com.example.demo1.pay.weixinpay.util.*;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.jdom.JDOMException;

public class WeChatPay {

 
    public Map<String, Object> wechatPayJSAPI(String indentNum,BigDecimal money,String openid,String body,HttpServletRequest request,String notify_url)  throws JDOMException {

	  	String totalCount = (money.setScale(2, BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal(100)).setScale(0).toString();
  
    	WeixinBean weixinBean = new WeixinBean(WeixinConfig.html_appid, WeixinConfig.partner_id, WeixinConfig.sub_partner_id,WeixinConfig.html_key, "JSAPI", openid, null,
    										body, indentNum, totalCount, notify_url);
  
    	Map<String,String> xml= doInBackground(weixinBean);

    	Map<String, Object> payMap = new HashMap<String, Object>();
    	System.out.println(xml);
	    if(xml.get("return_code").equals("SUCCESS")){   
	    	String	nonceStr = genNonceStr();    
	        String date=Sha1Util.getTimeStamp();
	        List<NameValuePair> packageParams = new LinkedList<NameValuePair>();
	        packageParams.add(new BasicNameValuePair("appId",WeixinConfig.html_appid)); 
 	        packageParams.add(new BasicNameValuePair("nonceStr",nonceStr)); 
	        packageParams.add(new BasicNameValuePair("package", "prepay_id="+xml.get("prepay_id")));      
	        packageParams.add(new BasicNameValuePair("signType","MD5"));
	        packageParams.add(new BasicNameValuePair("timeStamp",date));
	        String sign = genPackageSign(packageParams,WeixinConfig.html_key);  
	        payMap.put("sign", sign);
	        payMap.put("nonceStr", nonceStr);
	        payMap.put("timestamp", date);
	        payMap.put("prepayid", "prepay_id="+xml.get("prepay_id"));
	        payMap.put("appid", WeixinConfig.html_appid);
	    }else{
	    	 payMap.put("error", "错误");
	    }
		return payMap;
    }

  	protected  Map<String, String>  doInBackground(WeixinBean weixinBean) throws JDOMException {
  		
          String entity = getproduct(weixinBean);
          String buf = HtmlUtil.postData(WeixinConfig.service_url,entity);
          Map<String,String> xml=null;
          try {
        	  String content = new String(buf.getBytes(), "UTF-8");
			  xml=XMLUtil.doXMLParse(buf);
  		  }catch (UnsupportedEncodingException e1) {
  			// TODO Auto-generated catch block
  			e1.printStackTrace();
  		  } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  }
          return xml;
      }

	  public String getproduct(WeixinBean weixinBean){
	       List<NameValuePair> packageParams = new LinkedList<NameValuePair>();
	     
	       packageParams.add(new BasicNameValuePair("appid",weixinBean.getAppid()));          
	     
	       packageParams.add(new BasicNameValuePair("body",weixinBean.getBody()));
	     
	       packageParams.add(new BasicNameValuePair("mch_id", weixinBean.getMch_id()));
	     
	       packageParams.add(new BasicNameValuePair("nonce_str", genNonceStr()));
	    
	       packageParams.add(new BasicNameValuePair("notify_url",weixinBean.getNotify_url()));
	    
	       if(weixinBean.getTrade_type()=="JSAPI"){  
	    	   packageParams.add(new BasicNameValuePair("openid", weixinBean.getOpenid()));
	       }

	       packageParams.add(new BasicNameValuePair("out_trade_no",weixinBean.getOut_trade_no()));	  
	       packageParams.add(new BasicNameValuePair("spbill_create_ip",getLocalIP()));	  
	       packageParams.add(new BasicNameValuePair("total_fee", weixinBean.getTotal_fee()));	
	       packageParams.add(new BasicNameValuePair("trade_type", weixinBean.getTrade_type()));
	       String sign = genPackageSign(packageParams,weixinBean.getKey());
	       packageParams.add(new BasicNameValuePair("sign", sign));
	       String xmlstring =toXml(packageParams);   
	       return xmlstring;
	  }
	  

    public static String getLocalIP() {   
		InetAddress addr = null;   
        try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}         
        byte[] ipAddr = addr.getAddress();   
        String ipAddrStr = "";   
        for (int i = 0; i < ipAddr.length; i++) {   
            if (i > 0) {   
                ipAddrStr += ".";   
            }   
            ipAddrStr += ipAddr[i] & 0xFF;   
        }   

		return ipAddrStr;   
	} 
    

    private String genNonceStr() {
        Random random = new Random();
        return MD5Util.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
    }
    

    private String genPackageSign(List<NameValuePair> params,String key) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i).getName());
            sb.append('=');
            sb.append(params.get(i).getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append(key);     
        String packageSign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();  
        return packageSign;
    }

    public String toXml(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        for (int i = 0; i < params.size(); i++) {
            sb.append("<"+params.get(i).getName()+">");
            sb.append(params.get(i).getValue());
            sb.append("</"+params.get(i).getName()+">");
        }
        sb.append("</xml>");
        return sb.toString();
    }





}
