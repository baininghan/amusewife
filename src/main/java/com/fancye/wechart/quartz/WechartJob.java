package com.fancye.wechart.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fancye.wechart.process.Funny;

/**
 * 微信定时推送任务
 * 
 * @author Fancye
 *
 */
public class WechartJob implements Job {

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "★★★★★★★★★★★");

		String httpUrl = "http://apis.baidu.com/showapi_open_bus/showapi_joke/joke_text";
		String httpArg = "page=1";
		String jsonResult = Funny.request(httpUrl, httpArg);
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

}
