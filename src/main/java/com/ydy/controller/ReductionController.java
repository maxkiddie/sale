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
import com.ydy.model.Reduction;
import com.ydy.service.reduction.ReductionService;
import com.ydy.utils.StringUtils;
import com.ydy.vo.other.BaseVo;
import com.ydy.vo.other.PageVo;

/**
 * @author xuzhaojie
 *
 *         2019年1月18日 上午9:17:29
 */
@Controller
@RequestMapping(value = "reduction")
public class ReductionController {

	@Autowired
	private ReductionService reductionService;

	@AdminToken
	@GetMapping("select")
	@ResponseBody
	public ResponseEntity<PageVo<Reduction>> select(Reduction reduction, Integer page, Integer size) {
		StringUtils.setParamEmptyToNull(reduction);
		PageVo<Reduction> vo = reductionService.select(reduction, page, size);
		return ResponseEntity.ok(vo);
	}

	@AdminToken
	@CheckFormRepeat
	@PostMapping("save")
	@ResponseBody
	public ResponseEntity<Reduction> save(Reduction reduction) {
		return ResponseEntity.ok(reductionService.saveOrUpdate(reduction));
	}

	@AdminToken
	@CheckFormRepeat
	@DeleteMapping("delete")
	@ResponseBody
	public ResponseEntity<BaseVo> delete(@CtrlParam("id") Long id) {
		return ResponseEntity.ok(reductionService.delete(id));
	}

	@AdminToken
	@GetMapping("selectById")
	@ResponseBody
	public ResponseEntity<Reduction> selectById(@CtrlParam("id") Long id) {
		return ResponseEntity.ok(reductionService.selectById(id));
	}
}