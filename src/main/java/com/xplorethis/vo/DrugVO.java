package com.xplorethis.vo;

public class DrugVO extends BaseVO {

	private int drugId;
	private String description;
	private int availableQty;
	private int qtyPerUnit;
	private double unitRate;

	public int getDrugId() {
		return drugId;
	}

	public void setDrugId(int drugId) {
		this.drugId = drugId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAvailableQty() {
		return availableQty;
	}

	public void setAvailableQty(int availableQty) {
		this.availableQty = availableQty;
	}

	public int getQtyPerUnit() {
		return qtyPerUnit;
	}

	public void setQtyPerUnit(int qtyPerUnit) {
		this.qtyPerUnit = qtyPerUnit;
	}

	public double getUnitRate() {
		return unitRate;
	}

	public void setUnitRate(double unitRate) {
		this.unitRate = unitRate;
	}

}
