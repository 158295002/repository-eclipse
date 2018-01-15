package com.evada.test.factory;

public class ProductCar implements IProduct {

	@Override
	public void product() {
		System.out.println(Messages.getString("car")); //$NON-NLS-1$
	}

}
