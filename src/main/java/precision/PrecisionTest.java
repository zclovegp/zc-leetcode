package precision;

import java.math.BigDecimal;

/**
 * @author zhaochong on 2023/1/28 09:14
 */
public class PrecisionTest {

	public static void main(String[] args) {
		float a = 1f;
		float b = 0.9f;
		System.out.println(a - b);
		System.out.println(new BigDecimal(0.9));
	}
}
