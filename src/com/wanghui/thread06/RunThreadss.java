package com.wanghui.thread06;


/**
 * ʹ��volatile�ؼ���
 * @author Administrator
 *
 */
public class RunThreadss {

	private volatile boolean isRunning = true;
	
	public void setRunning(boolean isRunning){
		this.isRunning = isRunning;
	}
	
	public synchronized void run(){
		System.out.println("����run����...");
		while(isRunning == true){
			
		}
		System.out.println("�߳�ֹͣ");
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		RunThreadss rt = new RunThreadss();
		
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				rt.run();
			}
		});
		t.start();
		Thread.sleep(1000);
		rt.setRunning(false);
		System.out.println("isRunning��ֵ�Ѿ���������false");
		System.out.println(rt.isRunning);
	}
}
