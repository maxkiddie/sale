/**
 * 
 */
package com.ydy.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author xuzhaojie
 *
 *         2018年11月29日 下午5:56:41
 */
@Aspect // 作用是把当前类标识为一个切面供容器读取
@Component
public class LogAspect {

//	private final static Logger log = LoggerFactory.getLogger(LogAspect.class);

	/**
	 * execution(方法修饰符(可选) 返回类型 方法名 参数 异常模式(可选)) 修饰符 通配符(*表示所有，可定义void\String
	 * 返回值为具体类型的方法) 包名 类名 方法名 参数 定义AOP扫描的路径，第一个路径只扫描controller中的一个方法，两个点表示任何参数
	 */
	@Pointcut("execution(public * com.ydy.controller.*.*(..))")
	public void log() {
	}

	/**
	 * 获取返回内容
	 * 
	 * @param object
	 */
	@AfterReturning(returning = "object", pointcut = "log()")
	public void doAfterReturn(Object object) {
//			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
//					.getRequestAttributes();
	}

}
