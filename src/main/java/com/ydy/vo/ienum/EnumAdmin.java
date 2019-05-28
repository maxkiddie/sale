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
public enum EnumAdmin implements IErrorEnum {
	NOT_FOUND(9000, "找不到该管理员用户名"),
	PWD_ERROR(9001, "用户名密码错误"),
	CAN_NOT_USE_STATUS(9002, "用户状态不可用"),
	USERNAME_EXSIT(9003, "用户名已存在"),
	ADMIN_NOT_FOUND(9004, "管理员不存在");

	private Integer code;
	private String msg;

	private EnumAdmin(int code, String msg) {
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
