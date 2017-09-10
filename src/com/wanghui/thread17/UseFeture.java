package com.wanghui.thread17;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 使用 callable和FutureTask
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
		System.out.println("开始query");
		Thread.sleep(2000);
		String result = this.query + "处理结束";
		return result;
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		String query = "query";
		FutureTask<String> task = new FutureTask<>(new UseFeture(query));
		ExecutorService pool = Executors.newFixedThreadPool(1);
		Future f = pool.submit(task);
		try {
			//这里可以做额外的数据操作，也就是主程序执行其他业务逻辑
			System.out.println("处理实际的业务逻辑...");
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//调用获取数据方法,如果call()方法没有执行完成,则依然会进行等待
		System.out.println("数据：" + task.get());
		System.out.println("数据：" + f.get());
		pool.shutdown();
		
	}
}
