package com.wanghui.thread21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.IgnoreExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.WorkerPool;
import com.lmax.disruptor.YieldingWaitStrategy;


/**
 * ʹ��ringBuffer��WorkerPool
 * @author Administrator
 *
 */
public class Main2 {

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
		
		//����workpool
		WorkerPool<Trade> workPool = new WorkerPool<>(ringBuffer, barrier, new IgnoreExceptionHandler(),new TradeHandler());
		
		workPool.start(executor);
		
		//�����������8������
        for(int i=0;i<8;i++){  
            long seq=ringBuffer.next();  
            ringBuffer.get(seq).setPrice(Math.random()*9999);  
            ringBuffer.publish(seq);  
        }  
          
        Thread.sleep(1000);  
        workPool.halt();  
        executor.shutdown(); 
	}
}
