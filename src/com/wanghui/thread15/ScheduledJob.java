package com.wanghui.thread15;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 使用newScheduledThreadPool
 * @author Administrator
 *
 */
public class ScheduledJob {

	public static void main(String[] args) {
		
		Task task = new Task();
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		ScheduledFuture<?> scheduleTask = scheduler.scheduleWithFixedDelay(task, 2, 3, TimeUnit.SECONDS);//2秒后执行，每隔3轮询执行
		
	}
}

class Task implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("run...");
	}
	
}
