package com.reflection.getName;

import java.lang.reflect.Method;
/*
	3 ways to create, class name ki class ka object:
		1) through getClass()
		2) through forName()
		3) through class extension
*/

class Parent{
	
}

class Person extends Parent{
	public void show() {
		
	}
	public void view() {
		
	}
}

public class Launch {
	public static void main(String[] args) {
		
		Person p =new Person();
		Class c=p.getClass();
		
		//get class name
		String name=c.getName();
		System.out.println("Class Name: "+name);
		
		//get method name
		Method methods[]=c.getDeclaredMethods();
		for(Method m:methods) {
			System.out.println("Mehtod Name: "+m.getName());
		}
		
		//get parent class name
		Class superclass = c.getSuperclass();
		System.out.println("Super Class Name: "+superclass);
		
	}
}
