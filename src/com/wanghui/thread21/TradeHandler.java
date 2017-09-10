package com.wanghui.thread21;

import java.util.UUID;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

public class TradeHandler implements EventHandler<Trade>, WorkHandler<Trade>{

	@Override
	public void onEvent(Trade arg0) throws Exception {
		// TODO Auto-generated method stub
		arg0.setId(UUID.randomUUID().toString());
		System.out.println(arg0.getId());
	}

	@Override
	public void onEvent(Trade arg0, long arg1, boolean arg2) throws Exception {
		// TODO Auto-generated method stub
		this.onEvent(arg0);
	}
	
}
