package sqlHandler;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class SqlQuery {

	public static int updateTagRead(TagInfo information) {
		String sql = "UPDATE tagread SET time = ? WHERE tag_read_id = ?";

		PreparedStatement statement;
		try {
			statement = SqlConnector.getConnection().prepareStatement(sql);
			statement.setObject(1, information.getTime());
			statement.setString(2, information.getTagId());
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				System.out.println("Success");
				return 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed");
			return 0;
		}

		return 0;
	}

	public static TagInfo isProductPurchased(String tagreadId) {
		String sql = "SELECT t.tag_read_id, pi.product_instance_id, pl.name, pi.is_purchased "
				+ "FROM tagread t , productinstancerfid pi, productline pl " + 
				"WHERE t.tag_read_id = '" + tagreadId
				+ "' AND t.product_instance_id = pi.product_instance_id AND pi.product_line_id = pl.product_line_id";
		
		Statement statement;
		
		try {
			statement = SqlConnector.getConnection().createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			if (result != null && !result.next())
				return null;

			String tagId = result.getString(1);
			String productInstanceId = result.getString(2);
			String productName = result.getString(3);
			boolean isPurchased = result.getBoolean(4);
			LocalDateTime time = Instant.ofEpochSecond(System.currentTimeMillis()/1000).atZone(ZoneId.of("Asia/Ho_Chi_Minh")).toLocalDateTime();
			String antenna = "";
			
			TagInfo infor = new TagInfo(tagId, productInstanceId, productName, isPurchased, time, antenna);
			System.out.println("Success");
			return infor;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed");
		}
		
		return null;
	}
}
