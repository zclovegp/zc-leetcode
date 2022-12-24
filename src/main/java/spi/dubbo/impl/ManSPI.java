package spi.dubbo.impl;

import spi.MySPI;

/**
 * @author zhaochong on 2022/12/24 16:38
 */
public class ManSPI implements MySPI {
	@Override
	public void doProcess() {
		System.out.println("extension !!! here is man! let's do it");
	}
}
