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
	 * onData���������¼���ÿ����һ�ξͷ���һ���¼�
	 * ���Ĳ������ù��¼����ݸ�������
	 */
	public void onData(ByteBuffer buffer){
		
		long squence = ringBuffer.next();	//1.���԰�ringBuffer����һ���¼����У���ônext���ǵõ�����һ���¼���
		try{
			LongEvent event = ringBuffer.get(squence);	//�����������ȡ��һ���յ��¼�������䣨��ȡ����Ŷ�Ӧ���¼�����
			event.setValue(buffer.getLong(0));		//��ȡҪͨ���¼����ݵ�ҵ������
		}finally{
			ringBuffer.publish(squence);			//�����¼�,��� ringBuffer.publish ������������� finally ����ȷ������õ����ã����ĳ������� sequence δ���ύ��������������ķ����������������� producer��
		}
	}
	
}
