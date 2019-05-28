package com.ydy.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ydy.constant.SystemConstant;
import com.ydy.utils.VelidateImageUtil;

@Controller
@RequestMapping("code")
public class CodeController {

	@RequestMapping("imgCode")
	public void imageCode(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		// 生成随机字串
		String verifyCode = VelidateImageUtil.generateVerifyCode(4);
		request.getSession().setAttribute(SystemConstant.SESSION_CODE, verifyCode);
		// 生成图片
		try {
			VelidateImageUtil.outputImage(response.getOutputStream(), verifyCode);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
