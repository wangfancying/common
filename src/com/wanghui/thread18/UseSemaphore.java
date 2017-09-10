package com.wanghui.thread18;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class UseSemaphore {

	public static void main(String[] args) {
		
		ExecutorService pool = Executors.newCachedThreadPool();
		Semaphore sema = new Semaphore(3);
		
		for(int i=0;i<6;i++){
			final int Id = i;
 			Runnable run = new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						sema.acquire();
						 System.out.println("Accessing: " + Id);
						 Thread.sleep((long) (Math.random() * 1000));  
	                        // 访问完后，释放  
	                        sema.release();  
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			};
			pool.execute(run);
		}
		try {
				Thread.sleep(10);
		} catch (InterruptedException e) {
				e.printStackTrace();
		} 
		pool.shutdown();  
	}
}
