package com.wanghui.thread21;

import java.util.concurrent.atomic.AtomicInteger;

public class Trade {

	private String id;
	private String name;
	private double price;
	private AtomicInteger count = new AtomicInteger(0);
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public AtomicInteger getCount() {
		return count;
	}
	public void setCount(AtomicInteger count) {
		this.count = count;
	}
	
	public Trade(String id, String name, double price, AtomicInteger count) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.count = count;
	}
	public Trade() {
		super();
	}
}
