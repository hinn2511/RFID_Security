package Model.Entities;

public class HistoryResult {
	String tagId;
	String productLineID;
	String productLineName;
	int gateNumber;
	String timePassed;
	String status;
	
	public HistoryResult(String tagId, String productLineID, String productLineName, String timePassed, int gateNumber,
			String status) {
		super();
		this.tagId = tagId;
		this.productLineID = productLineID;
		this.productLineName = productLineName;
		this.timePassed = timePassed;
		this.gateNumber = gateNumber;
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

	public int getGateNumber() {
		return gateNumber;
	}

	public void setGateNumber(int gateNumber) {
		this.gateNumber = gateNumber;
	}

	public String getTimePassed() {
		return timePassed;
	}

	public void setTimePassed(String timePassed) {
		this.timePassed = timePassed;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
