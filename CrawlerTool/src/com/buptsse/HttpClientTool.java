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

	// ���response
	public String getReString() {
		HttpClient httpClient = new DefaultHttpClient();
		try {
			String reString;
			HttpGet httpGet = new HttpGet(this.url);// ʵ����һ��HttpGet�࣬�˶���ָ������http����ʽΪget
			httpGet.setHeader("X-Requested-With", "XMLHttpRequest"); // ���첽�ķ�ʽ���response
			httpGet.setHeader( //������Ϣͷ�ϵ��û�����
					"User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.76 Safari/537.36");
			HttpResponse response = httpClient.execute(httpGet); //����һ����ʽΪGet��http����
			HttpEntity entity = response.getEntity();//��÷�������Ӧ���ص�ʵ��
			reString = EntityUtils.toString(entity);//��ʵ��ת����String
			System.out.println("response: " + reString);
			httpClient.getConnectionManager().shutdown();//�ر����ӹ�����

			return reString;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// HttpClientTool��������ģ�����������̳�������󣬻����̳�������𸴵�ҳ��
		(new HttpClientTool(
				"http://www.newsmth.net/nForum/article/CrossGate/375668?ajax"))
				.getReString();
	}

}
