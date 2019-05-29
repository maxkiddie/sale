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
public enum EnumReduction implements IErrorEnum {
	DATA_NOT_FOUND(9004,"找不到优惠信息"),
	ADMIN_NOT_FOUND(9004, "管理员不存在");

	private Integer code;
	private String msg;

	private EnumReduction(int code, String msg) {
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