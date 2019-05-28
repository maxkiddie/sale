/**
 * 
 */
package com.ydy.exception;

import com.ydy.vo.ienum.base.IErrorEnum;

/**
 * @author xuzhaojie
 *
 *         2018年8月3日 上午11:54:23
 */
public class MyException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IErrorEnum errorEnum;

	public MyException(String message) {
		this(message, null);
	}

	public MyException(String message, Throwable cause) {
		super(message, cause);
	}

	public MyException(IErrorEnum errorEnum) {
		this.errorEnum = errorEnum;
	}

	/**
	 * @return the errorEnum
	 */
	public IErrorEnum getErrorEnum() {
		return errorEnum;
	}

	/**
	 * @param errorEnum
	 *            the errorEnum to set
	 */
	public void setErrorEnum(IErrorEnum errorEnum) {
		this.errorEnum = errorEnum;
	}

}
