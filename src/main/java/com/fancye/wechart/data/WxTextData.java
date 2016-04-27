package com.fancye.wechart.data;

/**
 * 接收普通消息：文本消息
 * <xml>
	 <ToUserName><![CDATA[toUser]]></ToUserName>
	 <FromUserName><![CDATA[fromUser]]></FromUserName>
	 <CreateTime>1348831860</CreateTime>
	 <MsgType><![CDATA[text]]></MsgType>
	 <Content><![CDATA[this is a test]]></Content>
	 <MsgId>1234567890123456</MsgId>
	 </xml>
 * @author Fancye
 *
 */
public class WxTextData {

	private String toUserName;
	private String fromUserName;
	private String createTime;
	private String msgType;
	private String content;
	
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}
