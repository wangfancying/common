package com.wanghui.thread06;

import com.wanghui.thread10.Singletion;

public class RunThreadPlus {

	public static void main(String[] args) throws InterruptedException {
		
		Singletion s = Singletion.getInstance();
		
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("�߳�1ִ��");
				while(s.isRunning){
					
				}
				System.out.println("�߳�2ֹͣ");
			}
		});
		t.start();
		Thread.sleep(1000);
		s.isRunning = false;
		System.out.println("isRunning��ֵ����Ϊ��false");
	}
}
