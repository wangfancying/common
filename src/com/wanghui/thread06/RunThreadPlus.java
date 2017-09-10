package com.wanghui.thread06;

import com.wanghui.thread10.Singletion;

public class RunThreadPlus {

	public static void main(String[] args) throws InterruptedException {
		
		Singletion s = Singletion.getInstance();
		
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("线程1执行");
				while(s.isRunning){
					
				}
				System.out.println("线程2停止");
			}
		});
		t.start();
		Thread.sleep(1000);
		s.isRunning = false;
		System.out.println("isRunning的值设置为了false");
	}
}
