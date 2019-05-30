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
public enum EnumGood implements IErrorEnum {
	SKU_NOT_FOUND(5000, "Sku不存在"),
	SPU_NOT_FOUND(5001, "Spu不存在"),
	SPU_OFF(5002, "Spu未上架或不存在"),
	SKU_OFF(5003, "Sku未上架或不存在"),
	SPU_SKU_NOT_RELATION(5004, "SPU,SKU不存在关联关系");

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
