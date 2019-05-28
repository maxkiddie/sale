/**
 * 
 */
package com.ydy.dto;

import java.util.List;

import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.ydy.model.OrderDetail;

/**
 * @author xuzhaojie
 *
 *         2019年5月27日 下午4:42:21
 */
public class OrderDTO {
	private Integer paymentType;
	/**
	 * 买家留言
	 */
	@NotNull
	private Long userId;
	@NotBlank
	private String buyerNick;
	private String buyerMessage;
	/**
	 * 收货人全名
	 */
	@NotBlank
	private String receiver;
	/**
	 * 移动电话
	 */
	@NotBlank
	private String receiverMobile;
	/**
	 * 省份
	 */
	@NotBlank
	private String receiverState;

	/**
	 * 城市
	 */
	@NotBlank
	private String receiverCity;

	/**
	 * 区/县
	 */
	@NotBlank
	private String receiverDistrict;

	/**
	 * 收货地址，如：xx路xx号
	 */

	private String receiverAddress;

	/**
	 * 邮政编码,如：310001
	 */
	private String receiverZip;

	@Transient
	private List<OrderDetail> orderDetails;

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
	 * @return the buyerMessage
	 */
	public String getBuyerMessage() {
		return buyerMessage;
	}

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
	 * @return the buyerNick
	 */
	public String getBuyerNick() {
		return buyerNick;
	}

	/**
	 * @param buyerNick
	 *            the buyerNick to set
	 */
	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick;
	}

	/**
	 * @param buyerMessage
	 *            the buyerMessage to set
	 */
	public void setBuyerMessage(String buyerMessage) {
		this.buyerMessage = buyerMessage;
	}

	/**
	 * @return the receiver
	 */
	public String getReceiver() {
		return receiver;
	}

	/**
	 * @param receiver
	 *            the receiver to set
	 */
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	/**
	 * @return the receiverMobile
	 */
	public String getReceiverMobile() {
		return receiverMobile;
	}

	/**
	 * @param receiverMobile
	 *            the receiverMobile to set
	 */
	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}

	/**
	 * @return the receiverState
	 */
	public String getReceiverState() {
		return receiverState;
	}

	/**
	 * @param receiverState
	 *            the receiverState to set
	 */
	public void setReceiverState(String receiverState) {
		this.receiverState = receiverState;
	}

	/**
	 * @return the receiverCity
	 */
	public String getReceiverCity() {
		return receiverCity;
	}

	/**
	 * @param receiverCity
	 *            the receiverCity to set
	 */
	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}

	/**
	 * @return the receiverDistrict
	 */
	public String getReceiverDistrict() {
		return receiverDistrict;
	}

	/**
	 * @param receiverDistrict
	 *            the receiverDistrict to set
	 */
	public void setReceiverDistrict(String receiverDistrict) {
		this.receiverDistrict = receiverDistrict;
	}

	/**
	 * @return the receiverAddress
	 */
	public String getReceiverAddress() {
		return receiverAddress;
	}

	/**
	 * @param receiverAddress
	 *            the receiverAddress to set
	 */
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	/**
	 * @return the receiverZip
	 */
	public String getReceiverZip() {
		return receiverZip;
	}

	/**
	 * @param receiverZip
	 *            the receiverZip to set
	 */
	public void setReceiverZip(String receiverZip) {
		this.receiverZip = receiverZip;
	}

	/**
	 * @return the orderDetails
	 */
	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	/**
	 * @param orderDetails
	 *            the orderDetails to set
	 */
	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

}
