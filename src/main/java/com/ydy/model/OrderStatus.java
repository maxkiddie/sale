/**
 * 
 */
package com.ydy.model;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author xuzhaojie
 *
 *         2019年5月27日 上午11:36:26
 */
@Table(name = "order_status")
public class OrderStatus {
	@Id
	private Long orderId;

	private Integer orderStatus;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 付款时间
	 */
	private Date paymentTime;

	/**
	 * 发货时间
	 */
	private Date consignTime;

	/**
	 * 交易结束时间
	 */
	private Date endTime;

	/**
	 * 交易关闭时间
	 */
	private Date closeTime;

	/**
	 * 评价时间
	 */
	private Date commentTime;

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
	 * @return the orderStatus
	 */
	public Integer getOrderStatus() {
		return orderStatus;
	}

	/**
	 * @param orderStatus
	 *            the orderStatus to set
	 */
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
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
	 * @return the paymentTime
	 */
	public Date getPaymentTime() {
		return paymentTime;
	}

	/**
	 * @param paymentTime
	 *            the paymentTime to set
	 */
	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}

	/**
	 * @return the consignTime
	 */
	public Date getConsignTime() {
		return consignTime;
	}

	/**
	 * @param consignTime
	 *            the consignTime to set
	 */
	public void setConsignTime(Date consignTime) {
		this.consignTime = consignTime;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the closeTime
	 */
	public Date getCloseTime() {
		return closeTime;
	}

	/**
	 * @param closeTime
	 *            the closeTime to set
	 */
	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}

	/**
	 * @return the commentTime
	 */
	public Date getCommentTime() {
		return commentTime;
	}

	/**
	 * @param commentTime
	 *            the commentTime to set
	 */
	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

}
