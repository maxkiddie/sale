/**
 * 
 */
package com.ydy.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
	private String image;
	@NotNull
	private String spuSpecs;
	@NotNull
	private Integer stock;
	@NotNull
	private Long price;
	@NotNull
	private Integer skuStatus = 0;
	private Date createTime;
	private Date updateTime;

	/**
	 * @return the skuId
	 */
	public Long getSkuId() {
		return skuId;
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
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(String image) {
		this.image = image;
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
	 * @return the skuStatus
	 */
	public Integer getSkuStatus() {
		return skuStatus;
	}

	/**
	 * @param skuStatus
	 *            the skuStatus to set
	 */
	public void setSkuStatus(Integer skuStatus) {
		this.skuStatus = skuStatus;
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
