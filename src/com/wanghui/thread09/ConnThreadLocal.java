package com.wanghui.thread09;


/**
 * 使用ThreadLocal
 * @author Administrator
 *
 */
public class ConnThreadLocal {
	
	/**
	 * ThreadLocal : 线程局部变量，为每个线程提供变量的副本，保证线程安全，以空间换时间的手段
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
				ct.setTh("张三");
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
