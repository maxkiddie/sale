/**
 * 
 */
package com.ydy.constant;

/**
 * @author xuzhaojie
 *
 *         2019年5月27日 下午5:45:53
 */
public enum OrderStatusEnum {

	CLOSED(0, "关闭"), COMMIT(1, "提交未付款"), PAY(2, " 已付款"), SEND(3, "已发货"), CONFIRM(4, "确认收货"), COMMENT(5, "已评价");

	private Integer code;
	private String msg;

	private OrderStatusEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(Integer code) {
		this.code = code;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
