package com.wanghui.thread07;

import java.util.ArrayList;
import java.util.List;


/**
 * wait notify 
 * wait 	释放锁
 * notify 	不释放锁
 * @author Administrator
 * 2017年8月19日11:24:41
 */
public class ListAdd {

	private volatile static List list = new ArrayList<>();

	public void add() {
		list.add("test");
	}

	public int size() {
		return list.size();
	}

	public static void main(String[] args) {

		final ListAdd1 list1 = new ListAdd1();
		final Object lock = new Object();

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					synchronized (lock) {
						for (int i = 0; i < 10; i++) {
							list1.add();
							System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素..");
							Thread.sleep(500);
							if (list1.size() == 5) {
								lock.notify();
								System.out.println("已经发出通知");
							}
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
				synchronized (lock) {
					if (list1.size() != 5) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					System.out.println("当前线程：" + Thread.currentThread().getName() + "收到通知线程停止..");
					throw new RuntimeException();
				}
			}
		}, "t2");

		t2.start();
		t1.start();
	}
}
