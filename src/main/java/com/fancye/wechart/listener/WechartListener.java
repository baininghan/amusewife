package com.fancye.wechart.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.fancye.wechart.quartz.QuartzManager;
import com.fancye.wechart.quartz.WechartJob;

/**
 * 定时器的监听，并启动
 * 
 * @author Fancye
 *
 */
public class WechartListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		QuartzManager.addJob("funny", WechartJob.class,"0 0 9,22 * * ?"); // 每天22点执行一次
	}

}
