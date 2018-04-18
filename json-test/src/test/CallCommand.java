package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;


public class CallCommand {

	@Test
	public void callCmd() {
		String command = "ipconfig -all";
		// String command="java -version";
		String s = "IPv4";
		String line = null;
		StringBuilder sb = new StringBuilder();
		Runtime runtime = Runtime.getRuntime();
		try {
			Process process = runtime.exec(command);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line + "\n");
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void callEmberCmd() throws IOException, InterruptedException {
//		System.out.println(System.getProperty("user.dir"));
		String dir = "J:/web_fontend_workspace/dd";
//		String cc ="cmd /c j: && cd J:/web_fontend_workspace/ember-quickstart && ember generate route t myRoute/dd --pod --dry-run";
		String cc = "cmd /c ember generate model t-t name --d";
		Runtime runtime = Runtime.getRuntime();
		String line = null;
		StringBuilder sb = new StringBuilder();
		Process process = runtime.exec(cc,null,new File(dir));
//		int code = process.waitFor();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

		System.out.println("input:");
		while ((line = bufferedReader.readLine()) != null) {
			sb.append(line + "\n");
			System.out.println(line);
		}
		
		BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

		System.out.println("error:");
		while ((line = errorReader.readLine()) != null) {
			sb.append(line + "\n");
			System.out.println(line);
		}
	}
	
	@Test
	public void callNodeCommand(){
		String command = "cmd /c nrm ls";
		Runtime runtime = Runtime.getRuntime();
		Process process;
		try {
			process = runtime.exec(command);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = null;
			while((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
