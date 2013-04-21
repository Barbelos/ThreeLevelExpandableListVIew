package com.example.threelevelexpandablelistview;

import java.util.List;

public class Object {
	String name;
	List<Object> objects;
	
	public Object(String name, List<Object> objects) {
		this.name= name;
		this.objects= objects;
	}
	
	public String getName() {
		return this.name;
	}
	
	public List<Object> getObjects() {
		return this.objects;
	}
}
