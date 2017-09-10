package com.wanghui.thread09;


/**
 * ʹ��ThreadLocal
 * @author Administrator
 *
 */
public class ConnThreadLocal {
	
	/**
	 * ThreadLocal : �ֲ߳̾�������Ϊÿ���߳��ṩ�����ĸ�������֤�̰߳�ȫ���Կռ任ʱ����ֶ�
	 */
	public static ThreadLocal<String> th = new ThreadLocal<>();

	public void setTh(String value){
		th.set(value);
	}
	public void getTh(){
		System.out.println(Thread.currentThread().getName() + ":" + this.th.get());
	}
	
	public static void main(String[] args) {
		
		final ConnThreadLocal ct = new ConnThreadLocal();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				ct.setTh("����");
				ct.getTh();
			}
		}, "t1");
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					ct.getTh();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t2");
		
		t1.start();
		t2.start();
	}
	
}
