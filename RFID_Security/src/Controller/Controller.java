package Controller;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import Model.SqlQuery;
import Model.Entities.Recent;
import Model.Entities.FilterQuery;
import Model.Entities.HistoryResult;
import Model.Entities.ProductLine;
import Model.Entities.ReportResult;
import Model.Entities.Tag;
import Model.Entities.TagResult;

public class Controller {
	
	public static boolean updateTag(Tag tag) {
		return SqlQuery.updateTag(tag);
	}
	
	public static Recent isProductPurchased(String tagId) {
		return SqlQuery.isProductPurchased(tagId, getCurrentTime());
	}
	
	public static ArrayList<ProductLine> getProductLines() {
		return SqlQuery.getProductLine();
	}
	
	public static ArrayList<TagResult> getTags() {
		return SqlQuery.getTag();
	}
	
	public static ArrayList<HistoryResult> getHistories(FilterQuery query) {
		return SqlQuery.getHistory(query);
	}
	
	public static ArrayList<ReportResult> getReports(FilterQuery query) {
		return SqlQuery.getReport(query);
	}
	
	static LocalDateTime getCurrentTime() {
		return Instant.ofEpochSecond(System.currentTimeMillis() / 1000).atZone(ZoneId.of("Asia/Ho_Chi_Minh"))
				.toLocalDateTime();
	}
	
	
}
