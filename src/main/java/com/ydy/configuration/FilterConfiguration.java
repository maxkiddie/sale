/**
 * 
 */
package com.ydy.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ydy.filter.LogFilter;

/**
 * 过滤器配置
 * 
 * @author xuzhaojie
 *
 *         2018年11月12日 下午4:07:07
 */
@Configuration
public class FilterConfiguration extends WebMvcConfigurerAdapter {
	@Autowired
	private LogFilter logFilter;

	@Bean
	public FilterRegistrationBean buildLogFilter() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setOrder(1);
		filterRegistrationBean.setFilter(logFilter);
		filterRegistrationBean.setName("logFilter");
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
	}

}