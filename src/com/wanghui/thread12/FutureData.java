package com.wanghui.thread12;

public class FutureData implements Data{
	
	private RealData realData;
	
	private boolean isReady = false;
	
	public synchronized void setRealData(RealData realData){
		
		if(isReady){
			return;
		}
		this.realData = realData;
		isReady = true;
		notify();
	}
	
	@Override
	public synchronized String getRequest() {
		while(!isReady){
			try {
				System.out.println("真实数据没有加载结束，线程等待...");
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return this.realData.getRequest();
	}
}
