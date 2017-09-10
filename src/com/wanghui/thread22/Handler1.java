package com.wanghui.thread22;

import com.lmax.disruptor.EventHandler;
import com.wanghui.thread21.Trade;

public class Handler1 implements EventHandler<Trade> {

	@Override
	public void onEvent(Trade event, long arg1, boolean arg2) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("handler1: set name");
		event.setName("h1");
    	Thread.sleep(1000);
	}
}
