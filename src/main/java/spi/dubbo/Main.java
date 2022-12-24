package spi.dubbo;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import spi.MySPI;

/**
 * @author zhaochong on 2022/12/24 16:59
 */
public class Main {

	public static void main(String[] args) {
		ExtensionLoader<MySPI> extensionLoader = ExtensionLoader.getExtensionLoader(MySPI.class);
		MySPI mySPI = extensionLoader.getExtension("man");
		mySPI.doProcess();
	}
}
