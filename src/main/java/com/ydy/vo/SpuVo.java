/**
 * 
 */
package com.ydy.vo;

import java.util.List;

import com.ydy.model.Sku;
import com.ydy.model.Spu;

/**
 * @author xuzhaojie
 *
 *         2019年5月29日 上午9:56:55
 */
public class SpuVo extends Spu {

	private List<Sku> skus;

	/**
	 * @return the skus
	 */
	public List<Sku> getSkus() {
		return skus;
	}

	/**
	 * @param skus
	 *            the skus to set
	 */
	public void setSkus(List<Sku> skus) {
		this.skus = skus;
	}

}
