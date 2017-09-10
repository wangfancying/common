package com.wanghui.thread16;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 使用ThreadPoolExecutor 
 * @author Administrator
 *
 */
public class UseThreadPoolExecutor2 {

	public static void main(String[] args) {
		
		/**
		 * 在使用无界队列时，若有新的任务需要执行，如果线程池实际线程数小于corePoolSize，则优先创建线程，
		 * 若大于corePoolSize，则会将任务加入队列，
		 * 若大于corePoolSize，会放入无界队列，无界队列会随着线程增加而扩容，直到内存耗尽
		 * 
		 */	
		ThreadPoolExecutor pool = new ThreadPoolExecutor(
				1, 
				2, 
				60, 
				TimeUnit.SECONDS, 
				new LinkedBlockingQueue<Runnable>());
		Task task1 = new Task(1, "任务1");
		Task task2 = new Task(1, "任务2");
		Task task3 = new Task(1, "任务3");
		Task task4 = new Task(1, "任务4");
		Task task5 = new Task(1, "任务5");
		Task task6 = new Task(1, "任务6");
		
		pool.execute(task1);
		pool.execute(task2);
		pool.execute(task3);
		pool.execute(task4);
		pool.execute(task5);
		pool.execute(task6);
		
		pool.shutdown();
	}
}