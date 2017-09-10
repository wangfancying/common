package com.wanghui.thread11;

import java.util.concurrent.PriorityBlockingQueue;

public class UsePriorityQueue {

	public static void main(String[] args) throws InterruptedException {
		
		PriorityBlockingQueue<Task> q = new PriorityBlockingQueue<>();
		
		Task t1 = new Task();
		t1.setId(3);
		t1.setName("idÎª3");
		Task t2 = new Task();
		t2.setId(4);
		t2.setName("idÎª4");
		Task t3 = new Task();
		t3.setId(1);
		t3.setName("idÎª1");
		
		//return this.id > task.id ? 1 : 0;
		q.add(t1);	//3
		q.add(t2);	//4
		q.add(t3);  //1
		
		// 1 3 4
		System.out.println("ÈÝÆ÷£º" + q);
		System.out.println(q.take().getId());
		System.out.println("ÈÝÆ÷£º" + q);
	}
}

class Task implements Comparable<Task>{
	private  int id;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int compareTo(Task task) {
		// TODO Auto-generated method stub
		return this.id > task.id ? 1 : (this.id < task.id ? -1 : 0);  
	}
	public String toString(){
		return this.id + "," + this.name;
	}
}
