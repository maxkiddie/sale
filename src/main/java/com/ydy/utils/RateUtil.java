/**
 * 
 */
package com.ydy.utils;

import java.math.BigDecimal;

/**
 * @author xuzhaojie
 *
 *         2019年6月4日 下午12:28:57
 */
public class RateUtil {

	public static BigDecimal calculate(Long sourceTotal, BigDecimal rate) {
		BigDecimal result = BigDecimalUtil.mul(100.0, rate.doubleValue());
		result = BigDecimalUtil.div(sourceTotal.doubleValue(), result.doubleValue());
		return result;
	}

	public static void main(String[] args) {
		System.out.println(calculate(1500L, new BigDecimal("8201.21")));
	}
}
