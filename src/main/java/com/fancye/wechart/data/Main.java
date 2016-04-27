package com.fancye.wechart.data;

import com.alibaba.fastjson.JSON;
import com.thoughtworks.xstream.XStream;

public class Main {

	private static final String STR_JSON = "{\"name\":\"Michael\",\"address\":{\"city\":\"Suzou\",\"street\":\" Changjiang Road \",\"postcode\":100025},\"blog\":\"http://www.ij2ee.com\"}";
	
    public static String xml2JSON(String xml){
    	XStream xstream = new XStream();
    	return JSON.toJSONString(xstream.fromXML(xml));
    }
     
    public static String json2XML(String json){
    	return new XStream().toXML(JSON.parse(json));
    }
     
    public static void main(String[] args) {
        String xml = json2XML(STR_JSON);
        System.out.println("xml = "+xml);
        String json = xml2JSON(xml);
        System.out.println("json="+json);
    }
}
