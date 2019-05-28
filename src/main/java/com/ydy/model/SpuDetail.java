/**
 * 
 */
package com.ydy.model;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author xuzhaojie
 *
 *         2019年5月27日 下午2:05:50
 */
@Table(name = "spu_detail")
public class SpuDetail {

	@Id
	private Long spuId;
	private String detail;

	/**
	 * @return the spuId
	 */
	public Long getSpuId() {
		return spuId;
	}

	/**
	 * @param spuId
	 *            the spuId to set
	 */
	public void setSpuId(Long spuId) {
		this.spuId = spuId;
	}

	/**
	 * @return the detail
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * @param detail
	 *            the detail to set
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

}
