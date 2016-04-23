package com.fancye.wechart.util;

import java.io.IOException;
import java.util.Properties;

/**
 * 
 * PropertiesUtil.java
 * 
 * @desc properties 资源文件解析工具
 * @author Guoxp
 * @datatime Apr 7, 2013 3:58:45 PM
 * 
 */
public class PropertiesUtil {
	
	private PropertiesUtil propUtil;
	private Properties prop;
	
	public PropertiesUtil(){
		
	}
	
	public PropertiesUtil getInstence(){
		if(propUtil == null) {
			propUtil = new PropertiesUtil();
			prop = new Properties();
		}
		
		return propUtil;
	}
	
	public void load(String propertiesName) throws IOException {
		prop.load(this.getClass().getResourceAsStream(propertiesName));
	}
	
	public String getProperty(String key) {
		return prop.getProperty(key);
	}
	
}
