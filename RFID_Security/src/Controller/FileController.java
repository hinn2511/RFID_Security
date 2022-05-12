package Controller;

import java.awt.Color;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.spire.xls.BordersLineType;
import com.spire.xls.CellRange;
import com.spire.xls.ExcelVersion;
import com.spire.xls.HorizontalAlignType;
import com.spire.xls.LineStyleType;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;

import Model.SqlQuery;
import Model.Entities.ReportResult;
import Model.Entities.Tag;

public class FileController {
	public static String exportReportFile(String fromDate, String toDate, String status, Integer totalQuantity, ArrayList<ReportResult> reports) {
		Workbook wb = new Workbook();
		Worksheet sheet = wb.getWorksheets().get(0);
		
		sheet.setColumnWidth(1, 20);
		sheet.setColumnWidth(2, 70);
		sheet.setColumnWidth(3, 20);

		 
		String[] sheetTitle = new String[] {"Security Report"};
		sheet.getRange().get("A1:C1").merge();
		sheet.getRange().get("A1").getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
		sheet.getCellRange("A1").getCellStyle().getExcelFont().setSize(20);
		sheet.getCellRange("A1").getCellStyle().getExcelFont().isBold(true);

		sheet.insertArray(sheetTitle, 1, 1, false);

		String[] sheetTime = new String[] { "From " + fromDate + " to " + toDate };
		sheet.getRange().get("A2:C2").merge();
		sheet.getRange().get("A2").getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
		sheet.insertArray(sheetTime, 2, 1, false);

		LocalDateTime now = LocalDateTime.now();
		String[] sheetMadeBy = new String[] {"Created at " + Controller.formatter.format(now)};
		sheet.getRange().get("A3:C3").merge();
		sheet.getRange().get("A3").getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
		sheet.insertArray(sheetMadeBy, 3, 1, false);

		String st = status.equals("All") ? "All status" : status;
		String[] sheetStatus = new String[] { "Status: " + st };
		sheet.getRange().get("A4:C4").merge();
		sheet.getRange().get("A4").getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
		sheet.insertArray(sheetStatus, 4, 1, false);

		String[] sheetHeadline = new String[] { "Product line ID", "Product line name", "Quantity" };
		sheet.insertArray(sheetHeadline, 7, 1, false);

		int line = 8;
		for (ReportResult rp : reports) {
			sheet.insertArray(new String[] { rp.getProductLineID(), rp.getProductLineName(),
					String.valueOf(rp.getQuantity()) }, line++, 1, false);
		}

		String[] sheetTotalQuantity = new String[] { "Total quantity: " + String.valueOf(totalQuantity) };
		sheet.getRange().get("A5:C5").merge();
		sheet.getRange().get("A5").getCellStyle().setHorizontalAlignment(HorizontalAlignType.Center);
		sheet.insertArray(sheetTotalQuantity, 5, 1, false);
		
		CellRange cr = sheet.getCellRange(7, sheet.getFirstColumn(), sheet.getLastRow(), 3);
		cr.getBorders().setLineStyle(LineStyleType.Thin);
        cr.getBorders().getByBordersLineType(BordersLineType.DiagonalDown).setLineStyle(LineStyleType.None);
        cr.getBorders().getByBordersLineType(BordersLineType.DiagonalUp).setLineStyle(LineStyleType.None);
        cr.getBorders().setColor(Color.BLACK);
        
		String reportFileName = "SecurityReport_" + fromDate + "_"
				+ toDate + ".xlsx";
		String reportFilePath = System.getProperty("user.home") + "\\Downloads" + "\\" + reportFileName;
		wb.saveToFile(reportFilePath, ExcelVersion.Version2016);
		
		return "Report saved at " + reportFilePath;
	}
	
	public static String updateTagFromFile(String path) {
		Workbook workbook = new Workbook();
		workbook.loadFromFile(path);
		Worksheet sheet = workbook.getWorksheets().get(0);

		int sheetLength = sheet.getLastRow();
		
		int success = 0;
		int failed = 0;

		for (int i = 1; i <= sheetLength; i++) {
			CellRange tagIdCell = sheet.getRange().get("A" + i);
			CellRange productIdCell = sheet.getRange().get("B" + i);
			CellRange statusCell = sheet.getRange().get("C" + i);
			
			Tag tag = new Tag(tagIdCell.getText(), productIdCell.getText(),
							statusCell.getNumberText().equals("1") ? true : false); 
			if(SqlQuery.updateTag(tag))
				success++; 
			else 
				failed++;
		}
		
		return "Finish update: " + success + " success, " + failed + " failed";
	}
}
