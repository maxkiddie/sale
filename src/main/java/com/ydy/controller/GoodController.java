/**
 * 
 */
package com.ydy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ydy.model.Sku;
import com.ydy.model.Spu;
import com.ydy.service.good.GoodService;
import com.ydy.utils.StringUtils;
import com.ydy.vo.other.PageVo;

/**
 * @author xuzhaojie
 *
 *         2019年1月18日 上午9:17:29
 */
@Controller
@RequestMapping(value = "good")
public class GoodController {

	@Autowired
	private GoodService goodService;

	/**
	 * 筛选商品信息
	 * 
	 * @return
	 */
	@GetMapping("select")
	@ResponseBody
	public ResponseEntity<PageVo<Spu>> select(Spu spu, Integer page, Integer size) {
		StringUtils.setParamEmptyToNull(spu);
		PageVo<Spu> vo = goodService.selectData(spu, page, size);
		return ResponseEntity.ok(vo);
	}

	@PostMapping("saveSpu")
	@ResponseBody
	public ResponseEntity<Spu> saveSpu(Spu spu) {
		StringUtils.setParamEmptyToNull(spu);
		spu = goodService.saveOrUpdateSpu(spu);
		return ResponseEntity.ok(spu);
	}

	@PostMapping("saveSku")
	@ResponseBody
	public ResponseEntity<Sku> saveSku(Sku sku) {
		StringUtils.setParamEmptyToNull(sku);
		sku = goodService.saveOrUpdateSku(sku);
		return ResponseEntity.ok(sku);
	}
}