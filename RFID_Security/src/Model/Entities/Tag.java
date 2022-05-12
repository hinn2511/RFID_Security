package Model.Entities;

public class Tag {
	private String tagID;
	private String productLineID;
	private boolean isPurchased;
	
	public Tag(String tagID, String productLineID, boolean isPurchased) {
		super();
		this.tagID = tagID;
		this.productLineID = productLineID;
		this.isPurchased = isPurchased;
	}

	public String getTagID() {
		return tagID;
	}

	public void setTagID(String tagID) {
		this.tagID = tagID;
	}

	public String getProductLineID() {
		return productLineID;
	}

	public void setProductLineID(String productLineID) {
		this.productLineID = productLineID;
	}

	public boolean isPurchased() {
		return isPurchased;
	}

	public void setPurchased(boolean isPurchased) {
		this.isPurchased = isPurchased;
	}
	
	
}
