package com.wanghui.thread12;


/**
 * 实现future模式
 * @author Administrator
 *
 */
public class Main {

	public static void main(String[] args) throws InterruptedException{
		
		FutureClient client = new FutureClient();
		Data data = client.request("ceshi");
		System.out.println("请求发送成功");
		System.out.println("做其他的事情...");
		//Thread.sleep(5000);
		System.out.println("开始查询结果");
		String result = data.getRequest();
		System.out.println(result);
	}
}
