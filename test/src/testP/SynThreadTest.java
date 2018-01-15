package testP;

import org.junit.Test;

public class SynThreadTest {

	class person implements Runnable{

		private int num = 50;
		
		@Override
		public void run() {
			while(num >0){
				eat();
			}
		}

		protected void eat() {
			synchronized (this) {
				if (num > 0) {
					System.out.println(String.format(Thread.currentThread().getName()+"吃了第%s个苹果", num));
					num--;
				}
			}
		}
	}
	
	@Test
	public void name() {
		person a = new person();
		new Thread(a, "B").start();
		new Thread(a, "C").start();
		new Thread(a, "A").start();
	}
}
