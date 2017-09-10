package com.wanghui.thread20;

import java.nio.ByteBuffer;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;


/**
 * provider 2
 * Disruptor 3.0�ṩ��lambdaʽ��API���������԰�һЩ���ӵĲ�������Ring Buffer��
 * ������Disruptor3.0�Ժ�İ汾���ʹ��Event Publisher����Event Translator�������¼�
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
