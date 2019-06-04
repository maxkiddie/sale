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
public enum EnumNews implements IErrorEnum {
	DATA_NOT_FOUND(1200,"找不到新闻");

	private Integer code;
	private String msg;

	private EnumNews(int code, String msg) {
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
