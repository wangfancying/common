package com.wanghui.thread20;

import java.nio.ByteBuffer;

import com.lmax.disruptor.RingBuffer;

public class LongEventProvider {

	private final RingBuffer<LongEvent> ringBuffer;

	public LongEventProvider(RingBuffer<LongEvent> ringBuffer) {
		super();
		this.ringBuffer = ringBuffer;
	}

	/**
	 * onData用来发布事件，每调用一次就发布一次事件
	 * 它的参数会用过事件传递给消费者
	 */
	public void onData(ByteBuffer buffer){
		
		long squence = ringBuffer.next();	//1.可以把ringBuffer看做一个事件队列，那么next就是得到下面一个事件槽
		try{
			LongEvent event = ringBuffer.get(squence);	//用上面的索引取出一个空的事件用于填充（获取该序号对应的事件对象）
			event.setValue(buffer.getLong(0));		//获取要通过事件传递的业务数据
		}finally{
			ringBuffer.publish(squence);			//发布事件,后的 ringBuffer.publish 方法必须包含在 finally 中以确保必须得到调用；如果某个请求的 sequence 未被提交，将会堵塞后续的发布操作或者其它的 producer。
		}
	}
	
}
