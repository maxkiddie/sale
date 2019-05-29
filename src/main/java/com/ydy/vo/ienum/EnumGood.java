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
public enum EnumGood implements IErrorEnum {
	SKU_NOT_FOUND(7000, "Sku不存在"),
	SPU_OFF(7001, "Spu未上架或不存在"),
	SKU_OFF(7002, "Sku未上架或不存在"),
	ADMIN_NOT_FOUND(9004, "管理员不存在");

	private Integer code;
	private String msg;

	private EnumGood(int code, String msg) {
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
