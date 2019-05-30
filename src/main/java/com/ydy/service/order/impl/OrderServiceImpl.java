/**
 * 
 */
package com.ydy.service.order.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ydy.constant.OrderStatusEnum;
import com.ydy.dto.BillDTO;
import com.ydy.dto.ItemDTO;
import com.ydy.dto.OrderDTO;
import com.ydy.exception.BusinessException;
import com.ydy.exception.DataNotFoundException;
import com.ydy.exception.ValidateException;
import com.ydy.ienum.EnumGood;
import com.ydy.ienum.EnumOrder;
import com.ydy.ienum.EnumSystem;
import com.ydy.mapper.OrderDetailMapper;
import com.ydy.mapper.OrderMapper;
import com.ydy.mapper.OrderStatusMapper;
import com.ydy.mapper.ReductionMapper;
import com.ydy.mapper.SkuMapper;
import com.ydy.model.Order;
import com.ydy.model.OrderDetail;
import com.ydy.model.OrderStatus;
import com.ydy.model.Reduction;
import com.ydy.model.Sku;
import com.ydy.service.order.OrderService;
import com.ydy.utils.DateUtil;
import com.ydy.utils.ValidateUtil;
import com.ydy.vo.OrderVo;
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
	@Autowired
	private ReductionMapper reductionMapper;

	@Override
	public PageVo<Order> select(Order order, Integer page, Integer size) {
		PageVo<Order> vo = new PageVo<Order>(page, size);
		Page<Order> pageBean = PageHelper.startPage(vo.getPage(), vo.getSize(), "order_id desc");
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
	public BillDTO calculateBill(BillDTO bill) {
		// 校验数据有效
		Map<String, String> validateInfo = ValidateUtil.validateEntity(bill);
		if (!validateInfo.isEmpty()) {
			throw new ValidateException(validateInfo);
		}
		List<ItemDTO> items = bill.getItems();
		for (ItemDTO dto : items) {
			validateInfo = ValidateUtil.validateEntity(dto);
			if (!validateInfo.isEmpty()) {
				throw new ValidateException(validateInfo);
			}
		}
		return calculate(bill);
	}

	private BillDTO calculate(BillDTO bill) {
		if (bill == null) {
			throw new NullPointerException();
		}
		Long totalPay = 0L;
		Long actualPay = 0L;
		totalPay += bill.getPostFee();// 邮费
		actualPay += bill.getPostFee();// 邮费
		Sku sku = null;
		Date now = new Date();
		for (ItemDTO item : bill.getItems()) {
			sku = skuMapper.selectByPrimaryKey(item.getSkuId());
			if (sku == null) {
				throw new DataNotFoundException(EnumGood.SKU_NOT_FOUND);
			}
			Reduction reduction = null;
			if (item.getReductionId() != null) {
				reduction = reductionMapper.selectByPrimaryKey(item.getReductionId());
			}
			Long itemActualTotal = 0L;
			boolean reductionFlag = false;
			if (reduction != null && item.getNum() >= reduction.getLimitNum()) {
				if (now.getTime() > reduction.getStartTime().getTime()
						&& now.getTime() < reduction.getEndTime().getTime()) {// 处于优惠活动期间
					reductionFlag = true;
				}
			}
			if (reductionFlag) {
				itemActualTotal = reduction.getPrice() * item.getNum();
			} else {
				itemActualTotal = sku.getNowPrice() * item.getNum();
			}
			Long itemTotal = (sku.getNowPrice() * item.getNum());
			actualPay += itemActualTotal;
			totalPay += itemTotal;
			item.setSku(sku);
			item.setItemActualTotal(itemActualTotal);
			item.setItemTotal(itemTotal);
			item.setUnitPrice(sku.getNowPrice());
		}
		bill.setActualPay(actualPay);
		bill.setTotalPay(totalPay);
		return bill;
	}

	@Override
	public Order createOrder(OrderDTO dto) {
		// 校验数据有效
		Map<String, String> validateInfo = ValidateUtil.validateEntity(dto);
		if (!validateInfo.isEmpty()) {
			throw new ValidateException(validateInfo);
		}
		Long orderId = createOrderId();
		BillDTO billDTO = calculate(dto);
		List<ItemDTO> items = billDTO.getItems();
		List<OrderDetail> detailList = new LinkedList<OrderDetail>();
		OrderDetail detail = null;
		for (ItemDTO data : items) {
			detail = new OrderDetail();
			detail.setOrderId(orderId);
			detail.setSkuId(data.getSkuId());
			detail.setNum(data.getNum());
			detail.setPrice(data.getUnitPrice());
			detail.setTotalPrice(data.getItemActualTotal());
			detail.setTitle(data.getSku().getSpuSpecs());
			detail.setImage(data.getSku().getMainImage());
			detailList.add(detail);
		}
		Date now = new Date();
		Order order = new Order();
		order.setOrderId(orderId);
		order.setTotalPay(billDTO.getTotalPay());
		order.setActualPay(billDTO.getActualPay());
		order.setPaymentType(billDTO.getPaymentType());
		order.setPostFee(billDTO.getPostFee());
		order.setCreateTime(now);
		order.setUserId(billDTO.getUserId());
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
		orderDetailMapper.insertList(detailList);
		order.setOrderDetails(detailList);
		OrderStatus status = new OrderStatus();
		status.setOrderStatus(OrderStatusEnum.COMMIT.getCode());
		status.setOrderId(orderId);
		status.setCreateTime(now);
		status.setOrderStatus(status.getOrderStatus());
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
		order.setOrderId(orderId);
		order.setShippingName(shippingName);
		order.setShippingCode(shippingCode);
		orderMapper.updateByPrimaryKeySelective(order);
		return new ResultVo(EnumSystem.SUSS);
	}

	@Override
	public BaseVo updateOrderStatusConfirm(Long orderId, Long userId) {
		Order order = orderMapper.selectByPrimaryKey(orderId);
		if (order == null) {
			throw new DataNotFoundException(EnumOrder.ORDER_NOT_FOUND);
		}
		if (!Objects.equals(userId, order.getUserId())) {
			throw new BusinessException(EnumSystem.NO_AUTH);
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
			throw new DataNotFoundException(EnumOrder.ORDER_NOT_FOUND);
		}
		OrderStatus status = new OrderStatus();
		status.setOrderId(orderId);
		status.setOrderStatus(statusEnum.getCode());
		Date now = new Date();
		if (OrderStatusEnum.CLOSED.equals(statusEnum)) {
			status.setCloseTime(now);
		} else if (OrderStatusEnum.PAY.equals(statusEnum)) {
			if (!Objects.equals(OrderStatusEnum.COMMIT.getCode(), temp.getOrderStatus())) {
				throw new BusinessException(EnumOrder.ORDER_STATUS_ERROR);
			}
			status.setPaymentTime(now);
		} else if (OrderStatusEnum.SEND.equals(statusEnum)) {
			if (!Objects.equals(OrderStatusEnum.PAY.getCode(), temp.getOrderStatus())) {
				throw new BusinessException(EnumOrder.ORDER_STATUS_ERROR);
			}
			status.setConsignTime(now);
		} else if (OrderStatusEnum.CONFIRM.equals(statusEnum)) {
			if (!Objects.equals(OrderStatusEnum.SEND.getCode(), temp.getOrderStatus())) {
				throw new BusinessException(EnumOrder.ORDER_STATUS_ERROR);
			}
			status.setEndTime(now);
		} else if (OrderStatusEnum.COMMENT.equals(statusEnum)) {
			if (!Objects.equals(OrderStatusEnum.CONFIRM.getCode(), temp.getOrderStatus())) {
				throw new BusinessException(EnumOrder.ORDER_STATUS_ERROR);
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

	@Override
	public BaseVo delete(Long id) {
		if (id == null) {
			throw new NullPointerException();
		}
		Order order = orderMapper.selectByPrimaryKey(id);
		if (order == null) {
			throw new DataNotFoundException(EnumOrder.ORDER_NOT_FOUND);
		}
		orderMapper.deleteByPrimaryKey(id);
		orderStatusMapper.deleteByPrimaryKey(id);
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrderId(id);
		orderDetailMapper.delete(orderDetail);
		return new ResultVo();
	}

	@Override
	public BaseVo delete(Long orderId, Long userId) {
		if (orderId == null) {
			throw new NullPointerException();
		}
		Order order = orderMapper.selectByPrimaryKey(orderId);
		if (order == null) {
			throw new DataNotFoundException(EnumOrder.ORDER_NOT_FOUND);
		}
		if (!Objects.equals(order.getUserId(), userId)) {
			throw new BusinessException(EnumSystem.NO_AUTH);
		}
		orderMapper.deleteByPrimaryKey(orderId);
		orderStatusMapper.deleteByPrimaryKey(orderId);
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrderId(orderId);
		orderDetailMapper.delete(orderDetail);
		return new ResultVo();
	}

	@Override
	public OrderVo selectById(Long id) {
		if (id == null) {
			throw new NullPointerException();
		}
		Order order = orderMapper.selectByPrimaryKey(id);
		if (order == null) {
			throw new DataNotFoundException(EnumOrder.ORDER_NOT_FOUND);
		}
		OrderVo vo = new OrderVo();
		BeanUtils.copyProperties(order, vo);
		OrderStatus status = orderStatusMapper.selectByPrimaryKey(order.getOrderId());
		if (status == null) {
			throw new DataNotFoundException(EnumOrder.ORDER_STATUS_NOT_FOUND);
		}
		vo.setOrderStatus(status);
		vo.setStatus(status.getOrderStatus());
		OrderDetail detail = new OrderDetail();
		detail.setOrderId(order.getOrderId());
		List<OrderDetail> list = orderDetailMapper.select(detail);
		vo.setOrderDetails(list);
		return vo;
	}

	@Override
	public OrderVo selectById(Long id, Long userId) {
		if (id == null) {
			throw new NullPointerException();
		}
		Order order = orderMapper.selectByPrimaryKey(id);
		if (order == null) {
			throw new DataNotFoundException(EnumOrder.ORDER_NOT_FOUND);
		}
		if (!Objects.equals(userId, order.getUserId())) {
			throw new BusinessException(EnumSystem.NO_AUTH);
		}
		OrderVo vo = new OrderVo();
		BeanUtils.copyProperties(order, vo);
		OrderStatus status = orderStatusMapper.selectByPrimaryKey(order.getOrderId());
		if (status == null) {
			throw new DataNotFoundException(EnumOrder.ORDER_STATUS_NOT_FOUND);
		}
		vo.setOrderStatus(status);
		vo.setStatus(status.getOrderStatus());
		OrderDetail detail = new OrderDetail();
		detail.setOrderId(order.getOrderId());
		List<OrderDetail> list = orderDetailMapper.select(detail);
		vo.setOrderDetails(list);
		return vo;
	}

}
