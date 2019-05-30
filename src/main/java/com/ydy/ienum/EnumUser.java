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
public enum EnumUser implements IErrorEnum {
	NOT_FOUND(9000, "找不到该用户名"),
	PWD_ERROR(9001, "用户名密码错误"),
	CAN_NOT_USE_STATUS(9002, "用户状态不可用"),
	DATA_NOT_FOUND(9003, "找不到用户信息"),
	USERNAME_EXSIT(9004, "用户名已存在");

	private Integer code;
	private String msg;

	private EnumUser(int code, String msg) {
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
