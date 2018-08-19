package com.ccservice.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.ccservice.vo.CreditCardVO;

/**
 * The entity object of credit card
 * @author avpra
 *
 */
@Entity
public class CreditCard implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String ccNumber;
	private String name;
	private double creditLimit;
	private double creditBalance;
	private Date createdTime;

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getCcNumber() {
		return ccNumber;
	}

	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}

	public double getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}

	public double getCreditBalance() {
		return creditBalance;
	}

	public void setCreditBalance(double creditBalance) {
		this.creditBalance = creditBalance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "[Name- " + this.getName() + ", card number- " + this.getCcNumber() + ", balance- "
				+ this.getCreditBalance() + ", limit- " + this.getCreditLimit() + ", createdDate- "
				+ this.getCreatedTime() + "]";
	}

	public CreditCardVO toCreditCardVO() {
		CreditCardVO creditCardVO = new CreditCardVO();
		creditCardVO.setCcNumber(this.getCcNumber());
		creditCardVO.setCreditBalance(this.getCreditBalance());
		creditCardVO.setCreditLimit(this.getCreditLimit());
		creditCardVO.setName(this.getName());
		creditCardVO.setCreatedTime(this.getCreatedTime());
		return creditCardVO;
	}

}
