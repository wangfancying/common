package com.wanghui.thread12;


/**
 * ʵ��futureģʽ
 * @author Administrator
 *
 */
public class Main {

	public static void main(String[] args) throws InterruptedException{
		
		FutureClient client = new FutureClient();
		Data data = client.request("ceshi");
		System.out.println("�����ͳɹ�");
		System.out.println("������������...");
		//Thread.sleep(5000);
		System.out.println("��ʼ��ѯ���");
		String result = data.getRequest();
		System.out.println(result);
	}
}
