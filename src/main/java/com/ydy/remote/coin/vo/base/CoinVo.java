/**
 * 
 */
package com.ydy.remote.coin.vo.base;

import java.math.BigDecimal;

/**
 * @author xuzhaojie
 *
 *         2019年6月4日 上午11:06:07
 */
public class CoinVo {

	private String name;
	private String fullName;
	private String fromSymbol;
	private String toSymbol;
	private BigDecimal price;
	private String imgUrl;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the imgUrl
	 */
	public String getImgUrl() {
		return imgUrl;
	}

	/**
	 * @param imgUrl
	 *            the imgUrl to set
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName
	 *            the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the fromSymbol
	 */
	public String getFromSymbol() {
		return fromSymbol;
	}

	/**
	 * @param fromSymbol
	 *            the fromSymbol to set
	 */
	public void setFromSymbol(String fromSymbol) {
		this.fromSymbol = fromSymbol;
	}

	/**
	 * @return the toSymbol
	 */
	public String getToSymbol() {
		return toSymbol;
	}

	/**
	 * @param toSymbol
	 *            the toSymbol to set
	 */
	public void setToSymbol(String toSymbol) {
		this.toSymbol = toSymbol;
	}

	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
