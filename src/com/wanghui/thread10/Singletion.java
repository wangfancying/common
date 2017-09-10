package com.wanghui.thread10;


/**
 * 单例，内部类的方式
 * @author Administrator
 *
 */
public class Singletion {

	public boolean isRunning;
	
	private Singletion(){
		
	}
	
	private static class Inner{
		private static Singletion single = new Singletion();
	}
	
	public static Singletion getInstance(){
		return Inner.single;
	}
	
	//反序列化时会被调用
	protected Object readResolve(){
		return Inner.single;
	}
	
}