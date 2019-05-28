/**
 * 
 */
package com.ydy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ydy.annotation.AdminToken;
import com.ydy.annotation.CtrlParam;
import com.ydy.annotation.UserToken;
import com.ydy.controller.base.BaseController;
import com.ydy.dto.OrderDTO;
import com.ydy.model.Order;
import com.ydy.model.User;
import com.ydy.service.order.OrderService;
import com.ydy.utils.StringUtils;
import com.ydy.vo.other.BaseVo;
import com.ydy.vo.other.PageVo;

/**
 * @author xuzhaojie
 *
 *         2019年1月18日 上午9:17:29
 */
@Controller
@RequestMapping(value = "order")
public class OrderController extends BaseController {

	@Autowired
	private OrderService orderService;

	@AdminToken
	@GetMapping("select")
	@ResponseBody
	public ResponseEntity<PageVo<Order>> select(Order order, Integer page, Integer size) {
		StringUtils.setParamEmptyToNull(order);
		PageVo<Order> vo = orderService.selectData(order, page, size);
		return ResponseEntity.ok(vo);
	}

	@UserToken
	@GetMapping("selectByUserId")
	@ResponseBody
	public ResponseEntity<PageVo<Order>> selectByUserId(Order order, Integer page, Integer size) {
		StringUtils.setParamEmptyToNull(order);
		User user = getUser();
		order.setUserId(user.getId());
		PageVo<Order> vo = orderService.selectData(order, page, size);
		return ResponseEntity.ok(vo);
	}

	@UserToken
	@PostMapping("create")
	@ResponseBody
	public ResponseEntity<Order> create(@RequestBody OrderDTO dto) {
		User user = getUser();
		dto.setUserId(user.getId());
		dto.setBuyerNick(user.getUsername());
		Order order = orderService.createOrder(dto);
		return ResponseEntity.ok(order);
	}

	@AdminToken
	@PostMapping("pay")
	@ResponseBody
	public ResponseEntity<BaseVo> pay(@CtrlParam("订单ID") Long orderId) {
		return ResponseEntity.ok(orderService.updateOrderStatusPay(orderId));
	}

}