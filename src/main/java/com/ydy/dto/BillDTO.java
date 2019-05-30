/**
 * 
 */
package com.ydy.dto;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

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
	/**
	 * 总金额
	 */
	private Long totalPay;
	/**
	 * 实付金额
	 */
	private Long actualPay;
	/**
	 * 邮费
	 */
	@NotNull
	@Min(value = 0)
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
