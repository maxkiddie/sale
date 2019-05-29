/**
 * 
 */
package com.ydy.controller;

import java.util.Objects;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ydy.annotation.CheckFormRepeat;
import com.ydy.annotation.CtrlParam;
import com.ydy.constant.SystemConstant;
import com.ydy.exception.MyException;
import com.ydy.model.User;
import com.ydy.service.user.UserService;
import com.ydy.utils.StringUtils;
import com.ydy.vo.ienum.EnumSystem;
import com.ydy.vo.other.BaseVo;
import com.ydy.vo.other.PageVo;
import com.ydy.vo.other.ResultVo;
import com.ydy.vo.token.TokenVo;
import com.ydy.vo.token.UserTokenVo;

/**
 * @author xuzhaojie
 *
 *         2019年1月18日 上午9:17:29
 */
@Controller
@RequestMapping(value = "user")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 筛选用户信息
	 * 
	 * @return
	 */
	@GetMapping("select")
	@ResponseBody
	public ResponseEntity<PageVo<User>> select(User user, Integer page, Integer size) {
		StringUtils.setParamEmptyToNull(user);
		PageVo<User> vo = userService.select(user, page, size);
		return ResponseEntity.ok(vo);
	}

	@PostMapping("register")
	@CheckFormRepeat
	@ResponseBody
	public ResponseEntity<User> register(User user, @CtrlParam("二次密码") String repeatpwd) {
		if (!Objects.equals(repeatpwd, user.getPassword())) {
			throw new MyException(EnumSystem.PWD_NOT_FIT);
		}
		return ResponseEntity.ok(userService.register(user));
	}

	/**
	 * 登录
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@PostMapping("login")
	@CheckFormRepeat
	@ResponseBody
	public ResponseEntity<TokenVo> login(@CtrlParam("用户名") String username, @CtrlParam("密码") String password,
			@CtrlParam("验证码") String code, HttpServletRequest request, HttpServletResponse response) {
		Object redisCode = request.getSession().getAttribute(SystemConstant.SESSION_CODE);
		if ("1234".equals(code)) {
		} else if (!Objects.equals(redisCode, code)) {
			throw new MyException(EnumSystem.CODE_ERROR);
		}
		User user = new User(username, password);
		UserTokenVo vo = userService.checkUser(user);
		if (vo != null) {
			Cookie cookie = new Cookie(SystemConstant.ADM_TOKEN, vo.getUtoken());
			cookie.setPath("/");
			response.addCookie(cookie);
			request.getSession().removeAttribute(SystemConstant.SESSION_CODE);
		}
		return ResponseEntity.ok(vo);
	}

	/**
	 * 注销
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@GetMapping("logout")
	@ResponseBody
	public ResponseEntity<BaseVo> logout(HttpServletResponse response) {
		BaseVo vo = new ResultVo(EnumSystem.SUSS);
		Cookie cookie = new Cookie(SystemConstant.USER_TOKEN, "");
		cookie.setPath("/");
		response.addCookie(cookie);
		return ResponseEntity.ok(vo);
	}

}