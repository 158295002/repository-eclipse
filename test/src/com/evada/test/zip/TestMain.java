package com.evada.test.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class TestMain {

	public static void main(String[] args) throws IOException {
		
		File file1 = new File("C:/Users/Administrator/Desktop/test");
		if (!file1.exists()) {
			file1.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(file1); 
        ZipOutputStream outs = new ZipOutputStream(fos); 

            String entryName = "C:/Users/Administrator/Desktop/test.zip"; 
            ZipEntry zipEntry = new ZipEntry(entryName); 
            zipEntry.setTime(new Date().getTime()); 
            FileInputStream ins = new FileInputStream(file1); 
            outs.putNextEntry(zipEntry); 
            IOHandler.pipe(ins, outs); 
            outs.closeEntry(); 
            ins.close(); 

        outs.close(); 
		
	}

}
