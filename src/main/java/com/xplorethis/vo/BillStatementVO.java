package com.xplorethis.vo;

import java.util.Date;

public class BillStatementVO {

	private int billId;
	private int purchasedId;
	private String paid;
	private double amount;
	private Date paidDate;
	private String comments;

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public int getPurchasedId() {
		return purchasedId;
	}

	public void setPurchasedId(int purchasedId) {
		this.purchasedId = purchasedId;
	}

	public String getPaid() {
		return paid;
	}

	public void setPaid(String paid) {
		this.paid = paid;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}