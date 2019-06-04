/**
 * 
 */
package com.ydy.remote.coin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ydy.exception.RemoteApiException;
import com.ydy.remote.coin.vo.BitCoinVo;
import com.ydy.remote.coin.vo.LiteCoinVo;
import com.ydy.utils.HttpUtil;

/**
 * @author xuzhaojie
 *
 *         2019年5月28日 下午3:11:31
 */
public class CoinApi {

	private final static Logger log = LoggerFactory.getLogger(CoinApi.class);

	/**
	 * 比特币实时价格
	 * 
	 * @return
	 */
	public static BitCoinVo requestBitCoinInfo() {
		String url = "https://min-api.cryptocompare.com/data/top/exchanges/full?fsym=BTC&tsym=USD&limit=1";
		String result = HttpUtil.doGet(url);
		if (result == null) {
			log.error("请求出现问题:" + url);
			throw new RemoteApiException("请求出现问题:" + url);
		}
		JSONObject json = JSONObject.parseObject(result);
		if (!json.containsKey("Response")) {
			if (!"Success".equalsIgnoreCase(json.getString("Response"))) {
				String msg = json.getString("Response") + ":" + json.getString("Message");
				log.error("请求出现问题:" + msg);
				throw new RemoteApiException(msg);
			}
		}
		BitCoinVo vo = new BitCoinVo();
		JSONObject data = json.getJSONObject("Data");
		JSONObject aggregatedData = data.getJSONObject("AggregatedData");
		JSONObject coinInfo = data.getJSONObject("CoinInfo");
		vo.setFromSymbol(aggregatedData.getString("FROMSYMBOL"));
		vo.setToSymbol(aggregatedData.getString("TOSYMBOL"));
		vo.setFullName(coinInfo.getString("FullName"));
		vo.setName(coinInfo.getString("Name"));
		vo.setPrice(aggregatedData.getBigDecimal("PRICE"));
		vo.setImgUrl("https://www.cryptocompare.com" + coinInfo.getString("ImageUrl"));
		log.info("实时数据:" + JSON.toJSONString(vo));
		return vo;
	}

	/**
	 * 莱特币实时价格
	 * 
	 * @return
	 */
	public static LiteCoinVo requestLiteCoinInfo() {
		String url = "https://min-api.cryptocompare.com/data/top/exchanges/full?fsym=LTC&tsym=USD&limit=1";
		String result = HttpUtil.doGet(url);
		if (result == null) {
			log.error("请求出现问题:" + url);
			throw new RemoteApiException("请求出现问题:" + url);
		}
		JSONObject json = JSONObject.parseObject(result);
		if (!json.containsKey("Response")) {
			if (!"Success".equalsIgnoreCase(json.getString("Response"))) {
				String msg = json.getString("Response") + ":" + json.getString("Message");
				log.error("请求出现问题:" + msg);
				throw new RemoteApiException(msg);
			}
		}
		LiteCoinVo vo = new LiteCoinVo();
		JSONObject data = json.getJSONObject("Data");
		JSONObject aggregatedData = data.getJSONObject("AggregatedData");
		JSONObject coinInfo = data.getJSONObject("CoinInfo");
		vo.setFromSymbol(aggregatedData.getString("FROMSYMBOL"));
		vo.setToSymbol(aggregatedData.getString("TOSYMBOL"));
		vo.setFullName(coinInfo.getString("FullName"));
		vo.setName(coinInfo.getString("Name"));
		vo.setPrice(aggregatedData.getBigDecimal("PRICE"));
		vo.setImgUrl("https://www.cryptocompare.com" + coinInfo.getString("ImageUrl"));
		log.info("实时数据:" + JSON.toJSONString(vo));
		return vo;
	}

	public static void main(String[] args) {
		System.out.println(JSONObject.toJSON(requestBitCoinInfo()));
		System.out.println(JSONObject.toJSON(requestLiteCoinInfo()));
	}
}
