/**
 * 
 */
package com.ydy.vo.ienum;

import com.ydy.vo.ienum.base.IErrorEnum;

/**
 * @author xuzhaojie
 *
 *         2018年11月12日 上午9:41:48
 */
public enum EnumOrder implements IErrorEnum {
	ORDER_DETAIL_EMPTY(8000, "订单条目不能为空"),
	ORDER_NOT_FOUND(8001, "订单不存在"),
	ADMIN_NOT_FOUND(9004, "管理员不存在");

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
