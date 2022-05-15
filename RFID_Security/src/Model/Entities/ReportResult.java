package Model.Entities;

public class ReportResult {
	String productLineID;
	String productLineName;
	int quantity;
	double price;
	
	public ReportResult(String productLineID, String productLineName, int quantity, double price) {
		super();
		this.productLineID = productLineID;
		this.productLineName = productLineName;
		this.quantity = quantity;
		this.price = price;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
}
