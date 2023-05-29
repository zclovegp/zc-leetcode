package precision;

import java.math.BigDecimal;

/**
 * 计算机中整数部分是除2取余，小数部分是乘2取整，正好相反，所以会些小数是无法精确表示的，比如0.6：
 * 0.6*2 = 1.2 ———— 1
 * 0.2*2 = 0.4 ———— 0
 * 0.4*2 = 0.8 ———— 0
 * 0.8*2 = 1.6 ———— 1
 * 0.6*2 = 1.2 ———— 1
 * 0.10011001…
 * 所以进行运算的时候无法精确
 * Double var  = (double)4.6 * 100 = 4.599999
 * 计算机运算时是需要转换为二进制的，而小数的二进制表示有时是不可能精确的
 * <p>
 * https://zhuanlan.zhihu.com/p/149718922?from_voters_page=true
 * https://www.cnblogs.com/yewsky/articles/1864934.html
 * https://blog.csdn.net/weixin_46242947/article/details/122262877
 *
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
