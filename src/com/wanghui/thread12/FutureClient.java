package com.wanghui.thread12;

public class FutureClient {

	public Data request(String request){
		final FutureData futureData = new FutureData();
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				RealData realData = new RealData(request);
				futureData.setRealData(realData);
			}
		}).start();
		return futureData;
	}
}
