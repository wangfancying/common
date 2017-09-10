package com.wanghui.thread22;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.wanghui.thread21.Trade;


/**
 * disruptor���β���
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
		
		//ʹ��disruptor������������C1,C2  
        EventHandlerGroup<Trade> handlerGroup = 
        		disruptor.handleEventsWith(new Handler1(), new Handler2());
        //������C1,C2����֮��ִ��JMS��Ϣ���Ͳ��� Ҳ���������ߵ�C3 
        handlerGroup.then(new Handler3());
        
        disruptor.start();//����  
        CountDownLatch latch=new CountDownLatch(1);  
        //������׼��  
        executorService.submit(new TradePublisher(latch, disruptor));
        
        latch.await();//�ȴ�����������. 
       
        disruptor.shutdown();  
        executorService.shutdown();  
        System.out.println("�ܺ�ʱ:"+(System.currentTimeMillis()-beginTime));  
        
	}
	
}
