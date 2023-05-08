package spi.dubbo.impl;

import spi.MySPI;


public class ManSPI implements MySPI {
	@Override
	public void doProcess() {
		System.out.println("extension !!! here is man! let's do it");
	}
}
