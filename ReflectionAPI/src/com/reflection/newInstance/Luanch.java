package com.reflection.newInstance;

class Person{
	public void show() {
		System.out.println("Show");
	}
}
public class Luanch {
	public static void main(String[] args) {
		try {
			Class c=Class.forName("com.reflection.newInstance.Person");
//			Person p=(Person)c.newInstance();		//deprecated 9+ onwards
			Person p=(Person)c.getConstructor().newInstance();
			p.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
