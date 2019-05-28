/**
 * 
 */
package com.ydy.controller.base;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ydy.constant.SystemConstant;
import com.ydy.exception.MyException;
import com.ydy.model.User;
import com.ydy.vo.ienum.EnumSystem;

/**
 * @author xuzhaojie
 *
 *         2019年5月28日 上午9:18:24
 */
public abstract class BaseController {
	public User getUser() {
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = sra.getRequest();
		Object object = request.getAttribute(SystemConstant.USER_TOKEN);
		if (object == null) {
			throw new MyException(EnumSystem.USER_CAN_NOT_GET);
		}
		return (User) object;
	}
}
