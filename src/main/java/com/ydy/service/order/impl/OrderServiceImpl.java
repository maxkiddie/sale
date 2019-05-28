/**
 * 
 */
package com.ydy.service.order.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ydy.constant.OrderStatusEnum;
import com.ydy.dto.OrderDTO;
import com.ydy.exception.MyException;
import com.ydy.mapper.OrderDetailMapper;
import com.ydy.mapper.OrderMapper;
import com.ydy.mapper.OrderStatusMapper;
import com.ydy.mapper.SkuMapper;
import com.ydy.model.Order;
import com.ydy.model.OrderDetail;
import com.ydy.model.OrderStatus;
import com.ydy.model.Sku;
import com.ydy.service.order.OrderService;
import com.ydy.utils.DateUtil;
import com.ydy.vo.ienum.EnumGood;
import com.ydy.vo.ienum.EnumOrder;
import com.ydy.vo.ienum.EnumSystem;
import com.ydy.vo.other.BaseVo;
import com.ydy.vo.other.PageVo;
import com.ydy.vo.other.ResultVo;

/**
 * @author xuzhaojie
 *
 *         2019年5月27日 下午3:52:15
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderStatusMapper orderStatusMapper;
	@Autowired
	private OrderDetailMapper orderDetailMapper;
	@Autowired
	private SkuMapper skuMapper;

	@Override
	public PageVo<Order> selectData(Order order, Integer page, Integer size) {
		PageVo<Order> vo = new PageVo<Order>(page, size);
		Page<Order> pageBean = PageHelper.startPage(vo.getPage(), vo.getSize(), "id desc");
		List<Order> list = orderMapper.select(order);
		vo.setTotal(pageBean.getTotal());
		vo.setList(list);
		return vo;
	}
	
	@Override
	public Long createOrderId() {
		String idString = "";
		synchronized (this) {
			idString = DateUtil.produceId() + (System.currentTimeMillis() + "").substring(9, 13);
		}
		return Long.parseLong(idString);
	}

	@Override
	public Order createOrder(OrderDTO dto) {
		List<OrderDetail> details = dto.getOrderDetails();
		if (CollectionUtils.isEmpty(details)) {
			throw new MyException(EnumOrder.ORDER_DETAIL_EMPTY);
		}
		Long total = 0L;
		Sku sku = null;
		Long orderId = createOrderId();
		for (OrderDetail detail : details) {
			sku = skuMapper.selectByPrimaryKey(detail.getSkuId());
			if (sku == null) {
				throw new MyException(EnumGood.SKU_NOT_FOUND);
			}
			detail.setOrderId(orderId);
			detail.setTitle(sku.getSpuSpecs());
			detail.setPrice(sku.getPrice());
			total = total + (sku.getPrice() * detail.getNum());
		}
		Date now = new Date();
		Order order = new Order();
		order.setOrderId(orderId);
		order.setTotalPay(total);
		order.setActualPay(total);
		order.setPaymentType(dto.getPaymentType());
		order.setPostFee(0L);
		order.setCreateTime(now);
		order.setUserId(dto.getUserId());
		order.setBuyerNick(dto.getBuyerNick());
		order.setBuyerMessage(dto.getBuyerMessage());
		order.setBuyerRate(0);
		order.setReceiver(dto.getReceiver());
		order.setReceiverMobile(dto.getReceiverMobile());
		order.setReceiverState(dto.getReceiverState());
		order.setReceiverCity(dto.getReceiverCity());
		order.setReceiverDistrict(dto.getReceiverDistrict());
		order.setReceiverAddress(dto.getReceiverAddress());
		order.setReceiverZip(dto.getReceiverZip());
		orderMapper.insertSelective(order);
		orderDetailMapper.insertList(details);
		OrderStatus status = new OrderStatus();
		status.setOrderStatus(OrderStatusEnum.COMMIT.getCode());
		status.setOrderId(orderId);
		status.setCreateTime(now);
		orderStatusMapper.insertSelective(status);
		return order;
	}

	@Override
	public BaseVo updateOrderStatus(Long orderId, OrderStatusEnum statusEnum) {
		OrderStatus temp = orderStatusMapper.selectByPrimaryKey(orderId);
		if (temp == null) {
			throw new MyException(EnumOrder.ORDER_NOT_FOUND);
		}
		OrderStatus status = new OrderStatus();
		status.setOrderId(orderId);
		status.setOrderStatus(statusEnum.getCode());
		Date now = new Date();
		if (OrderStatusEnum.CLOSED.equals(statusEnum)) {
			status.setCloseTime(now);
		} else if (OrderStatusEnum.SEND.equals(statusEnum)) {
			status.setConsignTime(now);
		} else if (OrderStatusEnum.CONFIRM.equals(statusEnum)) {
			status.setEndTime(now);
		} else if (OrderStatusEnum.COMMENT.equals(statusEnum)) {
			status.setCommentTime(now);
		}
		orderStatusMapper.updateByPrimaryKeySelective(status);
		return new ResultVo(EnumSystem.SUSS);
	}

}
