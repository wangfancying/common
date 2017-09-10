package com.wanghui.thread17;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * ʹ��UseCyclicBarrier
 * @author Administrator
 *
 */
public class UseCyclicBarrier {

	public static void main(String[] args) {
		
		CyclicBarrier barrier = new CyclicBarrier(3);
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(1000 * (new Random()).nextInt(5));  
					System.out.println("t1  ׼��OK.");
					barrier.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("t1  go.");
			}
		});
		

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(1000 * (new Random()).nextInt(5));  
					System.out.println("t2  ׼��OK.");
					barrier.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("t1  go.");
			}
		});
		

		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(1000 * (new Random()).nextInt(5));  
					System.out.println("t3  ׼��OK.");
					barrier.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("t1  go.");
			}
		});
		
		t1.start();
		t2.start();
		t3.start();
	}
}