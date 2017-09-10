package com.wanghui.thread22;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.wanghui.thread21.Trade;


/**
 * disruptor菱形操作
 * @author Administrator
 *
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		long beginTime=System.currentTimeMillis();  
		int bufferSize=1024;  
		
		ExecutorService executorService = Executors.newFixedThreadPool(4);
		
		Disruptor<Trade> disruptor = new Disruptor<>(new EventFactory<Trade>() {
			 public Trade newInstance() {  
	                return new Trade();  
	            }  
		}, bufferSize, executorService);
		
		//使用disruptor创建消费者组C1,C2  
        EventHandlerGroup<Trade> handlerGroup = 
        		disruptor.handleEventsWith(new Handler1(), new Handler2());
        //声明在C1,C2完事之后执行JMS消息发送操作 也就是流程走到C3 
        handlerGroup.then(new Handler3());
        
        disruptor.start();//启动  
        CountDownLatch latch=new CountDownLatch(1);  
        //生产者准备  
        executorService.submit(new TradePublisher(latch, disruptor));
        
        latch.await();//等待生产者完事. 
       
        disruptor.shutdown();  
        executorService.shutdown();  
        System.out.println("总耗时:"+(System.currentTimeMillis()-beginTime));  
        
	}
	
}
