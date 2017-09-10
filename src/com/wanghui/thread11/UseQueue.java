package com.wanghui.thread11;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;


/**
 * ConcurrentLinkedQueue ArrayBlockingQueue  SynchronousQueue的使用
 * @author Administrator
 *
 */
public class UseQueue {

	public static void main(String[] args) throws InterruptedException {
		
		/*ConcurrentLinkedQueue<String> q = new ConcurrentLinkedQueue<>();
		q.offer("a");
		q.offer("b");
		q.offer("c");
		q.offer("d");
		q.add("e");
		
		System.out.println(q.poll());	//a 从头部取出元素，并从队列里删除
		System.out.println(q.size());	//4
		System.out.println(q.peek());	//b
		System.out.println(q.size());	//4
*/		
		
		/*ArrayBlockingQueue<String> array = new ArrayBlockingQueue<String>(5);
		array.put("a");
		array.put("b");
		array.add("c");
		array.add("d");
		array.add("e");
		System.out.println(array.offer("a", 3, TimeUnit.SECONDS));*/
		
		SynchronousQueue<String> q = new SynchronousQueue<>();
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println(q.take());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				q.add("asdasd");
			}
		});
		
		t1.start();
		Thread.sleep(1000);
		t2.start();	
		
		
	}
}
