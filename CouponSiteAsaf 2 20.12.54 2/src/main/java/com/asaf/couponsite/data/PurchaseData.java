package com.asaf.couponsite.data;

public class PurchaseData {
	
	private long couponId;
	private int amount;
	private long customerId;
	
	public PurchaseData() {
	}
	
	public PurchaseData(long couponId, int amount) {
		this.couponId = couponId;
		this.amount = amount;
	}

	public PurchaseData(long couponId, int amount, long customerId) {
		this.couponId = couponId;
		this.amount = amount;
		this.customerId = customerId;
	}

	public long getCouponId() {
		return couponId;
	}
	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
}
