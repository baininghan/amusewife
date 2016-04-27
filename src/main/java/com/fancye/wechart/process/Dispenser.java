package com.fancye.wechart.process;

import com.fancye.wechart.data.WxData;

public class Dispenser {

	public static Processor dispenserRequest(WxData wxData) {
		return new Processor(wxData);
	}

}
