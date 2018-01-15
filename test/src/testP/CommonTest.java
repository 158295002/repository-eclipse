package testP;

import java.io.IOException;
import java.util.Date;


public class CommonTest {

	public static void main(String[] args) throws IOException {
//		FileWriter fileWriter = new FileWriter("C:\\Users\\Administrator\\Desktop\\ddTest.txt");
//		fileWriter.write("dsfdf\n"+"\ndd");
//		fileWriter.flush();
//		fileWriter.close();
//		System.out.println("–¥»Î≥…π¶");
//		String str = "Type";
//		System.out.println(str.toUpperCase());
		Date date = new Date();
		long time = date.getTime();
		System.out.println(time);
		System.out.println(date.getSeconds());
	}

}
