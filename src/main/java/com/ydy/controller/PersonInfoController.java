/**
 * 
 */
package com.ydy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ydy.annotation.AdminToken;
import com.ydy.annotation.UserToken;
import com.ydy.controller.base.BaseController;
import com.ydy.model.PersonInfo;
import com.ydy.model.User;
import com.ydy.service.personInfo.PersonInfoService;
import com.ydy.utils.StringUtils;
import com.ydy.vo.other.PageVo;

/**
 * @author xuzhaojie
 *
 *         2019年1月18日 上午9:17:29
 */
@Controller
@RequestMapping(value = "personInfo")
public class PersonInfoController extends BaseController {

	@Autowired
	private PersonInfoService personInfoService;

	@AdminToken
	@GetMapping("select")
	@ResponseBody
	public ResponseEntity<PageVo<PersonInfo>> select(PersonInfo info, Integer page, Integer size) {
		StringUtils.setParamEmptyToNull(info);
		PageVo<PersonInfo> vo = personInfoService.selectData(info, page, size);
		return ResponseEntity.ok(vo);
	}
	
	@UserToken
	@GetMapping("selectByUserId")
	@ResponseBody
	public ResponseEntity<PageVo<PersonInfo>> selectByUserId(PersonInfo info, Integer page, Integer size) {
		StringUtils.setParamEmptyToNull(info);
		User user = getUser();
		info.setUserId(user.getId());
		PageVo<PersonInfo> vo = personInfoService.selectData(info, page, size);
		return ResponseEntity.ok(vo);
	}

	@UserToken
	@PostMapping("save")
	@ResponseBody
	public ResponseEntity<PersonInfo> create(@RequestBody PersonInfo personInfo) {
		User user = getUser();
		personInfo.setUserId(user.getId());
		PersonInfo info = personInfoService.saveOrUpdate(personInfo);
		return ResponseEntity.ok(info);
	}

}