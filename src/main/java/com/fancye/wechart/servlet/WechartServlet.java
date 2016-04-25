package com.fancye.wechart.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.chanjar.weixin.common.util.StringUtils;

import com.fancye.wechart.WxConstant;
import com.fancye.wechart.util.SignUtil;

public class WechartServlet extends HttpServlet {

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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		/** 读取接收到的xml消息 */
		StringBuffer sb = new StringBuffer();
		InputStream is = request.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String s = "";
		while ((s = br.readLine()) != null) {
			sb.append(s);
		}
		String xml = sb.toString();	// 此即为接收到微信端发送过来的xml数据

		// 微信加密签名 
		String signature = request.getParameter("signature");
		// 时间戳  
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");
		PrintWriter out = response.getWriter();
		
		// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		} else {
			//正常的微信处理流程
//			result = new WechatProcess().processWechatMag(xml);
		}

		out.flush();
		out.close();
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
	
	private String exec(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter writer = response.getWriter();
            // 服务器开始时间
//            long starttime = System.currentTimeMillis();

            String signature = request.getParameter("signature");
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");
            String echostr = request.getParameter("echostr");
            String token = WxConstant.TOKEN;
            String[] str = {token, timestamp, nonce};
            Arrays.sort(str); // 字典序排序
            String bigStr = str[0] + str[1] + str[2];

            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(bigStr.getBytes());
            byte[] b = md.digest();

            StringBuilder sbDes = new StringBuilder();
            String tmp;
            for (int i = 0; i < b.length; i++) {
                tmp = (Integer.toHexString(b[i] & 0xFF));
                if (tmp.length() == 1) {
                    sbDes.append("0");
                }
                sbDes.append(tmp);
            }
            String digest = sbDes.toString();

            // 确认请求来至微信
            if (digest.equals(signature)) {
                // 认证 绑定功能
                if (StringUtils.isNotEmpty(echostr)) {
                    writer.print(echostr);
                    // 绑定不需再往后走
                    return echostr;
                }

                /** 读取接收到的xml消息 */
        		StringBuffer sb = new StringBuffer();
        		InputStream is = request.getInputStream();
        		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
        		BufferedReader br = new BufferedReader(isr);
        		String s = "";
        		while ((s = br.readLine()) != null) {
        			sb.append(s);
        		}
        		String xml = sb.toString();	// 此即为接收到微信端发送过来的xml数据
            } else {
                System.out.println("error:" + echostr);
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
		return "success";
    }

}
