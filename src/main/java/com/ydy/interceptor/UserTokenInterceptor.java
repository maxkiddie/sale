/**
 * 
 */
package com.ydy.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import com.alibaba.fastjson.JSONObject;
import com.ydy.annotation.UserToken;
import com.ydy.constant.SystemConstant;
import com.ydy.ienum.EnumSystem;
import com.ydy.interceptor.base.BaseInterceptor;
import com.ydy.model.User;
import com.ydy.utils.TokenUtil;
import com.ydy.vo.other.BaseVo;
import com.ydy.vo.other.ResultVo;

/**
 * 权限token拦截器
 * 
 * @author xuzhaojie
 *
 *         2019年1月16日 下午20:04:07
 */
@Component
public class UserTokenInterceptor extends BaseInterceptor {

	private final static Logger log = LoggerFactory.getLogger(UserTokenInterceptor.class);

	// 在控制器执行前调用
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HandlerMethod m = (HandlerMethod) handler;// 强转原来的类型
		UserToken an = m.getMethod().getAnnotation(UserToken.class);// 查看该方法有无权限注解
		if (an == null) {// 没有该注解，放行
			return true;
		}
		String token = request.getParameter("utoken");
		if (token == null || "".equals(token)) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			PrintWriter out = null;
			out = response.getWriter();
			BaseVo vo = new ResultVo(EnumSystem.USER_TOKEN_NOT_EXSIT);
			out.append(JSONObject.toJSONString(vo));
			return false;
		}
		try {
			User user = TokenUtil.getUser(token);// 解释cookie,获取admin信息
			request.setAttribute(SystemConstant.USER_TOKEN, user);
			// 后续涉及权限等级再完善
			user.toString();
			return true;
		} catch (Exception e) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			PrintWriter out = null;
			out = response.getWriter();
			BaseVo vo = new ResultVo(EnumSystem.TOKEN_NOT_MATCH);
			out.append(JSONObject.toJSONString(vo));
			log.error("token被篡改");
			return false;
		}
	}

}
