/**
 * 
 */
package com.ydy.utils;

import java.math.BigDecimal;

/**
 * @author xuzhaojie
 *
 *         2019年6月4日 下午2:08:39
 */
public class BigDecimalUtil {
	public static BigDecimal add(double v1, double v2) {// v1 + v2
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2);
	}

	public static BigDecimal sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2);
	}

	public static BigDecimal mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2);
	}

	public static BigDecimal discount(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2 / 100));
		return b1.multiply(b2);
	}

	public static BigDecimal div(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		// 2 = 保留小数点后两位 ROUND_HALF_UP = 四舍五入
		return b1.divide(b2, 8, BigDecimal.ROUND_HALF_UP);// 应对除不尽的情况
	}

	public static void main(String[] args) {
		System.out.println(discount(64200, 85));
	}
}
