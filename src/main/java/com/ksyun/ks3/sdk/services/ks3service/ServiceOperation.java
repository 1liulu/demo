/**
 * @author: yangji
 * @data:   Apr 8, 2013
 */
package com.ksyun.ks3.sdk.services.ks3service;

import com.ksyun.ks3.sdk.dto.Bucket;
import com.ksyun.ks3.sdk.dto.Credential;
import com.ksyun.ks3.sdk.dto.internal.HttpMethod;
import com.ksyun.ks3.sdk.dto.internal.Request;
import com.ksyun.ks3.sdk.dto.internal.Response;
import com.ksyun.ks3.sdk.services.httpservice.HttpRequestor;

import java.util.List;

public class ServiceOperation extends KS3Operation {
	
	public ServiceOperation(HttpRequestor requestor,Credential credential) throws Exception {
		super(requestor,credential);
	}
		
	public List<Bucket> getBucketList() throws Exception{
		
		RequestBuilder requestBuilder = requestFactory.getBuilder();	
		Request request = requestBuilder.setMethod(HttpMethod.GET).build();
		Response response = sendMessageAndKeepAlive(request);
		return resultParse.getBucketList(response.getBody());
	}	
}
