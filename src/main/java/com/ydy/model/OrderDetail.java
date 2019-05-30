/**
 * 
 */
package com.ydy.model;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author xuzhaojie
 *
 *         2019年5月27日 上午11:37:39
 */
@Table(name = "order_detail")
public class OrderDetail {
	@Id
	private Long id;

	/**
	 * 订单id
	 */
	private Long orderId;

	/**
	 * 商品id
	 */
	private Long skuId;

	/**
	 * 商品购买数量
	 */
	private Integer num;

	/**
	 * 商品标题
	 */
	private String title;

	/**
	 * 商品单价
	 */
	private Long price;
	private Long totalPrice;

	/**
	 * 图片
	 */
	private String image;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the totalPrice
	 */
	public Long getTotalPrice() {
		return totalPrice;
	}

	/**
	 * @param totalPrice
	 *            the totalPrice to set
	 */
	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the orderId
	 */
	public Long getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId
	 *            the orderId to set
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

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
	 * @return the num
	 */
	public Integer getNum() {
		return num;
	}

	/**
	 * @param num
	 *            the num to set
	 */
	public void setNum(Integer num) {
		this.num = num;
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

}
