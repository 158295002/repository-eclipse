package testP;

import java.io.IOException;

public class ProcessTest {

	public static void main(String[] args) throws IOException {
		processRunTest();
	}
	/**
	 * ����һ������(���±�)
	 * @throws IOException 
	 */
	private static void processRunTest() throws IOException {
		//��ʽ1
//		Runtime runtime = Runtime.getRuntime();
//		runtime.exec("notepad");
		//��ʽ2
		ProcessBuilder builder = new ProcessBuilder("notepad");
		builder.start();
	}
	
}
