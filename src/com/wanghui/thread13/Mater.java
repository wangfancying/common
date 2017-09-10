package com.wanghui.thread13;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Mater {

	private ConcurrentLinkedDeque<Task> workQueue = new ConcurrentLinkedDeque<>();
	
	private HashMap<String, Thread> workers = new HashMap<>();
	
	private ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<>();
	
	public Mater(Worker worker,int workerCount){
	}
}
