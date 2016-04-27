package com.fancye.wechart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fancye.common.AbstractController;

/**
 * 模拟微信订阅号，进行测试
 * 
 * @author Fancye
 *
 */
@Controller
@RequestMapping("/wxtest")
public class WxTestController extends AbstractController {

	@Override
	@RequestMapping("")
	public void api(HttpServletRequest request, HttpServletResponse response) {
		
	}

}
