package com.fancye.wechart.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.util.IOUtils;
import com.fancye.wechart.WxConstant;
import com.fancye.wechart.data.WxData;
import com.fancye.wechart.process.Dispenser;
import com.fancye.wechart.process.ParseXMLData;
import com.fancye.wechart.process.Processor;
import com.fancye.wechart.util.SignUtil;

public class WechartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(WechartServlet.class);

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Wechart start");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter writer = response.getWriter();
		// 服务器开始时间
//        long starttime = System.currentTimeMillis();
        
		String signature = request.getParameter("signature");// 微信加密签名 
		String timestamp = request.getParameter("timestamp");// 时间戳  
		String nonce = request.getParameter("nonce");// 随机数
		String echostr = request.getParameter("echostr");// 随机字符串
		
		// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		if (!"".equals(echostr) && null != echostr && SignUtil.checkSignature(signature, timestamp, nonce)) {
			logger.info("微信公众号绑定成功 !success:" + echostr);
			writer.print(echostr);
		} else {
			logger.info("Wechart start message execute");
			//正常的微信处理流程
			WxData wxData = null;
			try {
				logger.info("stringBuilder : " + IOUtils.toString(request.getInputStream()));
				System.out.println("stringBuilder : " + IOUtils.toString(request.getInputStream()));
				if (IOUtils.toString(request.getInputStream()).equals("")) {
//					writer.write("success");
					
					// 手动设置回复文本消息,无论用户发出何种消息(仅供测试)
					WxData data = new WxData();
					data.setToUserName("fancyebai");// 开发者微信号
					data.setFromUserName("fanxin_363310763");// 发送方帐号（一个OpenID）
					data.setMsgType(WxConstant.TEXT);
					data.setContent("1");
					
					Processor processor = Dispenser.dispenserRequest(data);
					if (null != processor) {
		                String result = processor.process();
		                if (result != null && !"".equals(result)) {
		                    writer.print(result);
		                }
		            }
					
//					writer.write("<xml><ToUserName><![CDATA[fanxin_363310763]]></ToUserName>" +
//							"<FromUserName><![CDATA[fanxin_363310763]]></FromUserName>" +
//							"<CreateTime><![CDATA[2016-05-13 08:10:01.866 UTC]]></CreateTime>" +
//							"<MsgType><![CDATA[text]]></MsgType>" +
//							"<Content><![CDATA[helloFunny]]></Content></xml>");
					
				}else{
					wxData = ParseXMLData.parseXMLData(request.getInputStream(), new WxData());
					
					if (null == wxData) {
						writer.print("wxData is null");
					} else {
						Processor processor = Dispenser.dispenserRequest(wxData);
						if (null != processor) {
			                String result = processor.process();
			                if (result != null && !"".equals(result)) {
			                    writer.print(result);
			                }
			                // 整个时长
//			                long diffmillis = System.currentTimeMillis() - starttime;
			            }
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		writer.flush();
		writer.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
