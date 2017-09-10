package com.wanghui.thread22;

import com.lmax.disruptor.EventHandler;
import com.wanghui.thread21.Trade;

public class Handler2 implements EventHandler<Trade> {

	@Override
	public void onEvent(Trade event, long arg1, boolean arg2) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("handler2: set price");
    	event.setPrice(17.0);
    	Thread.sleep(1000);
	}
}
