/**
 * 
 */
package com.ydy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ydy.annotation.AdminToken;
import com.ydy.annotation.CheckFormRepeat;
import com.ydy.annotation.CtrlParam;
import com.ydy.model.WebInfo;
import com.ydy.service.webInfo.WebInfoService;
import com.ydy.utils.StringUtils;
import com.ydy.vo.other.BaseVo;
import com.ydy.vo.other.PageVo;

/**
 * @author xuzhaojie
 *
 *         2019年1月18日 上午9:17:29
 */
@Controller
@RequestMapping(value = "webInfo")
public class WebInfoController {

	@Autowired
	private WebInfoService webInfoService;

	@AdminToken
	@GetMapping("select")
	@ResponseBody
	public ResponseEntity<PageVo<WebInfo>> select(WebInfo webInfo, Integer page, Integer size) {
		StringUtils.setParamEmptyToNull(webInfo);
		PageVo<WebInfo> vo = webInfoService.select(webInfo, page, size);
		return ResponseEntity.ok(vo);
	}

	@AdminToken
	@CheckFormRepeat
	@PostMapping("save")
	@ResponseBody
	public ResponseEntity<WebInfo> save(WebInfo webInfo) {
		return ResponseEntity.ok(webInfoService.saveOrUpdate(webInfo));
	}

	@AdminToken
	@CheckFormRepeat
	@DeleteMapping("delete")
	@ResponseBody
	public ResponseEntity<BaseVo> delete(@CtrlParam("id") Long id) {
		return ResponseEntity.ok(webInfoService.delete(id));
	}

	@GetMapping("aboutUs")
	@ResponseBody
	public ResponseEntity<WebInfo> aboutUs() {
		return ResponseEntity.ok(webInfoService.selectWebInfoByType("aboutUs"));
	}

	@GetMapping("howToPay")
	@ResponseBody
	public ResponseEntity<WebInfo> howToPay() {
		return ResponseEntity.ok(webInfoService.selectWebInfoByType("howToPay"));
	}
}