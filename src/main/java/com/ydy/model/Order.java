/**
 * 
 */
package com.ydy.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author xuzhaojie
 *
 *         2019年5月27日 上午11:39:04
 */
@Table(name = "o_order")
public class Order {
	@Id
	private Long orderId;

	/**
	 * 总金额
	 */
	private Long totalPay;
	/**
	 * 实付金额
	 */
	private Long actualPay;

	/**
	 * 支付类型，1、在线支付，2、货到付款
	 */
	@NotNull
	private Integer paymentType;

	/**
	 * 邮费
	 */
	private Long postFee;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 物流名称
	 */
	private String shippingName;

	/**
	 * 物流单号
	 */
	private String shippingCode;

	/**
	 * 用户id
	 */
	@NotNull
	private Long userId;

	/**
	 * 买家留言
	 */
	private String buyerMessage;

	/**
	 * 买家昵称
	 */
	@NotBlank
	private String buyerNick;

	/**
	 * 买家是否已经评价
	 */
	private Integer buyerRate;

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
	 * 国家
	 */
	@NotBlank
	private String receiverCountry;
	/**
	 * 省份
	 */
	@NotBlank
	private String receiverState;

	/**
	 * 城市
	 */
	private String receiverCity;

	/**
	 * 区/县
	 */
	@NotBlank
	private String receiverDistrict;

	/**
	 * 收货地址，如：xx路xx号
	 */
	@NotBlank
	private String receiverAddress;

	/**
	 * 邮政编码,如：310001
	 */
	@NotBlank
	private String receiverZip;

	@Transient
	private List<OrderDetail> orderDetails;

	@Transient
	private Integer status;

	/**
	 * @return the orderId
	 */
	public Long getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId
	 *            the orderId to set
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the receiverCountry
	 */
	public String getReceiverCountry() {
		return receiverCountry;
	}

	/**
	 * @param receiverCountry
	 *            the receiverCountry to set
	 */
	public void setReceiverCountry(String receiverCountry) {
		this.receiverCountry = receiverCountry;
	}

	/**
	 * @return the shippingName
	 */
	public String getShippingName() {
		return shippingName;
	}

	/**
	 * @param shippingName
	 *            the shippingName to set
	 */
	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
	}

	/**
	 * @return the shippingCode
	 */
	public String getShippingCode() {
		return shippingCode;
	}

	/**
	 * @param shippingCode
	 *            the shippingCode to set
	 */
	public void setShippingCode(String shippingCode) {
		this.shippingCode = shippingCode;
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
	 * @return the buyerMessage
	 */
	public String getBuyerMessage() {
		return buyerMessage;
	}

	/**
	 * @param buyerMessage
	 *            the buyerMessage to set
	 */
	public void setBuyerMessage(String buyerMessage) {
		this.buyerMessage = buyerMessage;
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
	 * @return the buyerRate
	 */
	public Integer getBuyerRate() {
		return buyerRate;
	}

	/**
	 * @param buyerRate
	 *            the buyerRate to set
	 */
	public void setBuyerRate(Integer buyerRate) {
		this.buyerRate = buyerRate;
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

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

}
