/**
 * 
 */
package com.ydy.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author xuzhaojie
 *
 *         2019年5月27日 下午2:05:50
 */
@Table(name = "sku")
public class Sku {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long skuId;
	@NotNull
	private Long spuId;
	@NotNull
	private String mainImage;
	@NotNull
	private String spuSpecs;
	@NotNull
	@Min(value = 0)
	private Integer stock;
	@NotNull
	@Min(value = 0)
	private Long price;
	@NotNull
	@Min(value = 0)
	private Long nowPrice;
	private Date createTime;
	private Date updateTime;

	/**
	 * @return the skuId
	 */
	public Long getSkuId() {
		return skuId;
	}

	/**
	 * @return the nowPrice
	 */
	public Long getNowPrice() {
		return nowPrice;
	}

	/**
	 * @param nowPrice
	 *            the nowPrice to set
	 */
	public void setNowPrice(Long nowPrice) {
		this.nowPrice = nowPrice;
	}

	/**
	 * @param skuId
	 *            the skuId to set
	 */
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

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
	 * @return the mainImage
	 */
	public String getMainImage() {
		return mainImage;
	}

	/**
	 * @param mainImage
	 *            the mainImage to set
	 */
	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}

	/**
	 * @return the spuSpecs
	 */
	public String getSpuSpecs() {
		return spuSpecs;
	}

	/**
	 * @param spuSpecs
	 *            the spuSpecs to set
	 */
	public void setSpuSpecs(String spuSpecs) {
		this.spuSpecs = spuSpecs;
	}

	/**
	 * @return the stock
	 */
	public Integer getStock() {
		return stock;
	}

	/**
	 * @param stock
	 *            the stock to set
	 */
	public void setStock(Integer stock) {
		this.stock = stock;
	}

	/**
	 * @return the price
	 */
	public Long getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(Long price) {
		this.price = price;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime
	 *            the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
