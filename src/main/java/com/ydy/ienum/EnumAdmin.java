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
public enum EnumAdmin implements IErrorEnum {
	NOT_FOUND(2000, "找不到该管理员用户名"),
	PWD_ERROR(2001, "用户名密码错误"),
	CAN_NOT_USE_STATUS(2002, "用户状态不可用"),
	USERNAME_EXSIT(2003, "用户名已存在"),
	DATA_NOT_FOUND(2004,"找不到管理员"),
	ADMIN_NOT_FOUND(2005, "管理员不存在");

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
