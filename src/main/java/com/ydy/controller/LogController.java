package com.ydy.controller;

import java.io.FileNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ydy.utils.FileUtil;
import com.ydy.utils.StringUtils;
import com.ydy.vo.other.FileDto;

/**
 * 日志控制器
 * 
 * @author xuzhaojie
 *
 *         2019年5月16日 下午4:12:44
 */
@Controller
@RequestMapping("/log")
public class LogController {

	@RequestMapping("insert")
	@ResponseBody
	public ResponseEntity<String> insert() {
		return ResponseEntity.status(HttpStatus.OK).body("sus");
	}

	/**
	 * 获取日志列表
	 * 
	 * @param path
	 * @return
	 */
	@RequestMapping(value = "getLog")
	@ResponseBody
	public ResponseEntity<FileDto> log(String path) {
		FileDto dto = null;
		if (StringUtils.isEmpty(path)) {
			path = "./logs";
		}
		try {
			dto = FileUtil.getFile(path);
		} catch (FileNotFoundException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

}
