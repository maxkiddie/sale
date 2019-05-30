/**
 * 
 */
package com.ydy.ienum;

import com.ydy.ienum.base.IErrorEnum;

/**
 * @author xuzhaojie
 *
 *         2018年11月12日 上午9:41:48
 */
public enum EnumOrder implements IErrorEnum {
	ORDER_DETAIL_EMPTY(6000, "订单条目不能为空"),
	ORDER_NOT_FOUND(6001, "订单不存在"),
	ORDER_STATUS_NOT_FOUND(6002, "订单状态不存在"),
	ORDER_STATUS_ERROR(6003, "订单流程有误");

	private Integer code;
	private String msg;

	private EnumOrder(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	@Override
	public int getCode() {
		return code;
	}

	@Override
	public String getMsg() {
		return msg;
	}

}
