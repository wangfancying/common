package com.wanghui.thread12;

public class RealData implements Data{

	private String result;
	
	public RealData(String request){
		System.out.println("����" + request + "���в�ѯ������һ���ܺ�ʱ�Ĳ���..");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("������ϣ���ȡ���");
		result = "��ѯ���";
	}
	@Override
	public String getRequest() {
		// TODO Auto-generated method stub
		return result;
	}

}
