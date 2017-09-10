package com.wanghui.thread17;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * ʹ�� callable��FutureTask
 * @author Administrator
 *
 */
public class UseFeture implements Callable<String>{
	
	private String query;
	
	public UseFeture(String query) {
		super();
		this.query = query;
	}
	
	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("��ʼquery");
		Thread.sleep(2000);
		String result = this.query + "�������";
		return result;
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		String query = "query";
		FutureTask<String> task = new FutureTask<>(new UseFeture(query));
		ExecutorService pool = Executors.newFixedThreadPool(1);
		Future f = pool.submit(task);
		try {
			//�����������������ݲ�����Ҳ����������ִ������ҵ���߼�
			System.out.println("����ʵ�ʵ�ҵ���߼�...");
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//���û�ȡ���ݷ���,���call()����û��ִ�����,����Ȼ����еȴ�
		System.out.println("���ݣ�" + task.get());
		System.out.println("���ݣ�" + f.get());
		pool.shutdown();
		
	}
}
