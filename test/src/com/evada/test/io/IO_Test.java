package com.evada.test.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IO_Test {

	public static void main(String[] args) throws IOException {
		File file = new File("C:\\Users\\Administrator\\Desktop\\test.txt");
		if (!file.exists()) {
			file.createNewFile();
			
		}
		FileWriter write = new FileWriter(file);
		BufferedWriter buWrite = new BufferedWriter(write);
		buWrite.newLine();
		buWrite.append("sfsf");
		buWrite.flush();
		buWrite.close();
		write.close();
		
		FileReader reader = new FileReader(file);
		BufferedReader buReader = new BufferedReader(reader);
		String str = buReader.readLine();
		while (str != null){
			System.out.println(str);
			str = buReader.readLine();
			
		}
		buReader.close();
		reader.close();
	}

}
