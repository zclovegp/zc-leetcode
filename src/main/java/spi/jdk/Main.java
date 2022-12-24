package spi.jdk;

import spi.MySPI;

import java.util.ServiceLoader;

/**
 * @author zhaochong on 2022/12/24 16:38
 */
public class Main {

	public static void main(String[] args) {
		ServiceLoader<MySPI> mySpi = ServiceLoader.load(MySPI.class);
		for (MySPI mySPI : mySpi) {
			mySPI.doProcess();
		}
	}
}
