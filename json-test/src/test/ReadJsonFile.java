package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import guideConfigModel.Root;

public class ReadJsonFile {

	@Test
	public void readFile() throws IOException {

		FileReader reader2 = new FileReader(
				new File("C:\\\\Users\\\\ThinkPad\\\\Desktop\\\\开发向导\\\\配置文件\\\\创建适配器.config"));
		BufferedReader br = new BufferedReader(reader2);

		StringBuilder stringBuilder = new StringBuilder();
		String s;

		while ((s = br.readLine()) != null) {
			stringBuilder.append(s);

		}
		br.close();
		reader2.close();
		System.out.println(stringBuilder.toString());
		ObjectMapper objectMapper = new ObjectMapper();
		Root p = objectMapper.readValue(stringBuilder.toString(), Root.class);
		System.out.println(p.getCallCommand());
	}

}
