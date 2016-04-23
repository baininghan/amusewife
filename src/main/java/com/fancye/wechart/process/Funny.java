package com.fancye.wechart.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fancye.wechart.util.PropertiesUtil;


public class Funny {

	private static final String apikey = "c9267621f53c24e8fa8f18d552e3c968";// 笑话接口的apikey
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String httpUrl = "http://apis.baidu.com/showapi_open_bus/showapi_joke/joke_text";
		String httpArg = "page=1";
		String jsonResult = request(httpUrl, httpArg);
		 JSONObject jsonObj = JSON.parseObject(jsonResult);
		 JSONObject showapi_res_body = (JSONObject)jsonObj.get("showapi_res_body");
		 @SuppressWarnings("unchecked")
		List<JSONObject> list = (List<JSONObject>)showapi_res_body.get("contentlist");
		 for(JSONObject content : list) {
			 System.out.println(JSON.toJSONString(content.get("title")));
			 System.out.println(JSON.toJSONString(content.get("text")));
			 System.out.println(JSON.toJSONString(content.get("type")));
			 System.out.println(JSON.toJSONString(content.get("ct")));
			 System.out.println("==============================");
		 }
	}
	

	/**
	 * @param urlAll
	 *            :请求接口
	 * @param httpArg
	 *            :参数
	 * @return 返回结果
	 */
	public static String request(String httpUrl, String httpArg) {
	    BufferedReader reader = null;
	    String result = null;
	    StringBuffer sbf = new StringBuffer();
	    httpUrl = httpUrl + "?" + httpArg;

	    try {
	        URL url = new URL(httpUrl);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("GET");
	        // 填入apikey到HTTP header
	        connection.setRequestProperty("apikey",  apikey);
	        connection.connect();
	        InputStream is = connection.getInputStream();
	        reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        String strRead = null;
	        while ((strRead = reader.readLine()) != null) {
	            sbf.append(strRead);
	            sbf.append("\r\n");
	        }
	        reader.close();
	        result = sbf.toString();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}

}
