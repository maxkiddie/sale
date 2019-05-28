/**
 * 
 */
package com.ydy.controller;

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

import com.ydy.annotation.AdminToken;
import com.ydy.annotation.CheckFormRepeat;
import com.ydy.annotation.CtrlParam;
import com.ydy.constant.SystemConstant;
import com.ydy.exception.MyException;
import com.ydy.model.Admin;
import com.ydy.service.admin.AdminService;
import com.ydy.service.redis.RedisService;
import com.ydy.utils.StringUtils;
import com.ydy.vo.ienum.EnumSystem;
import com.ydy.vo.other.BaseVo;
import com.ydy.vo.other.PageVo;
import com.ydy.vo.other.ResultVo;
import com.ydy.vo.token.AdminTokenVo;
import com.ydy.vo.token.TokenVo;

/**
 * @author xuzhaojie
 *
 *         2019年1月18日 上午9:17:29
 */
@Controller
@RequestMapping(value = "admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private RedisService redisService;

	/**
	 * 筛选管理员信息
	 * 
	 * @return
	 */
	@GetMapping("select")
	@ResponseBody
	public ResponseEntity<PageVo<Admin>> select(Admin admin, Integer page, Integer size) {
		StringUtils.setParamEmptyToNull(admin);
		PageVo<Admin> vo = adminService.selectData(admin, page, size);
		return ResponseEntity.ok(vo);
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
		String redisCode = redisService.getCode(request.getSession().getId());
		if (redisCode == null || "".equals(redisCode)) {
			throw new MyException(EnumSystem.CODE_EXPIRED);
		}
		if (!redisCode.equalsIgnoreCase(code)) {
			throw new MyException(EnumSystem.CODE_ERROR);
		}
		Admin admin = new Admin(username, password);
		AdminTokenVo vo = adminService.checkAdmin(admin);
		if (vo != null) {
			Cookie cookie = new Cookie(SystemConstant.COOKIE_ADM_TOKEN, vo.getToken());
			cookie.setPath("/");
			response.addCookie(cookie);
			redisService.delCode(request.getSession().getId());
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
		Cookie cookie = new Cookie(SystemConstant.COOKIE_ADM_TOKEN, "");
		cookie.setPath("/");
		response.addCookie(cookie);
		return ResponseEntity.ok(vo);
	}

	/**
	 * 保存管理员信息
	 * 
	 * @param response
	 * @return
	 */
	@AdminToken
	@CheckFormRepeat
	@PostMapping("save")
	@ResponseBody
	public ResponseEntity<Admin> save(Admin admin) {
		return ResponseEntity.ok(adminService.saveOrUpdate(admin));
	}

	/**
	 * 查找管理员信息
	 * 
	 * @param response
	 * @return
	 */
	@AdminToken
	@GetMapping("/selectById")
	@ResponseBody
	public ResponseEntity<Admin> selectById(@CtrlParam("主键") Integer id) {
		return ResponseEntity.ok(adminService.selectById(id));
	}

	/**
	 * 重置管理员密码
	 * 
	 * @param response
	 * @return
	 */
	@AdminToken
	@CheckFormRepeat
	@PostMapping("/resetPassWord")
	@ResponseBody
	public ResponseEntity<BaseVo> resetPassWord(@CtrlParam("主键") Integer id) {
		return ResponseEntity.ok(adminService.resetPassWord(id));
	}

	/**
	 * 更新管理员使用状态
	 * 
	 * @param response
	 * @return
	 */
	@AdminToken
	@PostMapping("/updateUseStatus")
	@ResponseBody
	public ResponseEntity<BaseVo> updateUseStatus(@CtrlParam("主键") Integer id) {
		return ResponseEntity.ok(adminService.updateUseStatus(id));
	}

}