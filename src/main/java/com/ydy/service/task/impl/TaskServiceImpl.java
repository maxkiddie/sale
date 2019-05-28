/**
 * 
 */
package com.ydy.service.task.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ydy.service.task.TaskService;

/**
 * @author xuzhaojie
 *
 *         2019年3月5日 上午11:25:29
 */
@Service
public class TaskServiceImpl implements TaskService {


	@Value("${NOT_UPDATE_TIME_LONG:0d12h0m0s}")
	private String NOT_UPDATE_TIME_LONG;

	@Scheduled(cron = "${CHECK_STOCK_TASK:0 0 0,6,12,18 * * ?}")
	public void stockUploadCheckTask() {
		
	}
}
