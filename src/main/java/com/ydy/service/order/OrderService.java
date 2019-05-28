/**
 * 
 */
package com.ydy.service.order;

import com.ydy.constant.OrderStatusEnum;
import com.ydy.dto.OrderDTO;
import com.ydy.model.Order;
import com.ydy.vo.other.BaseVo;
import com.ydy.vo.other.PageVo;

/**
 * @author xuzhaojie
 *
 *         2019年5月27日 下午3:52:02
 */
public interface OrderService {

	PageVo<Order> selectData(Order order, Integer page, Integer size);
	
	Long createOrderId();

	Order createOrder(OrderDTO dto);

	BaseVo updateOrderStatus(Long orderId, OrderStatusEnum statusEnum);
}