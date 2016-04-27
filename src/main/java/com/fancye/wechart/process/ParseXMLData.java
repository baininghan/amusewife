package com.fancye.wechart.process;

import java.io.InputStream;
import java.util.Date;
import java.util.Map;

import com.fancye.wechart.WxConstant;
import com.fancye.wechart.data.WxData;
import com.fancye.wechart.util.WeixinUtil;

public class ParseXMLData {

	/**
	 * 解析请求流信息，将数据保存进Map
	 * 
	 * @param inputStream 请求流
	 * @param wxData	 请求对象
	 * @return
	 */
	public static WxData parseXMLData(InputStream inputStream, WxData wxData) {
		return mapToWxData(WeixinUtil.parseXml(inputStream));
	}
	
	/**
	 * 存储数据的Map转换为对应的WxData对象
	 * 
	 * @param map 存储数据的map
	 * @return 返回对应WxData对象
	 */
	public static WxData mapToWxData(Map<String, String> map) {
		if (map == null)return null;
		
		WxData WxData = new WxData();
		WxData.setToUserName(map.get("ToUserName"));
		WxData.setFromUserName(map.get("FromUserName"));
		WxData.setCreateTime(new Date(Long.parseLong(map.get("CreateTime"))));
		String msgType = map.get("MsgType");
		WxData.setMsgType(msgType);
		WxData.setMsgId(map.get("MsgId"));
		if (msgType.equals(WxConstant.TEXT)) {
			WxData.setContent(map.get("Content"));
		} else if (msgType.equals(WxConstant.IMAGE)) {
			WxData.setPicUrl(map.get("PicUrl"));
		} else if (msgType.equals(WxConstant.LINK)) {
			WxData.setTitle(map.get("Title"));
			WxData.setDescription(map.get("Description"));
			WxData.setUrl(map.get("Url"));
		} else if (msgType.equals(WxConstant.LOCATION)) {
			WxData.setLocationX(map.get("Location_X"));
			WxData.setLocationY(map.get("Location_Y"));
			WxData.setScale(map.get("Scale"));
			WxData.setLabel(map.get("Label"));
		} else if (msgType.equals(WxConstant.EVENT)) {
			WxData.setEvent(map.get("Event"));
			WxData.setEventKey(map.get("EventKey"));
		}
		return WxData;
	}

}
