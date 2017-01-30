package com.xplorethis.entity;

import java.util.Date;

public class PurchasedDrugsEntity extends BaseEntity {

	private int purchasedId;
	private int drugId;
	private int purchasedBy;
	private Date purchasedDate;
	private String status;
	private int purchasedQty;
	private String comments;
	private UserEntity user;
	private int amount;

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

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

	public void setPurchasedBy(int userId) {
		this.purchasedBy = userId;
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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}