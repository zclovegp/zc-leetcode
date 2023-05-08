package spi.dubbo.impl;

import spi.MySPI;


public class WomanSPI implements MySPI {
	@Override
	public void doProcess() {
		System.out.println("extension !!! here is woman! let's do it");
	}
}
