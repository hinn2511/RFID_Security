package Model.Entities;

public class ProductLine {
	private String productLineID;
	private String productLineName;
	
	public ProductLine(String productLineID, String productLineName) {
		super();
		this.productLineID = productLineID;
		this.productLineName = productLineName;
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

	public void setProductLineName(String productLineNameString) {
		this.productLineName = productLineNameString;
	}

}
