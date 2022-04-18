package sqlHandler;

import java.time.LocalDateTime;

public class TagInfo {
	String tagId;
	String productInstanceId;
	String productName;
	boolean isPurchased;
	LocalDateTime time;
	String antenna;
	
	
	
	public String getTagId() {
		return tagId;
	}



	public String getProductInstanceId() {
		return productInstanceId;
	}



	public String getProductName() {
		return productName;
	}



	public boolean isPurchased() {
		return isPurchased;
	}



	public LocalDateTime getTime() {
		return time;
	}



	public String getAntenna() {
		return antenna;
	}

	

	public TagInfo() {
		super();
	}

	public TagInfo(String tagId, String productInstanceId, String productName, boolean isPurchased,
			LocalDateTime time, String antenna) {
		super();
		this.tagId = tagId;
		this.productInstanceId = productInstanceId;
		this.productName = productName;
		this.isPurchased = isPurchased;
		this.time = time;
		this.antenna = antenna;
	}
	
	
}
