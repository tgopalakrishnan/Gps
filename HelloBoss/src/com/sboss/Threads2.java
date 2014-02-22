package com.sboss;

public class Threads2 implements Runnable {

	public void run() {
		System.out.println("run.");
		throw new Error("****GOPAL****");
//		 throw new RuntimeException("Problem");

	}

	public static void main(String[] args) {
		Thread t = new Thread(new Threads2());
		System.out.println("End of method: "+t.getName());
		t.start();
		t.start();

//		t.getName();
//		System.out.println("End of method."+t.getName());
	}
}