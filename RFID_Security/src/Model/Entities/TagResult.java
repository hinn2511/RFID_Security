package Model.Entities;

public class TagResult {
	String tagId;
	String productLineID;
	String productLineName;
	String status;
	
	public TagResult(String tagId, String productLineID, String productLineName, String status) {
		super();
		this.tagId = tagId;
		this.productLineID = productLineID;
		this.productLineName = productLineName;
		this.status = status;
	}

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	public String getProductLineID() {
		return productLineID;
	}

	public void setProductLineID(String productLineID) {
		this.productLineID = productLineID;
	}

	public String getProductLineName() {
		return productLineName;
	}

	public void setProductLineName(String productLineName) {
		this.productLineName = productLineName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
