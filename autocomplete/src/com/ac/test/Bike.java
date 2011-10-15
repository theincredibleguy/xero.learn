package com.ac.test;

import com.ac.engine.AutoCEngine;

public class Bike extends Thread {

	AutoCEngine engine ;
	
	public Bike(){
//		engine = new AutoCEngine();
	}
	
	@Override
	public void run(){
		// engine.start();
		synchronized (this) {

			try {
				System.out.println("going to wait");
				this.wait();
				System.out.println("the wait's over.. bba");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		Bike b = new Bike();
		b.start();
		System.out.println("now waiting .. zzzz");
		Thread.sleep(1000);
		System.out.println("wow that's a nice sleep :D");
		synchronized(b){
			b.notify();
		}
	}
	
	
}
