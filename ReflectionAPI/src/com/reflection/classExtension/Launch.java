package com.reflection.classExtension;

import java.lang.reflect.Constructor;

/*
 	3 ways to create, class name ki class ka object:
 		1) through getClass()
 		2) through forName()
 		3) through class extension
 */

class Person{
	public void Person() {	//method
		
	}
}

public class Launch {
	public static void main(String[] args) {
		//Method 1
//		Person p=new Person();
//		Class c=p.getClass();
		//Method 2
//		Class c=Class.forName("reflection.classExtension.Person");
		//Mehtod 3
		Class c=Person.class;
		Constructor cons[]=c.getDeclaredConstructors();
		for(Constructor c1:cons) {
			System.out.println("Constructor Name: "+c1.getName());
		}
	}
}
