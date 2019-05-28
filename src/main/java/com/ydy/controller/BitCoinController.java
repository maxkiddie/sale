/**
 * 
 */
package com.ydy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ydy.remote.BitCoinApi;
import com.ydy.remote.vo.BitCoinVo;

/**
 * @author xuzhaojie
 *
 *         2019年1月18日 上午9:17:29
 */
@Controller
@RequestMapping(value = "bitCoin")
public class BitCoinController {

	/**
	 * 筛选商品信息
	 * 
	 * @return
	 */
	@GetMapping("USD")
	@ResponseBody
	public ResponseEntity<BitCoinVo> usd() {
		return ResponseEntity.ok(BitCoinApi.requestBitCoinInfo());
	}
}