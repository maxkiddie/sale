/**
 * 
 */
package com.ydy.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ydy.exception.BusinessException;
import com.ydy.ienum.EnumSystem;
import com.ydy.utils.DateUtil;
import com.ydy.utils.FileUtil;

/**
 * @author xuzhaojie
 *
 *         2018年12月17日 上午9:17:29
 */
@Controller
@RequestMapping(value = "file")
public class FileController {

	private final String[] IMG_FORMAT = { "jpg", "jpeg", "png" };

	@Value("${img_loaction}")
	private String imgPath;

	@Value("${img_httpurl}")
	private String imgHttpurl;

	@PostMapping("imgUpload")
	@ResponseBody
	public ResponseEntity<String> imgUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request)
			throws Exception {
		String fileName = file.getOriginalFilename(); // 图片名字
		if (!FileUtil.isFitFormat(fileName, IMG_FORMAT)) {
			throw new BusinessException(EnumSystem.FILE_TYPE_NOT_FIT);
		}
		// 文件存放路径
		String filePath = File.separator + DateUtil.getTodatDate();
		String abPath = imgPath + filePath;
		fileName = System.currentTimeMillis() + fileName;
		String httpUrl = imgHttpurl + filePath + File.separator + fileName;
		// 调用文件处理类FileUtil，处理文件，将文件写入指定位置
		filePath = FileUtil.uploadFile(file.getBytes(), abPath, fileName);
		// 返回图片的存放路径
		return ResponseEntity.ok(httpUrl);
	}

	/**
	 * 设置http响应头，下载文件
	 * 
	 * @param filename
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("unused")
	private void setDownloadFile(String filename, HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		response.setContentType("application/force-download");// 设置强制下载不打开
		// 获得请求头中的User-Agent
		String agent = request.getHeader("User-Agent");
		// 根据不同浏览器进行不同的编码
		String filenameEncoder = "";
		if (agent.contains("MSIE")) {
			// IE浏览器
			filenameEncoder = URLEncoder.encode(filename, "utf-8");
			filenameEncoder = filenameEncoder.replace("+", " ");
		} else if (agent.contains("Firefox")) {
			// 火狐浏览器
			filenameEncoder = "=?utf-8?B?" + Base64.getEncoder().encodeToString(filename.getBytes("utf-8")) + "?=";
		} else {
			// 其它浏览器
			filenameEncoder = URLEncoder.encode(filename, "utf-8");
		}
		response.addHeader("Content-Disposition", "attachment;fileName=" + filenameEncoder);// 设置文件名
	}

}