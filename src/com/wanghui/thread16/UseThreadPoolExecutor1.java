package com.wanghui.thread16;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 使用ThreadPoolExecutor类
 * @author Administrator
 *
 */
public class UseThreadPoolExecutor1 {

	public static void main(String[] args) {
		
		/**
		 * 在使用有界队列时，若有新的任务需要执行，如果线程池实际线程数小于corePoolSize，则优先创建线程，
		 * 若大于corePoolSize，则会将任务加入队列，
		 * 若队列已满，则在总线程数不大于maximumPoolSize的前提下，创建新的线程，
		 * 若线程数大于maximumPoolSize，则执行拒绝策略。或其他自定义方式。
		 * 
		 */
		ThreadPoolExecutor pool = new ThreadPoolExecutor(
				1, 
				2, 
				60, 
				TimeUnit.SECONDS, 
				new ArrayBlockingQueue<Runnable>(3));
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
class Task implements Runnable {
	private int taskId;
	private String taskName;

	public Task(int taskId, String taskName) {
		super();
		this.taskId = taskId;
		this.taskName = taskName;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			System.out.println("run taskId =" + this.taskId);
			Thread.sleep(5 * 1000);
			// System.out.println("end taskId =" + this.taskId);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}