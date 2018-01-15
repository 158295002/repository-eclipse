package com.evada.test.factory;

public class ProductBike implements IProduct {

	@Override
	public void product() {
		System.out.println(Messages.getString("bike"));
	}

}
