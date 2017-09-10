package com.wanghui.thread08;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ʵ���������� BlockingQueue
 * @author Administrator
 *
 */
public class MyQueue {

	//1 ��Ҫһ����װԪ�صļ���
	private final LinkedList<Object> list = new LinkedList<>();
	
	//2 ��Ҫһ��������
	private AtomicInteger count = new AtomicInteger(0);
	
	//3 ��Ҫ�ƶ����޺�����
	private final int minSize = 0;
	private final int maxSize;
	
	//4 ���췽��
	public MyQueue(int maxSize) {
		super();
		this.maxSize = maxSize;
	}
	
	//5 ��ʼ��һ������ ���ڼ���
	private final Object lock = new Object();
	
	//��anObject�ӵ�BlockingQueue��,���BlockQueueû�пռ�,����ô˷������̱߳���ϣ�ֱ��BlockingQueue�����пռ��ټ���.
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
			list.add(obj);	//����Ԫ��
			count.incrementAndGet();	//�������ۼ�
			lock.notify();	//֪ͨ����һ���̣߳����ѣ�
			System.out.println("�¼����Ԫ��Ϊ:" + obj);
		}
	}
	
	//ȡ��BlockingQueue��������λ�Ķ���,��BlockingQueueΪ��,��Ͻ���ȴ�״ֱ̬��BlockingQueue���µ����ݱ�����.
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
			result = list.removeFirst();	//���Ƴ�Ԫ�ز���
			count.decrementAndGet();	//�������ݼ�
			lock.notify();	//��������һ���߳�
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
		System.out.println("��ǰ�����ĳ���:" + mq.getSize());
		
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
				System.out.println("�Ƴ���Ԫ��Ϊ:" + o1);
				Object o2 = mq.get();
				System.out.println("�Ƴ���Ԫ��Ϊ:" + o2);
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
	