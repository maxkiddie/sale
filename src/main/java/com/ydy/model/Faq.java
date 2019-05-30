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

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author xuzhaojie
 *
 *         2019年5月27日 下午2:05:50
 */
@Table(name = "faq")
public class Faq {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String ask;
	@NotBlank
	private String answer;
	private String type;
	@NotNull
	private Integer inx;
	private Date createTime;

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
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the ask
	 */
	public String getAsk() {
		return ask;
	}

	/**
	 * @param ask
	 *            the ask to set
	 */
	public void setAsk(String ask) {
		this.ask = ask;
	}

	/**
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * @param answer
	 *            the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/**
	 * @return the inx
	 */
	public Integer getInx() {
		return inx;
	}

	/**
	 * @param inx
	 *            the inx to set
	 */
	public void setInx(Integer inx) {
		this.inx = inx;
	}

}
