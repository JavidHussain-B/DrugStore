package com.xplorethis.vo;

import java.util.Date;

public class PurchasedDrugsVO {

	private int purchasedId;
	private int drugId;
	private int purchasedBy;
	private Date purchasedDate;
	private String status;
	private int purchasedQty;
	private String comments;
	private String userName;
	private int amount;
	
	public int getPurchasedId() {
		return purchasedId;
	}

	public void setPurchasedId(int purchasedId) {
		this.purchasedId = purchasedId;
	}

	public int getDrugId() {
		return drugId;
	}

	public void setDrugId(int drugId) {
		this.drugId = drugId;
	}

	public int getPurchasedBy() {
		return purchasedBy;
	}

	public void setPurchasedBy(int purchasedBy) {
		this.purchasedBy = purchasedBy;
	}

	public Date getPurchasedDate() {
		return purchasedDate;
	}

	public void setPurchasedDate(Date purchasedDate) {
		this.purchasedDate = purchasedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getPurchasedQty() {
		return purchasedQty;
	}

	public void setPurchasedQty(int purchasedQty) {
		this.purchasedQty = purchasedQty;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}