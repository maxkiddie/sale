/**
 * 
 */
package com.ydy.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

/**
 * @author xuzhaojie
 *
 *         2019年1月21日 下午5:07:55
 */
//@Component到时候去掉注释即可用
public class DataInit implements CommandLineRunner {

	private final static Logger log = LoggerFactory.getLogger(DataInit.class);

	@Override
	public void run(String... args) throws Exception {
		log.info("DataInit开始初始化...");
		log.info("DataInit初始化成功...");
	}

}
