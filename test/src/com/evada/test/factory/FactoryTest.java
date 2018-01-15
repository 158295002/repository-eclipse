package com.evada.test.factory;

import org.junit.Test;

public class FactoryTest {

	@Test
	public void productTest(){
		ProductFactory factory = new ProductFactory();
		IProduct product = factory.product(ProductBike.class.getName());
		product.product();
	}
	
}
