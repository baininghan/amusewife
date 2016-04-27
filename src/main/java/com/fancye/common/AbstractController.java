package com.fancye.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractController {
	
	public static Logger logger = LoggerFactory.getLogger(AbstractController.class);
	
	/**
	 * 微信入口
	 * 
	 * @param request
	 * @param response
	 */
	public abstract void api(HttpServletRequest request, HttpServletResponse response);

}
