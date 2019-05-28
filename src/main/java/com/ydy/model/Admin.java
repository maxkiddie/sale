/**
 * 
 */
package com.ydy.model;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author xuzhaojie
 *
 *         2019年1月17日 下午2:49:47
 */
@Table(name = "admin")
public class Admin {

	@Id
	private Integer id;
	private String username;
	private String password;
	private String realname;
	private String email;
	private String phone;
	private Short useStatus;
	private Date regTime;

	/**
	 * 
	 */
	public Admin() {
		super();
	}

	/**
	 * @param username
	 * @param password
	 */
	public Admin(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the realname
	 */
	public String getRealname() {
		return realname;
	}

	/**
	 * @param realname
	 *            the realname to set
	 */
	public void setRealname(String realname) {
		this.realname = realname;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the useStatus
	 */
	public Short getUseStatus() {
		return useStatus;
	}

	/**
	 * @param useStatus
	 *            the useStatus to set
	 */
	public void setUseStatus(Short useStatus) {
		this.useStatus = useStatus;
	}

	/**
	 * @return the regTime
	 */
	public Date getRegTime() {
		return regTime;
	}

	/**
	 * @param regTime
	 *            the regTime to set
	 */
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

}
