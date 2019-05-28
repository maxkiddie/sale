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
import com.ydy.annotation.AdminToken;
import com.ydy.interceptor.base.BaseInterceptor;
import com.ydy.model.Admin;
import com.ydy.utils.TokenUtil;
import com.ydy.vo.ienum.EnumSystem;
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
public class AdminTokenInterceptor extends BaseInterceptor {

	private final static Logger log = LoggerFactory.getLogger(AdminTokenInterceptor.class);

	// 在控制器执行前调用
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HandlerMethod m = (HandlerMethod) handler;// 强转原来的类型
		AdminToken an = m.getMethod().getAnnotation(AdminToken.class);// 查看该方法有无权限注解
		if (an == null) {// 没有该注解，放行
			return true;
		}
		String token = request.getParameter("token");
		if (token == null || "".equals(token)) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			PrintWriter out = null;
			out = response.getWriter();
			BaseVo vo = new ResultVo(EnumSystem.TOKEN_NOT_EXSIT);
			out.append(JSONObject.toJSONString(vo));
			return false;
		}
		try {
			Admin admin = TokenUtil.getAdmin(token);// 解释cookie,获取admin信息
			// 后续涉及权限等级再完善
			admin.toString();
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
