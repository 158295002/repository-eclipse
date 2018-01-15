package com.evada.test.io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFile_Test {

	public static void main(String[] args) throws IOException {
		File file = new File("C:/Users/Administrator/Desktop/test.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		RandomAccessFile accessfile = new RandomAccessFile(file, "rw");
//		accessfile.write(123);
		String str = "¶¡";
		accessfile.write(str.getBytes("gbk"));
		
		byte[] bytes = new byte[(int) accessfile.length()];
		accessfile.seek(0);;
		accessfile.read(bytes);
//		for (byte b : bytes) {
//			System.out.println(b);
//		}
		String sr = new String(bytes,"gbk");
		System.out.println(sr);
		
	}
}
