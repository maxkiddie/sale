/**
 * 
 */
package com.ydy.service.task.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ydy.model.OrderStatus;
import com.ydy.service.order.OrderService;
import com.ydy.service.task.TaskService;
import com.ydy.utils.DateUtil;

/**
 * @author xuzhaojie
 *
 *         2019年3月5日 上午11:25:29
 */
@Service
public class TaskServiceImpl implements TaskService {

	private final static Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

	@Autowired
	private OrderService orderService;

	@Value("${NOT_UPDATE_TIME_LONG:0d2h0m0s}")
	private String NOT_UPDATE_TIME_LONG;

	@Scheduled(cron = "${CHECK_STOCK_TASK:0 0/5 * * * ?}")
	public void orderClose() {
		Long timeLong = DateUtil.formatTimeLong(NOT_UPDATE_TIME_LONG);
		Date now = new Date();
		Date createTime = new Date(now.getTime() - timeLong);
		log.info("查询订单创建时间小于:" + DateUtil.getDateStrByDateFormat(createTime));
		List<OrderStatus> list = orderService.selectStatusCommit(createTime);
		if (!CollectionUtils.isEmpty(list)) {
			orderService.updateOrderStatusListClosed(list);
		} else {
			log.info("无过期订单");
		}
	}
}
