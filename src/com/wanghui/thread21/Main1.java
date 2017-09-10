package com.wanghui.thread21;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.YieldingWaitStrategy;


/**
 * ʹ��disruptor��ringBuffer��BatchEventProcessor
 * @author Administrator
 *
 */
public class Main1 {

	public static void main(String[] args) throws InterruptedException {
		
		int bufferSize = 1024*1024;
		int threadNum = 4;
		
		//����ringBuffer
		final RingBuffer<Trade> ringBuffer = RingBuffer.createSingleProducer(new EventFactory<Trade>() {
			public Trade newInstance() {  
                return new Trade();  
            }  
		}, bufferSize,new YieldingWaitStrategy()); 
		
		//�����̳߳�
		ExecutorService executor = Executors.newFixedThreadPool(threadNum);
		
		//����sequenceBarrier
		SequenceBarrier barrier = ringBuffer.newBarrier();
		
		//������Ϣ������
		BatchEventProcessor<Trade> transProcessor = new BatchEventProcessor<>(ringBuffer, barrier, new TradeHandler());
		
		//��һ����Ŀ�ľ��ǰ������ߵ�λ����Ϣ����ע�뵽������    ���ֻ��һ�������ߵ��������ʡ�� 
		ringBuffer.addGatingSequences(transProcessor.getSequence());
		
		executor.submit(transProcessor);
		
		
		//����event
		executor.submit(new Callable<Void>() {
			public Void call() throws Exception {  
				long sqe;
				for(int i=0;i<10;i++){
					sqe = ringBuffer.next();
					Trade trade = ringBuffer.get(sqe);
					trade.setPrice(Math.random()*10);
					ringBuffer.publish(sqe);
				}
				return null;
			}
		});
		
		Thread.sleep(1000);
		transProcessor.halt();
		executor.shutdown();
	}
}
