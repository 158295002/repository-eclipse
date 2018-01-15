package testP;

import org.junit.Test;

public class ThreadTest {

class A extends Thread{
		
		@Override
		public void run() {
			for(int index = 0 ;index <= 5; index++){
				System.out.println("A"+index);
			}
		}
	}
	class B extends Thread{
		
		@Override
		public void run() {
			for(int index = 0 ;index <= 5; index++){
				System.out.println("B"+index);
			}
		}
	}
	
	@Test
	public void name() {
		A a = new A();
		B b = new B();
		a.start();
		b.start();
	}
	
}
