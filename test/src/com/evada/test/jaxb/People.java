package com.evada.test.jaxb;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "people", propOrder = { "chid", })
@XmlRootElement
public class People {

	@XmlAttribute(name = "id")
	public String id = "001";

	@XmlAttribute(name = "name")
	public String name = "灰太狼";

	@XmlAttribute(name = "age")
	public int age = 26;

	@XmlElement(name = "chid", required = true)
	child chid = new child();

	public static void main(String[] args) throws JAXBException {
		System.out.println(args.length);
		// Java2XML
		JAXBContext context = JAXBContext.newInstance(People.class);
		Marshaller shaller = context.createMarshaller();
		shaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		People people = new People();
		shaller.marshal(people, new File("C:\\Users\\Administrator\\Desktop\\test.txt"));
		System.out.println("转换成XML成功");
		// XML2Java
		Unmarshaller unShaller = context.createUnmarshaller();
		People p = (People) unShaller.unmarshal(new File("C:\\Users\\Administrator\\Desktop\\test.txt"));
		System.out.println(p.chid.code);
		System.out.println("转换成对象成功");
	}

	private static class child {
		@XmlAttribute(name = "code")
		String code = "12332";
	}

}
