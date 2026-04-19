package com.reflection.forName;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
/*
	3 ways to create, class name ki class ka object:
		1) through getClass()
		2) through forName()
		3) through class extension
*/

class Person{
	public void show() {
		
	}
	public void view() {
		
	}
}

public class Launch {
	public static void main(String[] args) {
		try {
			Class c=Class.forName("com.reflection.forName.Person");
			Method[] methods = c.getDeclaredMethods();
			for(Method m:methods) {
				System.out.println("Method Name: "+m.getName());
				int modifiers = m.getModifiers();
				System.out.println("Modifier Name: "+Modifier.toString(modifiers));
				System.out.println("Return Type Name: "+m.getReturnType());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
