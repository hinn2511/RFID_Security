package sqlHandler.model;

public class ReportResult {
	String productLineID;
	String productLineName;
	int quantity;
	
	public ReportResult(String productLineID, String productLineName, int quantity) {
		super();
		this.productLineID = productLineID;
		this.productLineName = productLineName;
		this.quantity = quantity;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
