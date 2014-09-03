package com.buptsse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpClientTool {

	private String url;

	public HttpClientTool() {
		url = "";
	}

	public HttpClientTool(String url) {
		this.url = url;
	}

	// 获得response
	public String getReString() {
		HttpClient httpClient = new DefaultHttpClient();
		try {
			String reString;
			HttpGet httpGet = new HttpGet(this.url);// 实例化一个HttpGet类，此对象指明本次http请求方式为get
			httpGet.setHeader("X-Requested-With", "XMLHttpRequest"); // 以异步的方式获得response
			httpGet.setHeader( //设置消息头上的用户代理
					"User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.76 Safari/537.36");
			HttpResponse response = httpClient.execute(httpGet); //发送一个方式为Get的http请求
			HttpEntity entity = response.getEntity();//获得服务器响应返回的实体
			reString = EntityUtils.toString(entity);//将实体转换成String
			System.out.println("response: " + reString);
			httpClient.getConnectionManager().shutdown();//关闭连接管理器

			return reString;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// HttpClientTool类是用来模拟浏览器向论坛发送请求，获得论坛服务器答复的页面
		(new HttpClientTool(
				"http://www.newsmth.net/nForum/article/CrossGate/375668?ajax"))
				.getReString();
	}

}
