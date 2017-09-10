package com.wanghui.thread06;


/**
 * ʹ��volatile�ؼ���
 * @author Administrator
 *
 */
public class RunThreads extends Thread{

	//volatile
	private  Run run = new Run(true);
	
	public void run(){
		System.out.println("����run����...");
		while(run.isRunning() == true){
			
		}
		System.out.println("�߳�ֹͣ");
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		RunThreads rt = new RunThreads();
		rt.start();
		Thread.sleep(1000);
		rt.run.setRunning(false);
		System.out.println("isRunning��ֵ�Ѿ���������false");
	}
}

class Run{
	private volatile boolean isRunning;

	public Run(boolean isRunning) {
		super();
		this.isRunning = isRunning;
	}

	public Run() {
		super();
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
}
