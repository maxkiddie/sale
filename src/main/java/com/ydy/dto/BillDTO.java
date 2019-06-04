/**
 * 
 */
package com.ydy.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author xuzhaojie
 *
 *         2019年5月30日 上午9:01:51
 */
public class BillDTO {

	@NotNull
	private Long userId;

	@NotNull
	private Integer paymentType;
	private String coinType;// 币种
	/**
	 * 总金额
	 */
	private Long totalPay;
	/**
	 * 实付金额
	 */
	private Long actualPay;
	private String moneyPay;// 付款数目

	@NotBlank
	private String payCoinType;// 付款 币种

	private String rate;// 当天汇率
	/**
	 * 邮费
	 */
	private Long postFee;
	@NotEmpty
	List<ItemDTO> items;

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the payCoinType
	 */
	public String getPayCoinType() {
		return payCoinType;
	}

	/**
	 * @param payCoinType
	 *            the payCoinType to set
	 */
	public void setPayCoinType(String payCoinType) {
		this.payCoinType = payCoinType;
	}

	/**
	 * @return the coinType
	 */
	public String getCoinType() {
		return coinType;
	}

	/**
	 * @param coinType
	 *            the coinType to set
	 */
	public void setCoinType(String coinType) {
		this.coinType = coinType;
	}

	/**
	 * @return the moneyPay
	 */
	public String getMoneyPay() {
		return moneyPay;
	}

	/**
	 * @param moneyPay
	 *            the moneyPay to set
	 */
	public void setMoneyPay(String moneyPay) {
		this.moneyPay = moneyPay;
	}

	/**
	 * @return the rate
	 */
	public String getRate() {
		return rate;
	}

	/**
	 * @param rate
	 *            the rate to set
	 */
	public void setRate(String rate) {
		this.rate = rate;
	}

	/**
	 * @return the paymentType
	 */
	public Integer getPaymentType() {
		return paymentType;
	}

	/**
	 * @param paymentType
	 *            the paymentType to set
	 */
	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}

	/**
	 * @return the totalPay
	 */
	public Long getTotalPay() {
		return totalPay;
	}

	/**
	 * @param totalPay
	 *            the totalPay to set
	 */
	public void setTotalPay(Long totalPay) {
		this.totalPay = totalPay;
	}

	/**
	 * @return the actualPay
	 */
	public Long getActualPay() {
		return actualPay;
	}

	/**
	 * @param actualPay
	 *            the actualPay to set
	 */
	public void setActualPay(Long actualPay) {
		this.actualPay = actualPay;
	}

	/**
	 * @return the postFee
	 */
	public Long getPostFee() {
		return postFee;
	}

	/**
	 * @param postFee
	 *            the postFee to set
	 */
	public void setPostFee(Long postFee) {
		this.postFee = postFee;
	}

	/**
	 * @return the items
	 */
	public List<ItemDTO> getItems() {
		return items;
	}

	/**
	 * @param items
	 *            the items to set
	 */
	public void setItems(List<ItemDTO> items) {
		this.items = items;
	}

}
