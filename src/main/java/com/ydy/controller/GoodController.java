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
import com.ydy.annotation.CtrlParam;
import com.ydy.model.Sku;
import com.ydy.model.Spu;
import com.ydy.service.good.GoodService;
import com.ydy.utils.StringUtils;
import com.ydy.vo.SpuVo;
import com.ydy.vo.other.BaseVo;
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

	@GetMapping("listSpu")
	@ResponseBody
	public ResponseEntity<PageVo<Spu>> listSpu(Spu spu, Integer page, Integer size) {
		StringUtils.setParamEmptyToNull(spu);
		PageVo<Spu> vo = goodService.list(spu, page, size);
		return ResponseEntity.ok(vo);
	}

	@GetMapping("listWithReduction")
	@ResponseBody
	public ResponseEntity<PageVo<Spu>> listWithReduction(Spu spu, Integer page, Integer size) {
		StringUtils.setParamEmptyToNull(spu);
		PageVo<Spu> vo = goodService.listWithReduction(spu, page, size);
		return ResponseEntity.ok(vo);
	}

	@GetMapping("spuDetail")
	@ResponseBody
	public ResponseEntity<SpuVo> spuDetail(@CtrlParam("商品ID") Long spuId) {
		return ResponseEntity.ok(goodService.selectSpuVoById(spuId));
	}

	@AdminToken
	@GetMapping("selectSpu")
	@ResponseBody
	public ResponseEntity<PageVo<Spu>> selectSpu(Spu spu, Integer page, Integer size) {
		StringUtils.setParamEmptyToNull(spu);
		PageVo<Spu> vo = goodService.select(spu, page, size);
		return ResponseEntity.ok(vo);
	}

	@AdminToken
	@GetMapping("selectSku")
	@ResponseBody
	public ResponseEntity<PageVo<Sku>> selectSku(Sku sku, Integer page, Integer size) {
		StringUtils.setParamEmptyToNull(sku);
		PageVo<Sku> vo = goodService.select(sku, page, size);
		return ResponseEntity.ok(vo);
	}

	@AdminToken
	@PostMapping("saveSpu")
	@ResponseBody
	public ResponseEntity<Spu> saveSpu(Spu spu) {
		StringUtils.setParamEmptyToNull(spu);
		spu = goodService.saveOrUpdateSpu(spu);
		return ResponseEntity.ok(spu);
	}

	@AdminToken
	@PostMapping("saveSku")
	@ResponseBody
	public ResponseEntity<Sku> saveSku(Sku sku) {
		StringUtils.setParamEmptyToNull(sku);
		sku = goodService.saveOrUpdateSku(sku);
		return ResponseEntity.ok(sku);
	}

	@AdminToken
	@DeleteMapping("deleteSpu")
	@ResponseBody
	public ResponseEntity<BaseVo> deleteSpu(@CtrlParam("spuId") Long spuId) {
		return ResponseEntity.ok(goodService.deleteSpu(spuId));
	}

	@AdminToken
	@PostMapping("statusSpu")
	@ResponseBody
	public ResponseEntity<BaseVo> statusSpu(@CtrlParam("spuId") Long spuId) {
		return ResponseEntity.ok(goodService.statusSpu(spuId));
	}

	@AdminToken
	@GetMapping("selectSkuById")
	@ResponseBody
	public ResponseEntity<Sku> selectSkuById(@CtrlParam("skuId") Long skuId) {
		return ResponseEntity.ok(goodService.selectSkuById(skuId));
	}
}