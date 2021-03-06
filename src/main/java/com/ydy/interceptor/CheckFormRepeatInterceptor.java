/**
 * 
 */
package com.ydy.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;

import com.ydy.interceptor.base.BaseInterceptor;

/**
 * 检查表单重复提交拦截器
 * 
 * @author xuzhaojie
 *
 *         2018年12月26日 下午4:04:07
 */
//@Component
public class CheckFormRepeatInterceptor extends BaseInterceptor {

	//private final static Logger log = LoggerFactory.getLogger(CheckFormRepeatInterceptor.class);

//	@Autowired
//	private RedisService redisService;

	// 在控制器执行前调用
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler instanceof HandlerMethod) {
			return true;
			/*HandlerMethod m = (HandlerMethod) handler;// 强转原来的类型
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
			}*/
		} else {
			return true;
		}
	}

}
