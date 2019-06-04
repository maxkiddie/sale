/**
 * 
 */
package com.ydy.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.ydy.model.Sku;

/**
 * @author xuzhaojie
 *
 *         2019年5月30日 上午9:09:27
 */
public class ItemDTO {

	@NotNull
	private Long skuId;
	private Sku sku;
	private Long unitPrice;
	@NotNull
	@Min(value = 1)
	private Integer num;
	private Long itemTotal;
	private Long itemActualTotal;

	/**
	 * @return the itemActualTotal
	 */
	public Long getItemActualTotal() {
		return itemActualTotal;
	}

	/**
	 * @param itemActualTotal
	 *            the itemActualTotal to set
	 */
	public void setItemActualTotal(Long itemActualTotal) {
		this.itemActualTotal = itemActualTotal;
	}

	/**
	 * @return the skuId
	 */
	public Long getSkuId() {
		return skuId;
	}

	/**
	 * @param skuId
	 *            the skuId to set
	 */
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	/**
	 * @return the sku
	 */
	public Sku getSku() {
		return sku;
	}

	/**
	 * @param sku
	 *            the sku to set
	 */
	public void setSku(Sku sku) {
		this.sku = sku;
	}

	/**
	 * @return the unitPrice
	 */
	public Long getUnitPrice() {
		return unitPrice;
	}

	/**
	 * @param unitPrice
	 *            the unitPrice to set
	 */
	public void setUnitPrice(Long unitPrice) {
		this.unitPrice = unitPrice;
	}

	/**
	 * @return the num
	 */
	public Integer getNum() {
		return num;
	}

	/**
	 * @param num
	 *            the num to set
	 */
	public void setNum(Integer num) {
		this.num = num;
	}

	/**
	 * @return the itemTotal
	 */
	public Long getItemTotal() {
		return itemTotal;
	}

	/**
	 * @param itemTotal
	 *            the itemTotal to set
	 */
	public void setItemTotal(Long itemTotal) {
		this.itemTotal = itemTotal;
	}

}
