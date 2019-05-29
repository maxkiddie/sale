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
public enum EnumUser implements IErrorEnum {
	NOT_FOUND(6000, "找不到该用户名"),
	PWD_ERROR(6001, "用户名密码错误"),
	CAN_NOT_USE_STATUS(6002, "用户状态不可用"),
	DATA_NOT_FOUND(6002, "找不到用户信息"),
	USERNAME_EXSIT(6003, "用户名已存在"),
	USER_NOT_FOUND(6004, "管理员不存在");

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
