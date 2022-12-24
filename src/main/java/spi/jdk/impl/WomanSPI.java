package spi.jdk.impl;

import spi.MySPI;

/**
 * @author zhaochong on 2022/12/24 16:39
 */
public class WomanSPI implements MySPI {
	@Override
	public void doProcess() {
		System.out.println("here is woman! let's do it");
	}
}
