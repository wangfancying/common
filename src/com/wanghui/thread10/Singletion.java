package com.wanghui.thread10;


/**
 * �������ڲ���ķ�ʽ
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
	
	//�����л�ʱ�ᱻ����
	protected Object readResolve(){
		return Inner.single;
	}
	
}