package com.wanghui.thread22;

import com.lmax.disruptor.EventHandler;
import com.wanghui.thread21.Trade;

public class Handler3 implements EventHandler<Trade> {

	@Override
	public void onEvent(Trade event, long arg1, boolean arg2) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("handler3: name: " + event.getName() + " , price: " + event.getPrice() + ";  instance: " + event.toString());
	}

}
