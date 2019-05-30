/**
 * 
 */
package com.ydy.constant;

/**
 * 系统静态常量
 * 
 * @author xuzhaojie
 *
 *         2018年12月3日 上午10:30:45
 */
public class SystemConstant {

	public static final String MANAGER_DEFAULT_PWD = "123456a";// 管理员cookies
	
	public static final String DEFAULT_CODE = "1234";// 管理员cookies

	public static final String ADM_TOKEN = "ADM_TOKEN";// 管理员cookies

	public static final String USER_TOKEN = "USER_TOKEN";// 用户cookies

	public static final String SESSION_CODE = "V_CODE";// 验证码key

	public static final Integer NOT_DELETE = 0;// 没删除状态

	public static final Integer HAS_DELETE = 1;// 已删除状态

	// 接口关闭状态
	public static final Integer SWITCH_STATUS_CLOSED = 0;

	public static final Integer SWITCH_STATUS_OPEN = 1;

	// 状态
	public static final Short USE_STATUS_ON = 1;

	public static final Short USE_STATUS_OFF = 0;
	// 状态
	public static final Integer SPU_ON = 1;
	
	public static final Integer SPU_OFF = 0;

}
