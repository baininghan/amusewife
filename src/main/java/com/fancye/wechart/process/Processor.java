package com.fancye.wechart.process;

import java.util.Date;

import com.fancye.wechart.WxConstant;
import com.fancye.wechart.data.Reply;
import com.fancye.wechart.data.WxData;
import com.fancye.wechart.util.WeixinUtil;

public class Processor {

	private WxData wxData;
	
	public Processor(WxData wxData) {
		this.wxData = wxData;
	}
	
	public String process() {
		//拼装回复消息
		Reply reply = new Reply();
		reply.setToUserName(wxData.getFromUserName());
		reply.setFromUserName(wxData.getToUserName());
		reply.setCreateTime(new Date());
		reply.setMsgType(Reply.TEXT);
		
		String content = "";
		String type = wxData.getMsgType();
		if (type.equals(WxConstant.TEXT)) {//仅处理文本回复内容
			if ("1".equals(wxData.getContent())) {
				content =  Funny.getFirstFunny();
			}
		}
		
		reply.setContent(content);
		
		return WeixinUtil.replyToXml(reply);
	}

}
