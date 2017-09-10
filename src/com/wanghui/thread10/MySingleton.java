package com.wanghui.thread10;

public class MySingleton {

	private static MySingleton singleton = new MySingleton();
	
	private MySingleton(){
		
	}
	
	private static MySingleton getInstance(){
		return singleton;
	}
}
