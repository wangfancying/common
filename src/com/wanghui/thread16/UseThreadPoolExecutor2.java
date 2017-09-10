package com.wanghui.thread16;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * ʹ��ThreadPoolExecutor 
 * @author Administrator
 *
 */
public class UseThreadPoolExecutor2 {

	public static void main(String[] args) {
		
		/**
		 * ��ʹ���޽����ʱ�������µ�������Ҫִ�У�����̳߳�ʵ���߳���С��corePoolSize�������ȴ����̣߳�
		 * ������corePoolSize����Ὣ���������У�
		 * ������corePoolSize��������޽���У��޽���л������߳����Ӷ����ݣ�ֱ���ڴ�ľ�
		 * 
		 */	
		ThreadPoolExecutor pool = new ThreadPoolExecutor(
				1, 
				2, 
				60, 
				TimeUnit.SECONDS, 
				new LinkedBlockingQueue<Runnable>());
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