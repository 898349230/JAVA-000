package com.ab.httpclient;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class TestHttpClient {

	public static void main(String[] args) {
		CloseableHttpClient defaultClient = HttpClients.createDefault();
		HttpGet get = new HttpGet("http://localhost:8088/api/hello");
		CloseableHttpResponse execute = null;
		try {
			execute = defaultClient.execute(get);
			HttpEntity entity = execute.getEntity();
			String s = EntityUtils.toString(entity);
			System.out.println(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
