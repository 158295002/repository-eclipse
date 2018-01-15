package testP;

import java.io.IOException;

public class ProcessTest {

	public static void main(String[] args) throws IOException {
		processRunTest();
	}
	/**
	 * 开启一个进程(记事本)
	 * @throws IOException 
	 */
	private static void processRunTest() throws IOException {
		//方式1
//		Runtime runtime = Runtime.getRuntime();
//		runtime.exec("notepad");
		//方式2
		ProcessBuilder builder = new ProcessBuilder("notepad");
		builder.start();
	}
	
}
