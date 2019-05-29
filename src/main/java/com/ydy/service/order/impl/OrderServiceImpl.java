/**
 * 
 */
package com.ydy.service.order.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ydy.constant.OrderStatusEnum;
import com.ydy.dto.OrderDTO;
import com.ydy.exception.MyException;
import com.ydy.exception.ValidateException;
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
import com.ydy.utils.ValidateUtil;
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
	public PageVo<Order> select(Order order, Integer page, Integer size) {
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
		// 校验数据有效
		Map<String, String> validateInfo = ValidateUtil.validateEntity(dto);
		if (!validateInfo.isEmpty()) {
			throw new ValidateException(validateInfo);
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
			detail.setImage(sku.getMainImage());
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
		order.setReceiverCountry(dto.getReceiverCountry());
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
	public BaseVo updateOrderStatusClosed(Long orderId) {
		OrderStatus status = createOrderStatus(orderId, OrderStatusEnum.CLOSED);
		orderStatusMapper.updateByPrimaryKeySelective(status);
		return new ResultVo(EnumSystem.SUSS);
	}

	@Override
	public BaseVo updateOrderStatusListClosed(List<OrderStatus> list) {
		Date now = new Date();
		for (OrderStatus status : list) {
			status.setCloseTime(now);
			status.setOrderStatus(OrderStatusEnum.CLOSED.getCode());
			status.setCommentTime(null);
			status.setConsignTime(null);
			status.setCreateTime(null);
			status.setEndTime(null);
			status.setPaymentTime(null);
			orderStatusMapper.updateByPrimaryKeySelective(status);
		}
		return new ResultVo(EnumSystem.SUSS);
	}

	@Override
	public BaseVo updateOrderStatusPay(Long orderId) {
		OrderStatus status = createOrderStatus(orderId, OrderStatusEnum.PAY);
		orderStatusMapper.updateByPrimaryKeySelective(status);
		return new ResultVo(EnumSystem.SUSS);
	}

	@Override
	public BaseVo updateOrderStatusSend(Long orderId, String shippingName, String shippingCode) {
		OrderStatus status = createOrderStatus(orderId, OrderStatusEnum.SEND);
		orderStatusMapper.updateByPrimaryKeySelective(status);
		Order order = new Order();
		order.setShippingName(shippingName);
		order.setShippingCode(shippingCode);
		orderMapper.updateByPrimaryKeySelective(order);
		return new ResultVo(EnumSystem.SUSS);
	}

	@Override
	public BaseVo updateOrderStatusConfirm(Long orderId, Long userId) {
		Order order = orderMapper.selectByPrimaryKey(orderId);
		if (order == null) {
			throw new MyException(EnumSystem.DATA_NOT_FOUND);
		}
		if (!Objects.equals(userId, order.getUserId())) {
			throw new MyException(EnumSystem.NO_AUTH);
		}
		OrderStatus status = createOrderStatus(orderId, OrderStatusEnum.CONFIRM);
		orderStatusMapper.updateByPrimaryKeySelective(status);
		return new ResultVo(EnumSystem.SUSS);
	}

	@Override
	public BaseVo updateOrderStatusComment(Long orderId) {
		OrderStatus status = createOrderStatus(orderId, OrderStatusEnum.COMMENT);
		orderStatusMapper.updateByPrimaryKeySelective(status);
		return new ResultVo(EnumSystem.SUSS);
	}

	private OrderStatus createOrderStatus(Long orderId, OrderStatusEnum statusEnum) {
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
		} else if (OrderStatusEnum.PAY.equals(statusEnum)) {
			if (!Objects.equals(OrderStatusEnum.COMMIT.getCode(), temp.getOrderStatus())) {
				throw new MyException(EnumOrder.ORDER_STATUS_ERROR);
			}
			status.setPaymentTime(now);
		} else if (OrderStatusEnum.SEND.equals(statusEnum)) {
			if (!Objects.equals(OrderStatusEnum.PAY.getCode(), temp.getOrderStatus())) {
				throw new MyException(EnumOrder.ORDER_STATUS_ERROR);
			}
			status.setConsignTime(now);
		} else if (OrderStatusEnum.CONFIRM.equals(statusEnum)) {
			if (!Objects.equals(OrderStatusEnum.SEND.getCode(), temp.getOrderStatus())) {
				throw new MyException(EnumOrder.ORDER_STATUS_ERROR);
			}
			status.setEndTime(now);
		} else if (OrderStatusEnum.COMMENT.equals(statusEnum)) {
			if (!Objects.equals(OrderStatusEnum.CONFIRM.getCode(), temp.getOrderStatus())) {
				throw new MyException(EnumOrder.ORDER_STATUS_ERROR);
			}
			status.setCommentTime(now);
		}
		return status;
	}

	@Override
	public List<OrderStatus> selectStatusCommit(Date createTime) {
		PageHelper.startPage(1, 200, "order_id asc");
		return orderStatusMapper.selectStatusCommit(OrderStatusEnum.COMMIT.getCode(), createTime);
	}
}
