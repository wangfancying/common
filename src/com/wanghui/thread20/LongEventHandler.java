package com.wanghui.thread20;

import com.lmax.disruptor.EventHandler;

public class LongEventHandler implements EventHandler<LongEvent>{

	@Override
	public void onEvent(LongEvent longEvent, long arg1, boolean arg2) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(longEvent.getValue()); 
	}

}
