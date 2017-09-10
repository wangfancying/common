package com.wanghui.thread08;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 实现阻塞队列 BlockingQueue
 * @author Administrator
 *
 */
public class MyQueue {

	//1 需要一个承装元素的集合
	private final LinkedList<Object> list = new LinkedList<>();
	
	//2 需要一个计数器
	private AtomicInteger count = new AtomicInteger(0);
	
	//3 需要制定上限和下限
	private final int minSize = 0;
	private final int maxSize;
	
	//4 构造方法
	public MyQueue(int maxSize) {
		super();
		this.maxSize = maxSize;
	}
	
	//5 初始化一个对象 用于加锁
	private final Object lock = new Object();
	
	//把anObject加到BlockingQueue里,如果BlockQueue没有空间,则调用此方法的线程被阻断，直到BlockingQueue里面有空间再继续.
	public void put(Object obj){
		synchronized (lock) {
			while(count.get() == this.maxSize){
				try {
					lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			list.add(obj);	//加入元素
			count.incrementAndGet();	//计数器累加
			lock.notify();	//通知另外一个线程（唤醒）
			System.out.println("新加入的元素为:" + obj);
		}
	}
	
	//取走BlockingQueue里排在首位的对象,若BlockingQueue为空,阻断进入等待状态直到BlockingQueue有新的数据被加入.
	public Object get(){
		Object result = null;
		synchronized (lock) {
			while(count.get() == this.minSize){
				try {
					lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			result = list.removeFirst();	//做移除元素操作
			count.decrementAndGet();	//计数器递减
			lock.notify();	//唤醒另外一个线程
			return result;
		}
	}
	
	public int getSize(){
		return this.list.size();
	}
	
	public static void main(String[] args) {
		
		final MyQueue mq = new MyQueue(5);
		mq.put("a");
		mq.put("a");
		mq.put("a");
		mq.put("a");
		mq.put("a");
		System.out.println("当前容器的长度:" + mq.getSize());
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				mq.put("f");
				mq.put("g");
			}
		},"t1");
		
		t1.start();
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				Object o1 = mq.get();
				System.out.println("移除的元素为:" + o1);
				Object o2 = mq.get();
				System.out.println("移除的元素为:" + o2);
			}
		},"t2");
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		t2.start();
	}
}
	