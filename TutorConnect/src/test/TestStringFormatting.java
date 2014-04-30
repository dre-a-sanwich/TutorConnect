package test;

import event.Event;

public class TestStringFormatting {
	
	public static void main(String[] args) {
		Event event = new Event("JossWhedon#!SAC#!Stuff#!DATE");
		System.out.println(event.toString());
	}

}
