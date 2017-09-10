package com.wanghui.thread07;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * ʹ��CountDownLatch
 * @author Administrator
 * 2017��8��19��11:24:32
 */
public class ListAdd2 {
	
	private volatile static List list = new ArrayList<>();

	public void add() {
		list.add("test");
	}

	public int size() {
		return list.size();
	}
	
	public static void main(String[] args) {

		final ListAdd1 list1 = new ListAdd1();
		final CountDownLatch countDownLatch = new CountDownLatch(1);

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
						for (int i = 0; i < 10; i++) {
							list1.add();
							System.out.println("��ǰ�̣߳�" + Thread.currentThread().getName() + "�����һ��Ԫ��..");
							Thread.sleep(500);
							if (list1.size() == 5) {
								System.out.println("�Ѿ�����֪ͨ");
								countDownLatch.countDown();
							}
						}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t1");

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
					if (list1.size() != 5) {
						try {
							countDownLatch.await();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					System.out.println("��ǰ�̣߳�" + Thread.currentThread().getName() + "�յ�֪ͨ�߳�ֹͣ..");
					throw new RuntimeException();
			}
		}, "t2");

		t2.start();
		t1.start();
	}
}
