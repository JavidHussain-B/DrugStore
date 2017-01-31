package com.xplorethis.vo;

public class MenuVO {

	private int menuId;
	private String description;
	private int parentScreenInt;
	private int sequenceNo;

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getParentScreenInt() {
		return parentScreenInt;
	}

	public void setParentScreenInt(int parentScreenInt) {
		this.parentScreenInt = parentScreenInt;
	}

	public int getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(int sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

}