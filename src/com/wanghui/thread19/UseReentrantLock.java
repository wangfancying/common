package com.wanghui.thread19;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * ʹ��ReentrantLock
 * @author Administrator
 *
 */
public class UseReentrantLock {

	private Lock lock = new ReentrantLock();
	
	public void menthod1(){
		
		try{
			lock.lock();
			System.out.println("��ǰ�߳�:" + Thread.currentThread().getName() + "����method1..");
			Thread.sleep(1000);
			System.out.println("��ǰ�߳�:" + Thread.currentThread().getName() + "�˳�method1..");
			Thread.sleep(1000);
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			lock.unlock();
		}
	}
	
	public void method2(){
		try {
			lock.lock();
			System.out.println("��ǰ�߳�:" + Thread.currentThread().getName() + "����method2..");
			Thread.sleep(2000);
			System.out.println("��ǰ�߳�:" + Thread.currentThread().getName() + "�˳�method2..");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		final UseReentrantLock ur = new UseReentrantLock();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				ur.menthod1();
				ur.method2();
			}
		}, "t1");
		
		t1.start();
	}
}
