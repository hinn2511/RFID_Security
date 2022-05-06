package Model.Entities;

import java.time.LocalDateTime;

public class CheckoutInfo {
	private String tagId;
	private String productId;
	private String productName;
	private boolean isPurchased;
	private int gateNumber;
	private LocalDateTime time;
	
	public CheckoutInfo(String tagId, String productId, String productName, boolean isPurchased, int gateNumber,
			LocalDateTime time) {
		super();
		this.tagId = tagId;
		this.productId = productId;
		this.productName = productName;
		this.isPurchased = isPurchased;
		this.gateNumber = gateNumber;
		this.time = time;
	}

	public CheckoutInfo() {
		super();
	}

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public boolean isPurchased() {
		return isPurchased;
	}

	public void setPurchased(boolean isPurchased) {
		this.isPurchased = isPurchased;
	}

	public int getGateNumber() {
		return gateNumber;
	}

	public void setGateNumber(int gateNumber) {
		this.gateNumber = gateNumber;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	
	
}
