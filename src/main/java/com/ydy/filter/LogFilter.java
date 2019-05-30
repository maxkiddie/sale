/**
 * 
 */
package com.ydy.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ydy.filter.base.BaseFilter;
import com.ydy.utils.IpUtil;

/**
 * @author xuzhaojie
 *
 *         2018年11月13日 下午4:19:57
 */
@Component
public class LogFilter extends BaseFilter {

	public final static String REQUEST_ID = "Q";
	private final static Logger log = LoggerFactory.getLogger(LogFilter.class);

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		Long start = System.currentTimeMillis();
		String reqId = start + "";
		chain.doFilter(servletRequest, servletResponse);
		log.info(IpUtil.getIpAddress(request) + "-req=" + reqId + "-[" + request.getRequestURI().toString() + "]-"
				+ "耗时" + (System.currentTimeMillis() - start) + "ms");
	}

}
