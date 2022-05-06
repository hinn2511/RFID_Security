package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import Model.Entities.CheckoutInfo;
import Model.Entities.FilterQuery;
import Model.Entities.HistoryResult;
import Model.Entities.ReportResult;

public class SqlQuery {
	public static int addLog(CheckoutInfo info) {
		String sql = "INSERT INTO log (tag_id, time, gate_number, status)"
				+ " VALUES (?, ?, ?, ?);";

		PreparedStatement statement;
		try {
			statement = SqlConnector.getConnection().prepareStatement(sql);
			
			statement.setString(1, info.getTagId());
			statement.setObject(2, info.getTime());
			statement.setInt(3, info.getGateNumber());
			statement.setString(4, info.isPurchased() ? "Purchased" : "Not purchased");
			
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("Success: Add to log");
				return 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed: Add to log");
			return 0;
		}

		return 0;
	}

	public static CheckoutInfo isProductPurchased(String rfidTagId) {
		String sql = "SELECT t.tag_id, t.product_line_id, pl.name, t.is_purchased "
				+ "FROM tag t, productline pl "
				+ "WHERE t.product_line_id = pl.product_line_id AND tag_id = '" + rfidTagId + "'";
		
		Statement statement;
		
		try {
			statement = SqlConnector.getConnection().createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			if (result != null && !result.next()) {
				System.out.println("Not found: Tag id not found");
				return null;
			}

			String tagId = result.getString(1);
			String productId = result.getString(2);
			String productName = result.getString(3);
			boolean isPurchased = result.getBoolean(4);
			
			CheckoutInfo infor = new CheckoutInfo(tagId, productId, productName, isPurchased, 0, getCurrentTime());
			System.out.println("Success: Is product purchased");
			return infor;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed: Is product purchased");
		}
		
		return null;
	}
	
	public static boolean isTagExist(String rfidTagId) {
		String sql = "SELECT *"
				+ " FROM tag"
				+ " WHERE tag_id = '" + rfidTagId + "' LIMIT 1";
		
		Statement statement;
		
		try {
			statement = SqlConnector.getConnection().createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			if (result != null && !result.next()) {
				System.out.println("Not found: Tag not exist");
				return false;
			}
			
			System.out.println("Success: Tag exist");
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed: Is tag exist");
		}
		
		return false;
	}
	
	
	public static ArrayList<HistoryResult> getHistory(FilterQuery query) {
		String sql = "SELECT l.tag_id, t.product_line_id, pl.name, l.time, l.gate_number, l.status"
				+ " FROM log l, tag t, productline pl"
				+ " WHERE t.product_line_id = pl.product_line_id AND l.tag_id = t.tag_id"
				+ " AND l.time > '" + query.getFromDate() + "'"
				+ " AND l.time < '" + query.getToDate() + "'";
				
		
		if(!query.getStatus().toLowerCase().equals("all"))
			sql += " AND l.status = '" + query.getStatus() + "'";
		
		if(!query.getFilter().toLowerCase().equals("none"))
			sql += " AND " + query.getFilter() +" = '" + query.getFilterValue() + "'";
		
		if(!query.getOrderBy().toLowerCase().equals("none"))
			sql += " ORDER BY " + query.getOrderBy();
		
		System.out.println(sql);
		
		Statement statement;
		
		try {
			statement = SqlConnector.getConnection().createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			ArrayList<HistoryResult> results = new ArrayList<>();
			
			while(result.next())
			{
				if(result != null) {
					HistoryResult hr = new HistoryResult(result.getString(1), result.getString(2),
								result.getString(3), result.getString(4), result.getInt(5), result.getString(6));
					results.add(hr);
				}
			}
			System.out.println("Success: Get History");
			return results;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed: Get History");
		}
		
		return null;
	}
	
	
	
	public static ArrayList<ReportResult> getReport(FilterQuery query) {
		String sql = "SELECT  pl.product_line_id, pl.name, COUNT(pl.product_line_id) as quantity"
				+ " FROM log l"
				+ " INNER JOIN (SELECT tag_id, MAX(time) AS lastTime FROM log GROUP BY tag_id) lastTimePassed"
				+ " ON l.tag_id = lastTimePassed.tag_id AND l.time = lastTimePassed.lastTime"
				+ " INNER JOIN tag t ON l.tag_id = t.tag_id"
				+ " INNER JOIN productline pl ON pl.product_line_id = t.product_line_id"
				+ " WHERE l.time > '" + query.getFromDate() + "'"
				+ " AND l.time < '" + query.getToDate() + "'";
				
		
		if(!query.getStatus().toLowerCase().equals("all"))
			sql += " AND l.status = '" + query.getStatus() + "'";
		
		if(!query.getFilter().toLowerCase().equals("none"))
			sql += " AND " + query.getFilter() +" = '" + query.getFilterValue() + "'";
		
		sql += " GROUP BY pl.product_line_id";
		
		if(!query.getOrderBy().toLowerCase().equals("none"))
			sql += " ORDER BY " + query.getOrderBy();
		
		System.out.println(sql);
		
		Statement statement;
		
		try {
			statement = SqlConnector.getConnection().createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			ArrayList<ReportResult> results = new ArrayList<>();
			
			while(result.next())
			{
				if(result != null) {
					ReportResult rr = new ReportResult(result.getString(1), result.getString(2), result.getInt(3));
					results.add(rr);
				}
			}
			System.out.println("Succes: Get Report");
			return results;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed: Get Report");
		}
		
		return null;
	}
	
	static LocalDateTime getCurrentTime() {
		return Instant.ofEpochSecond(System.currentTimeMillis()/1000).atZone(ZoneId.of("Asia/Ho_Chi_Minh")).toLocalDateTime();
	}
	
}
