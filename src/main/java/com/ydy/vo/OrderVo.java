/**
 * 
 */
package com.ydy.vo;

import javax.persistence.Transient;

import com.ydy.model.Order;
import com.ydy.model.OrderStatus;

/**
 * @author xuzhaojie
 *
 *         2019年5月29日 下午6:55:42
 */
public class OrderVo extends Order {

	@Transient
	private OrderStatus orderStatus;

	/**
	 * @return the orderStatus
	 */
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	/**
	 * @param orderStatus
	 *            the orderStatus to set
	 */
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

}
