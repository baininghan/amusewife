package com.fancye;

import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpClientTest {

	/**
	 * 最简单的HTTP客户端,用来演示通过GET或者POST方式访问某个页面
	 * 
	 * @param args
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static void main(String[] args) throws ClientProtocolException, IOException {

		HttpClient client = new DefaultHttpClient();
		// 设置代理服务器地址和端口
		// client.getHostConfiguration().setProxy("proxy_host_addr",proxy_port);
		// 使用 GET 方法 ，如果服务器需要通过 HTTPS 连接，那只需要将下面 URL 中的 http 换成 https
		HttpGet method = new HttpGet("http://apis.baidu.com/showapi_open_bus/showapi_joke/joke_text?page=1");
		HttpResponse response = client.execute(method);
		HttpEntity entity = response.getEntity();
		StringBuffer sb = new StringBuffer();
		if (entity != null) {
			InputStream instream = entity.getContent();
			int l;
			byte[] tmp = new byte[2048];
			while ((l = instream.read(tmp)) != -1) {
				sb.append(new String(tmp, "UTF-8"));
			}
		}
		System.out.println(sb.toString());
		// 打印服务器返回的状态
		System.out.println(method.getMethod());
		// 打印返回的信息
		System.out.println(method.getRequestLine());
		// 释放连接
		method.releaseConnection();

	}

}
