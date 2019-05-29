package com.ydy.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ydy.mapper.base.BaseMapper;
import com.ydy.model.OrderStatus;

public interface OrderStatusMapper extends BaseMapper<OrderStatus> {

	List<OrderStatus> selectStatusCommit(@Param("orderStatus") Integer orderStatus,
			@Param("createTime") Date createTime);
}