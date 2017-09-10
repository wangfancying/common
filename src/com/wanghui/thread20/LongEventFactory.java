package com.wanghui.thread20;

import com.lmax.disruptor.EventFactory;

public class LongEventFactory implements EventFactory{

	@Override
	public Object newInstance() {
		// TODO Auto-generated method stub
		return new LongEvent();
	}
	
}
