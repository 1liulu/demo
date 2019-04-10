package com.example.demo1.pay.weixinpay.bean;
 
public class WeixinBean {
	
	private String appid ;			 
	private String mch_id ;			 
	private String sub_mch_id ;			 
	private String key;				 
	private String trade_type;		    
	private String openid; 			 
	private String product_id;		 
	private String body; 			 
	private String out_trade_no;	 
	private String total_fee;		 
	private String notify_url; 		 
 
	public WeixinBean() {
		super();
	}
	public WeixinBean(String appid, String mch_id, String sub_mch_id, String key, String trade_type, String openid, String product_id,
			String body, String out_trade_no, String total_fee, String notify_url) {
		super();
		this.appid = appid;
		this.mch_id = mch_id;
		this.sub_mch_id = sub_mch_id;
		this.key = key;
		this.trade_type = trade_type;
		this.openid = openid;
		this.product_id = product_id;
		this.body = body;
		this.out_trade_no = out_trade_no;
		this.total_fee = total_fee;
		this.notify_url = notify_url;
	}
	
	public String getSub_mch_id() {
		return sub_mch_id;
	}

	public void setSub_mch_id(String sub_mch_id) {
		this.sub_mch_id = sub_mch_id;
	}

	public String getNotify_url() {
		return notify_url;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}
	
}
