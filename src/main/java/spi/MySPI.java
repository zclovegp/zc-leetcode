package spi;

import org.apache.dubbo.common.extension.SPI;

/**
 * @author zhaochong on 2022/12/24 16:37
 */
@SPI
public interface MySPI {
	void doProcess();
}
