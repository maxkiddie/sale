package com.ydy.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.util.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ydy.utils.FileUtil;
import com.ydy.utils.StringUtils;
import com.ydy.vo.other.FileDto;

/**
 * 日志控制器
 * @author xuzhaojie
 *
 * 2019年5月16日  下午4:12:44
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

	/**
	 * 下载日志文件
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("download")
	public ResponseEntity<byte[]> download(@RequestParam String path) throws IOException {
		File file = new File(path);
		HttpHeaders headers = new HttpHeaders();
		String fileName = new String(file.getName().getBytes("UTF-8"), "iso-8859-1");// 为了解决中文名称乱码问题
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(IOUtils.toByteArray(new FileInputStream(file), (int) file.length()), headers,
				HttpStatus.CREATED);
	}

}
