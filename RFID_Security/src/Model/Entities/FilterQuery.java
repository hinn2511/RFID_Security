package Model.Entities;


public class FilterQuery {
	String fromDate;
	String toDate;
	String status;
	String filter;
	String filterValue;
	String orderBy;
	
	public FilterQuery(String fromDate, String toDate, String status, String filter, String filterValue, String orderBy) {
		super();
		this.fromDate = fromDate + " 00:00:00";
		this.toDate = toDate + " 00:00:00";
		this.status = status;
		
		switch (filter) {
		case "Product line ID":
			this.filter = "product_line_id";
			break;
		case "Product line name":
			this.filter = "name";
			break;
		case "Tag ID":
			this.filter = "tag_id";
			break;
		default:
			this.filter = "none";
			break;
		}
		
		this.filterValue = filterValue;
		
		switch (orderBy) {
		case "Tag ID (A-Z)":
			this.orderBy = "tag_id ASC";
			break;
		case "Tag ID (Z-A)":
			this.orderBy = "tag_id DESC";
			break;
		case "Product line ID (A-Z)":
			this.orderBy = "product_line_id ASC";
			break;
		case "Product line ID (Z-A)":
			this.orderBy = "product_line_id DESC";
			break;
		case "Product line name (A-Z)":
			this.orderBy = "name ASC";
			break;
		case "Product line name (Z-A)":
			this.orderBy = "name DESC";
			break;
		case "Time passed (Newest)":
			this.orderBy = "time DESC";
			break;
		case "Time passed (Oldest)":
			this.orderBy = "time ASC";
			break;
		case "Gate number (Asc)":
			this.orderBy = "gate_number ASC";
			break;
		case "Gate number (Desc)":
			this.orderBy = "gate_number DESC";
			break;
		case "Quantity (Asc)":
			this.orderBy = "quantity ASC";
			break;
		case "Quantity (Desc)":
			this.orderBy = "quantity DESC";
			break;
		default:
			this.orderBy = "none";
			break;
		}
		
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getFilterValue() {
		return filterValue;
	}

	public void setFilterValue(String filterValue) {
		this.filterValue = filterValue;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
