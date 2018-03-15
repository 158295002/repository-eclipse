

import java.io.File;
import java.io.IOException;

public class RunMaven {
	public static void main(String[] args) {
		try {
			File file = new File("J:\\self_gitHb\\gitHub-workspace\\test_project");
			if( file.exists()) {
				System.out.println();
			}
			Runtime runtime = Runtime.getRuntime();
			runtime.exec(String.format("cmd /k cd %s && mvn package",file.toString()));
//			Process process = runtime.exec("mvn package", null, file);
//			int waitFor = process.waitFor();
//			System.out.println(waitFor);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
