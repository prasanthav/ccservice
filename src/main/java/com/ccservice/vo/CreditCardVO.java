package com.ccservice.vo;

import java.io.Serializable;
import java.util.Date;

import com.ccservice.entity.CreditCard;

/**
 * The credit card value object
 * @author avpra
 *
 */
public class CreditCardVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ccNumber;
	private String name;
	private double creditLimit;
	private double creditBalance;
	private Date createdTime;

	public CreditCardVO() {
		super();
	}

	public CreditCardVO(String ccNumber, String name, double creditLimit, double creditBalance, Date createdTime) {
		super();
		this.ccNumber = ccNumber;
		this.name = name;
		this.creditLimit = creditLimit;
		this.creditBalance = creditBalance;
		this.createdTime = createdTime;
	}

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

	public CreditCard toCreditCard() {
		CreditCard creditCard = new CreditCard();
		creditCard.setCcNumber(this.getCcNumber());
		creditCard.setCreditBalance(this.getCreditBalance());
		creditCard.setCreditLimit(this.getCreditLimit());
		creditCard.setName(this.getName());
		creditCard.setCreatedTime(this.getCreatedTime());
		return creditCard;
	}
}
