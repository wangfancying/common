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
 * 使用disruptor的ringBuffer和BatchEventProcessor
 * @author Administrator
 *
 */
public class Main1 {

	public static void main(String[] args) throws InterruptedException {
		
		int bufferSize = 1024*1024;
		int threadNum = 4;
		
		//创建ringBuffer
		final RingBuffer<Trade> ringBuffer = RingBuffer.createSingleProducer(new EventFactory<Trade>() {
			public Trade newInstance() {  
                return new Trade();  
            }  
		}, bufferSize,new YieldingWaitStrategy()); 
		
		//创建线程池
		ExecutorService executor = Executors.newFixedThreadPool(threadNum);
		
		//创建sequenceBarrier
		SequenceBarrier barrier = ringBuffer.newBarrier();
		
		//创建消息处理器
		BatchEventProcessor<Trade> transProcessor = new BatchEventProcessor<>(ringBuffer, barrier, new TradeHandler());
		
		//这一步的目的就是把消费者的位置信息引用注入到生产者    如果只有一个消费者的情况可以省略 
		ringBuffer.addGatingSequences(transProcessor.getSequence());
		
		executor.submit(transProcessor);
		
		
		//创建event
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
