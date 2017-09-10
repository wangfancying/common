package com.wanghui.thread16;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ʹ��ThreadPoolExecutor��
 * @author Administrator
 *
 */
public class UseThreadPoolExecutor1 {

	public static void main(String[] args) {
		
		/**
		 * ��ʹ���н����ʱ�������µ�������Ҫִ�У�����̳߳�ʵ���߳���С��corePoolSize�������ȴ����̣߳�
		 * ������corePoolSize����Ὣ���������У�
		 * �������������������߳���������maximumPoolSize��ǰ���£������µ��̣߳�
		 * ���߳�������maximumPoolSize����ִ�оܾ����ԡ��������Զ��巽ʽ��
		 * 
		 */
		ThreadPoolExecutor pool = new ThreadPoolExecutor(
				1, 
				2, 
				60, 
				TimeUnit.SECONDS, 
				new ArrayBlockingQueue<Runnable>(3));
		Task task1 = new Task(1, "����1");
		Task task2 = new Task(1, "����2");
		Task task3 = new Task(1, "����3");
		Task task4 = new Task(1, "����4");
		Task task5 = new Task(1, "����5");
		Task task6 = new Task(1, "����6");
		
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