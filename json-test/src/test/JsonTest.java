package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTest {

	
	@Test
	public void test()  {
		Child child = new Child(1);
		List<Child> childs = new ArrayList<>();
		childs.add(child);
		childs.add(child);	
		childs.add(child);
		ObjectMapper objectMapper = new ObjectMapper();
		Person p1 = new Person(1, "a",childs);
		
		try {
			String value = objectMapper.writeValueAsString(p1);
			Person p = objectMapper.readValue(value, Person.class);
			System.out.println(p+"child:"+p.getChilds());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
