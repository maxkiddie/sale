/**
 * 
 */
package com.ydy.remote;

import com.alibaba.fastjson.JSONObject;
import com.ydy.exception.MyException;
import com.ydy.remote.vo.BitCoinVo;
import com.ydy.utils.HttpUtil;
import com.ydy.vo.ienum.EnumSystem;

/**
 * @author xuzhaojie
 *
 *         2019年5月28日 下午3:11:31
 */
public class BitCoinApi {

	/**
	 * 比特币兑换美元
	 * 
	 * @return
	 */
	public static BitCoinVo requestBitCoinInfo() {
		String result = HttpUtil.doGet("https://api.coindesk.com/v1/bpi/currentprice.json");
		if (result == null) {
			throw new MyException(EnumSystem.REMOTE_IP_ERROR);
		}
		JSONObject json = JSONObject.parseObject(result);
		BitCoinVo vo = new BitCoinVo();
		vo.setChartName(json.getString("chartName"));
		JSONObject usd = json.getJSONObject("bpi").getJSONObject("USD");
		vo.setCode(usd.getString("code"));
		vo.setDescription(usd.getString("description"));
		vo.setRate(usd.getDouble("rate"));
		vo.setRateFloat(usd.getDouble("rate_float"));
		vo.setUpdated(json.getJSONObject("time").getString("updated"));
		return vo;
	}

}
