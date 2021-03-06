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
public enum EnumPersonInfo implements IErrorEnum {
	DATA_NOT_FOUND(7000,"找不到人物信息"),
	PERSON_ORDER_RELATION_EMPTY(7001,"订单-人物关系不能为空");

	private Integer code;
	private String msg;

	private EnumPersonInfo(int code, String msg) {
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
