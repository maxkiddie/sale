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
import com.ydy.model.Faq;
import com.ydy.service.faq.FaqService;
import com.ydy.utils.StringUtils;
import com.ydy.vo.other.BaseVo;
import com.ydy.vo.other.PageVo;

/**
 * @author xuzhaojie
 *
 *         2019年1月18日 上午9:17:29
 */
@Controller
@RequestMapping(value = "faq")
public class FaqController {

	@Autowired
	private FaqService faqService;

	@GetMapping("list")
	@ResponseBody
	public ResponseEntity<PageVo<Faq>> list(Faq faq, Integer page, Integer size) {
		StringUtils.setParamEmptyToNull(faq);
		PageVo<Faq> vo = faqService.list(faq, page, size);
		return ResponseEntity.ok(vo);
	}

	@AdminToken
	@GetMapping("select")
	@ResponseBody
	public ResponseEntity<PageVo<Faq>> select(Faq faq, Integer page, Integer size) {
		StringUtils.setParamEmptyToNull(faq);
		PageVo<Faq> vo = faqService.select(faq, page, size);
		return ResponseEntity.ok(vo);
	}

	@AdminToken
	@CheckFormRepeat
	@PostMapping("save")
	@ResponseBody
	public ResponseEntity<Faq> save(Faq faq) {
		return ResponseEntity.ok(faqService.saveOrUpdate(faq));
	}

	@AdminToken
	@CheckFormRepeat
	@DeleteMapping("delete")
	@ResponseBody
	public ResponseEntity<BaseVo> delete(Long id) {
		return ResponseEntity.ok(faqService.delete(id));
	}
}