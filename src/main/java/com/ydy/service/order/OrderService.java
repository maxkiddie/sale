/**
 * 
 */
package com.ydy.service.order;

import java.util.Date;
import java.util.List;

import com.ydy.dto.BillDTO;
import com.ydy.dto.OrderDTO;
import com.ydy.model.Order;
import com.ydy.model.OrderStatus;
import com.ydy.vo.OrderVo;
import com.ydy.vo.other.BaseVo;
import com.ydy.vo.other.PageVo;

/**
 * @author xuzhaojie
 *
 *         2019年5月27日 下午3:52:02
 */
public interface OrderService {

	PageVo<Order> select(Order order, Integer page, Integer size);

	Long createOrderId();

	BillDTO calculateBill(BillDTO bill);

	Order createOrder(OrderDTO dto);

	BaseVo updateOrderStatusClosed(Long orderId);

	BaseVo updateOrderStatusListClosed(List<OrderStatus> list);

	BaseVo updateOrderStatusPay(Long orderId);

	BaseVo updateOrderStatusSend(Long orderId, String shippingName, String shippingCode);

	BaseVo updateOrderStatusConfirm(Long orderId, Long userId);

	BaseVo updateOrderStatusComment(Long orderId);

	BaseVo delete(Long orderId);

	BaseVo delete(Long orderId, Long userId);

	List<OrderStatus> selectStatusCommit(Date createTime);

	OrderVo selectById(Long id);

	OrderVo selectById(Long id, Long userId);
}
