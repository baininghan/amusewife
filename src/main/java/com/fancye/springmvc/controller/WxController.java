package com.fancye.springmvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fancye.common.AbstractController;
import com.fancye.common.http.HttpXmlClient;
import com.fancye.wechart.WxConstant;
import com.fancye.wechart.data.WxData;

@Controller
@RequestMapping("/wxtest")
public class WxController extends AbstractController {

	@RequestMapping("")
	public void api(HttpServletRequest request, HttpServletResponse response) {
		try {
			// URL ToUserName FromUserName  CreateTime  MsgType  Content  MsgId
			PrintWriter writer = response.getWriter();
//			String url = request.getParameter("URL");
			String url = "http://localhost:8080/fancye/weixin";
			String toUserName = request.getParameter("ToUserName");
			String fromUserName = request.getParameter("FromUserName");
			long createTime = new Date().getTime();
			String msgType = WxConstant.TEXT;
//			String msgId = request.getParameter("MsgId");
			String msgId = "123";
			
			WxData wxData = new WxData();
			wxData.setContent("1");
			wxData.setToUserName("toUserName");
			wxData.setFromUserName("fromUserName");
			wxData.setCreateTime(new Date());
			wxData.setMsgType(WxConstant.TEXT);
			wxData.setMsgId("123");
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("ToUserName", toUserName);
			map.put("FromUserName", fromUserName);
			map.put("CreateTime", String.valueOf(createTime));
			map.put("MsgType", msgType);
			map.put("Content", "1");
			map.put("msgId", msgId);
			
			String result = HttpXmlClient.post(url, map);
			
			writer.print(result);
//			ModelAndView mav = new ModelAndView();
//			mav.addObject("result", result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
