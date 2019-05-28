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
public enum EnumSystem implements IErrorEnum{
	SUSS(0, "请求成功"),
	SYSTEM_ERROR(1000, "系统异常"),
	PARAM_ERROR(1001, "参数缺失"),
	PARAM_FORMAT_ERROR(1002, "参数格式错误"),
	DATA_NOT_MATCH(1003, "数据填写有误"),
	DATA_NOT_FOUND(1004, "找不到数据"),
	TOKEN_NOT_MATCH(1005, "token不匹配"),
	TOKEN_NOT_EXSIT(1006, "token是必须参数"),
	USER_TOKEN_NOT_EXSIT(1006, "utoken是必须参数"),
	USER_CAN_NOT_GET(1006, "系统获取不到用户信息"),
	ADMIN_CAN_NOT_GET(1007, "系统获取不到管理员信息"),
	CODE_EXPIRED(1007, "验证码已过期"),
	CODE_ERROR(1008, "验证码不匹配"),
	DATA_REPEAT(1009, "表单重复提交"),
	NO_AUTH(1010, "没权限"),
	PWD_NOT_FIT(1010, "两次密码不匹配"),
	FILE_TYPE_NOT_FIT(1011, "文件类型不匹配"),
	REMOTE_IP_ERROR(1012, "远程网络出现问题");
	private Integer code;
	private String msg;

	private EnumSystem(int code, String msg) {
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
