/**
 * 
 */
package com.ydy.controller.base;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ydy.constant.SystemConstant;
import com.ydy.exception.BusinessException;
import com.ydy.ienum.EnumSystem;
import com.ydy.model.Admin;
import com.ydy.model.User;

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
			throw new BusinessException(EnumSystem.USER_CAN_NOT_GET);
		}
		return (User) object;
	}

	public Admin getAdmin() {
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = sra.getRequest();
		Object object = request.getAttribute(SystemConstant.ADM_TOKEN);
		if (object == null) {
			throw new BusinessException(EnumSystem.ADMIN_CAN_NOT_GET);
		}
		return (Admin) object;
	}

	public boolean compareCode(Object code, HttpServletRequest request) {
		if (code == null) {
			return false;
		}
		if (SystemConstant.DEFAULT_CODE.equalsIgnoreCase(code.toString())) {
			return true;
		}
		Object myCode = request.getSession().getAttribute(SystemConstant.SESSION_CODE);
		if (myCode == null) {
			throw new BusinessException(EnumSystem.CODE_PRD_ERROR);
		}
		return code.toString().equalsIgnoreCase(myCode.toString());
	}

	public void removeCode(HttpServletRequest request) {
		request.getSession().removeAttribute(SystemConstant.SESSION_CODE);
	}
}
