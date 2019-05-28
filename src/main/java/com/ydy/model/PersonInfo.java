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
 *         19年1月17日 下午:49:47
 */
@Table(name = "person_info")
public class PersonInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private Long userId;
	@NotBlank
	private String firstName;
	private String middleName;
	@NotBlank
	private String lastName;
	@NotNull
	private Date birthDate;
	@NotBlank
	private String state;
	@NotNull
	private Double heightFeet;
	@NotNull
	private Double heightInches;
	@NotNull
	private Double weight;
	@NotBlank
	private String eyes;
	@NotBlank
	private String hair;
	private Integer gender;
	@NotBlank
	private String address1;
	private String address2;
	@NotBlank
	private String city;
	@NotBlank
	private String zip;
	@NotBlank
	private String picture;
	@NotBlank
	private String signature;
	private String additional;

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
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName
	 *            the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the birthDate
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate
	 *            the birthDate to set
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the heightFeet
	 */
	public Double getHeightFeet() {
		return heightFeet;
	}

	/**
	 * @param heightFeet
	 *            the heightFeet to set
	 */
	public void setHeightFeet(Double heightFeet) {
		this.heightFeet = heightFeet;
	}

	/**
	 * @return the heightInches
	 */
	public Double getHeightInches() {
		return heightInches;
	}

	/**
	 * @param heightInches
	 *            the heightInches to set
	 */
	public void setHeightInches(Double heightInches) {
		this.heightInches = heightInches;
	}

	/**
	 * @return the weight
	 */
	public Double getWeight() {
		return weight;
	}

	/**
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(Double weight) {
		this.weight = weight;
	}

	/**
	 * @return the eyes
	 */
	public String getEyes() {
		return eyes;
	}

	/**
	 * @param eyes
	 *            the eyes to set
	 */
	public void setEyes(String eyes) {
		this.eyes = eyes;
	}

	/**
	 * @return the hair
	 */
	public String getHair() {
		return hair;
	}

	/**
	 * @param hair
	 *            the hair to set
	 */
	public void setHair(String hair) {
		this.hair = hair;
	}

	/**
	 * @return the gender
	 */
	public Integer getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(Integer gender) {
		this.gender = gender;
	}

	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * @param address1
	 *            the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @param address2
	 *            the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip
	 *            the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * @return the picture
	 */
	public String getPicture() {
		return picture;
	}

	/**
	 * @param picture
	 *            the picture to set
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}

	/**
	 * @return the signature
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * @param signature
	 *            the signature to set
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}

	/**
	 * @return the additional
	 */
	public String getAdditional() {
		return additional;
	}

	/**
	 * @param additional
	 *            the additional to set
	 */
	public void setAdditional(String additional) {
		this.additional = additional;
	}

}
