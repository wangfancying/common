package com.wanghui.thread02;


/**
 * �ؼ���synchronizedȡ�õ������Ƕ������������ǰ�һ�δ��루��������������
 * ���Դ������ĸ��߳���ִ��synchronized�ؼ��ֵķ������ĸ��߳̾ͳ��и÷����������������Lock����
 * �ھ�̬�����ϼ�synchronized�ؼ��֣���ʾ����.class�࣬��һ�����������ռ.class�ࣩ��
 * @author Administrator
 *
 */
public class MultiThread {
	
	private int num = 0;
	
	/** static */
	public synchronized void printNum(String tag){
		try {
			
			if(tag.equals("a")){
				num = 100;
				System.out.println("tag a, set num over!");
				Thread.sleep(1000);
			} else {
				num = 200;
				System.out.println("tag b, set num over!");
			}
			
			System.out.println("tag " + tag + ", num = " + num);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	//ע��۲�run�������˳��
		public static void main(String[] args) {
			
			//������ͬ�Ķ���
			final MultiThread m1 = new MultiThread();
			final MultiThread m2 = new MultiThread();
			
			Thread t1 = new Thread(new Runnable() {
				@Override
				public void run() {
					m1.printNum("a");
				}
			});
			
			Thread t2 = new Thread(new Runnable() {
				@Override 
				public void run() {
					m2.printNum("b");
				}
			});		
			
			t1.start();
			t2.start();
			
		}
}
