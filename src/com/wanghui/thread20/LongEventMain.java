package com.wanghui.thread20;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;


/**
 * disruptor��ʹ��
 * @author Administrator
 *
 */
public class LongEventMain {

	public static void main(String[] args) {
		
		//���������
		ExecutorService executor = Executors.newCachedThreadPool();
		
		//��������
		LongEventFactory factory = new LongEventFactory();
		
		//����ringBuffer��С
		int ringBufferSize = 1024*1024;
		
		/**
		//BlockingWaitStrategy �����Ч�Ĳ��ԣ������CPU��������С�����ڸ��ֲ�ͬ���𻷾������ṩ����һ�µ����ܱ���
		WaitStrategy BLOCKING_WAIT = new BlockingWaitStrategy();
		//SleepingWaitStrategy �����ܱ��ָ�BlockingWaitStrategy��࣬��CPU������Ҳ���ƣ�������������̵߳�Ӱ����С���ʺ������첽��־���Ƶĳ���
		WaitStrategy SLEEPING_WAIT = new SleepingWaitStrategy();
		//YieldingWaitStrategy ����������õģ��ʺ����ڵ��ӳٵ�ϵͳ����Ҫ�󼫸��������¼���������С��CPU�߼��������ĳ����У��Ƽ�ʹ�ô˲��ԣ����磬CPU�������̵߳�����
		WaitStrategy YIELDING_WAIT = new YieldingWaitStrategy();
		*/
		//����disruptor
		Disruptor<LongEvent> disruptor = new Disruptor<>(factory, ringBufferSize, executor, ProducerType.SINGLE, new YieldingWaitStrategy());
		
		//���������¼�
		disruptor.handleEventsWith(new LongEventHandler());
		
		//����
		disruptor.start();
		
		//�����¼�
		RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();	//��ȡringBuffer
		//LongEventProvider provider = new LongEventProvider(ringBuffer);
		LongEventProducerWithTranslator provider = new LongEventProducerWithTranslator(ringBuffer);
		ByteBuffer buffer = ByteBuffer.allocate(8);
		for(long l = 0; l<100; l++){
			buffer.putLong(0, l);
			provider.onData(buffer);
			//Thread.sleep(1000);
		}
		disruptor.shutdown();//�ر� disruptor�������������ֱ�����е��¼����õ�����
		executor.shutdown();//�ر� disruptor ʹ�õ��̳߳أ������Ҫ�Ļ��������ֶ��رգ� disruptor �� shutdown ʱ�����Զ��رգ�	
		
	}
}
