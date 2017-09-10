package com.wanghui.thread20;

import java.nio.ByteBuffer;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;


/**
 * provider 2
 * Disruptor 3.0提供了lambda式的API。这样可以把一些复杂的操作放在Ring Buffer，
 * 所以在Disruptor3.0以后的版本最好使用Event Publisher或者Event Translator来发布事件
 * @author Administrator
 *
 */
public class LongEventProducerWithTranslator {

	private final RingBuffer<LongEvent> ringBuffer;

	public LongEventProducerWithTranslator(RingBuffer<LongEvent> ringBuffer) {
		super();
		this.ringBuffer = ringBuffer;
	}
	
	public void onData(ByteBuffer buffer){
		ringBuffer.publishEvent(translator,buffer);
	}
	
	private static final EventTranslatorOneArg<LongEvent, ByteBuffer> translator = new EventTranslatorOneArg<LongEvent, ByteBuffer>() {
		
		@Override
		public void translateTo(LongEvent arg0, long arg1, ByteBuffer arg2) {
			// TODO Auto-generated method stub
			arg0.setValue(arg2.getLong(0));
		}
	};
}
