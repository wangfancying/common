package com.wanghui.thread17;

import java.util.concurrent.CountDownLatch;


/**
 * ʹ��CountDownLatch
 * @author Administrator
 *
 */
public class UseCountDownLatch {

	public static void main(String[] args) {
		
		final CountDownLatch count = new CountDownLatch(2);
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("�����߳�t1" + "�ȴ������̴߳������...");
				try {
					count.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("t1�̼߳���ִ��...");
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("t2�߳̽��г�ʼ������...");
				try {
					Thread.sleep(3000);
					System.out.println("t2�̳߳�ʼ����ϣ�֪ͨt1�̼߳���...");
					count.countDown();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("t3�߳̽��г�ʼ������...");
				try {
					Thread.sleep(3000);
					System.out.println("t3�̳߳�ʼ����ϣ�֪ͨt1�̼߳���...");
					count.countDown();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();
		t3.start();
	}
}
