/**
 * 
 */
package com.ydy.model;

import java.util.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

/**
 * @author xuzhaojie
 *
 *         2019年5月27日 下午2:05:50
 */
@Table(name = "spu")
public class Spu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long spuId;
	private Integer categoryId;// 未知分类
	@NotBlank
	private String name;
	@NotBlank
	private String title;
	@NotBlank
	private String mainImage;
	private Long minPrice;
	private Long nowMinPrice;
	private Long maxPrice;
	private Long nowMaxPrice;
	@NotBlank
	@Transient
	private String detail;
	@Transient
	@NotBlank
	private String images;
	private Integer spuStatus;
	private Date createTime;
	private Date updateTime;
	@Transient
	private List<Reduction> reductions;

	/**
	 * @return the reductions
	 */
	public List<Reduction> getReductions() {
		return reductions;
	}

	/**
	 * @param reductions
	 *            the reductions to set
	 */
	public void setReductions(List<Reduction> reductions) {
		this.reductions = reductions;
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
	 * @return the categoryId
	 */
	public Integer getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId
	 *            the categoryId to set
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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
	 * @return the minPrice
	 */
	public Long getMinPrice() {
		return minPrice;
	}

	/**
	 * @param minPrice
	 *            the minPrice to set
	 */
	public void setMinPrice(Long minPrice) {
		this.minPrice = minPrice;
	}

	/**
	 * @return the nowMinPrice
	 */
	public Long getNowMinPrice() {
		return nowMinPrice;
	}

	/**
	 * @param nowMinPrice
	 *            the nowMinPrice to set
	 */
	public void setNowMinPrice(Long nowMinPrice) {
		this.nowMinPrice = nowMinPrice;
	}

	/**
	 * @return the maxPrice
	 */
	public Long getMaxPrice() {
		return maxPrice;
	}

	/**
	 * @param maxPrice
	 *            the maxPrice to set
	 */
	public void setMaxPrice(Long maxPrice) {
		this.maxPrice = maxPrice;
	}

	/**
	 * @return the nowMaxPrice
	 */
	public Long getNowMaxPrice() {
		return nowMaxPrice;
	}

	/**
	 * @param nowMaxPrice
	 *            the nowMaxPrice to set
	 */
	public void setNowMaxPrice(Long nowMaxPrice) {
		this.nowMaxPrice = nowMaxPrice;
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

	/**
	 * @return the images
	 */
	public String getImages() {
		return images;
	}

	/**
	 * @param images
	 *            the images to set
	 */
	public void setImages(String images) {
		this.images = images;
	}

	/**
	 * @return the spuStatus
	 */
	public Integer getSpuStatus() {
		return spuStatus;
	}

	/**
	 * @param spuStatus
	 *            the spuStatus to set
	 */
	public void setSpuStatus(Integer spuStatus) {
		this.spuStatus = spuStatus;
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
