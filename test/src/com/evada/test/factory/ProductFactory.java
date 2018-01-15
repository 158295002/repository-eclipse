package com.evada.test.factory;

public class ProductFactory {

	public IProduct product(String className){
		try {
			return (IProduct) Class.forName(className).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
