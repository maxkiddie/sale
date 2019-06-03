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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import com.alibaba.fastjson.JSONObject;
import com.ydy.annotation.CheckFormRepeat;
import com.ydy.constant.RedisConstant;
import com.ydy.ienum.EnumSystem;
import com.ydy.interceptor.base.BaseInterceptor;
import com.ydy.service.redis.RedisService;
import com.ydy.vo.other.BaseVo;
import com.ydy.vo.other.ResultVo;

/**
 * 检查表单重复提交拦截器
 * 
 * @author xuzhaojie
 *
 *         2018年12月26日 下午4:04:07
 */
@Component
public class CheckFormRepeatInterceptor extends BaseInterceptor {

	private final static Logger log = LoggerFactory.getLogger(CheckFormRepeatInterceptor.class);

	@Autowired
	private RedisService redisService;

	// 在控制器执行前调用
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod m = (HandlerMethod) handler;// 强转原来的类型
			CheckFormRepeat an = m.getMethod().getAnnotation(CheckFormRepeat.class);
			if (an == null) {
				return true;
			}
			StringBuilder builder = new StringBuilder();
			builder.append(RedisConstant.REQUEST_SESSION);// redis前缀
			builder.append(request.getSession().getId());// 会话id
			builder.append(request.getRequestURI());// 请求路径
			String key = builder.toString();// 作为redis的键
			if (!redisService.hasKey(key)) {
				redisService.setString(key, "1", RedisConstant.FORM_REPEAT_EXPIRE_TIME);
				return true;
			} else {
				log.info("重复提交表单:" + key);
				response.setCharacterEncoding("UTF-8");
				response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
				response.setStatus(HttpStatus.BAD_REQUEST.value());
				PrintWriter out = null;
				out = response.getWriter();
				BaseVo vo = new ResultVo(EnumSystem.DATA_REPEAT);
				out.append(JSONObject.toJSONString(vo));
				return false;
			}
		} else {
			return true;
		}
	}

}
