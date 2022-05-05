package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JTabbedPane;
import net.miginfocom.swing.MigLayout;
import sqlHandler.SqlQuery;
import sqlHandler.model.CheckoutInfo;
import sqlHandler.model.FilterQuery;
import sqlHandler.model.HistoryResult;
import sqlHandler.model.ReportResult;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.github.lgooddatepicker.components.DatePicker;
import com.spire.xls.ExcelVersion;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;



public class MainApplication extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	static DefaultTableModel checkoutModel;
	private static JTable tblCheckout;
	static Object[] checkoutRow;
	
	static DefaultTableModel historyModel;
	private static JTable tblHistory;
	static Object[] historyRow;
	
	static DefaultTableModel reportModel;
	private static JTable tblReport;
	static Object[] reportRow;
	
	private DatePicker dpHistoryFrom;
	private DatePicker dpHistoryTo;
	
	private DatePicker dpReportFrom;
	private DatePicker dpReportTo;
	private static JTextField txtReportTotalQuantity;
	
	
	private static ArrayList<HistoryResult> histories = new ArrayList<>();
	
	private static ArrayList<ReportResult> reports = new ArrayList<>();
	
	static int totalQuantity = 0;

	public MainApplication() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1006, 689);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[][grow]"));

		JLabel lblTitle = new JLabel("RFID Security");
		lblTitle.setForeground(Color.BLACK);
		lblTitle.setFont(new Font("Malgun Gothic", Font.BOLD, 24));
		lblTitle.setBorder(new EmptyBorder(10, 0, 10, 0));
		contentPane.add(lblTitle, "cell 0 0");

		checkoutModel = new DefaultTableModel();
		Object[] checkoutColumn = { "Tag ID", "Product line ID", "Product line name", "Time passed", "Gate number", "Status" };
		checkoutRow = new Object[6];
		checkoutModel.setColumnIdentifiers(checkoutColumn);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(Color.DARK_GRAY);
		tabbedPane.setFont(new Font("Malgun Gothic", Font.PLAIN, 16));
		contentPane.add(tabbedPane, "cell 0 1,grow");
		
		
		/////////////// Check out  /////////////////////

		JPanel checkoutPanel = new JPanel();
		tabbedPane.addTab("Checkout", null, checkoutPanel, null);
		checkoutPanel.setLayout(new BoxLayout(checkoutPanel, BoxLayout.X_AXIS));

		JScrollPane scrollPane = new JScrollPane();
		checkoutPanel.add(scrollPane);

		tblCheckout = new JTable();
		
		scrollPane.setViewportView(tblCheckout);
		tblCheckout.setModel(checkoutModel);
		TableColumnModel columnModel = tblCheckout.getColumnModel();
		
		columnModel.getColumn(0).setPreferredWidth(200);
		columnModel.getColumn(0).setMaxWidth(220);
		columnModel.getColumn(1).setPreferredWidth(100);
		columnModel.getColumn(1).setMaxWidth(120);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(2).setMaxWidth(220);
		
		tblCheckout.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
		tblCheckout.setDefaultEditor(Object.class, null);
		
		///////////////////// History ///////////////////////
		
		JPanel historyPanel = new JPanel();
		historyPanel.setBackground(new Color(248, 248, 255));
		
		tabbedPane.addTab("History", null, historyPanel, null);
		historyPanel.setLayout(new MigLayout("", "[grow,fill][200:300:400]", "[grow]"));
		
		JPanel leftHistoryJPanel  = new JPanel();
		leftHistoryJPanel.setBackground(new Color(248, 248, 255));
		historyPanel.add(leftHistoryJPanel, "cell 0 0,grow");
		leftHistoryJPanel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		leftHistoryJPanel.add(scrollPane_2, "cell 0 0,grow");
		
		historyModel = new DefaultTableModel();
		Object[] historyColumn = {"Tag ID", "Product line ID", "Product line name", "Time passed", "Gate number", "Status"};
		historyRow = new Object[6];
		historyModel.setColumnIdentifiers(historyColumn);
		
		tblHistory = new JTable();
		tblHistory.setBackground(Color.WHITE);
		scrollPane_2.setViewportView(tblHistory);
		tblHistory.setModel(historyModel);
		
		tblHistory.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
		tblHistory.setDefaultEditor(Object.class, null);
		
		JPanel rightHistoryPanel = new JPanel();
		rightHistoryPanel.setBackground(new Color(248, 248, 255));
		historyPanel.add(rightHistoryPanel, "cell 1 0,grow");
		rightHistoryPanel.setLayout(new MigLayout("", "[grow]", "[][][][][][][][][][][][][][][][]"));
		
		JLabel lblNewLabel_11 = new JLabel("Filter");
		rightHistoryPanel.add(lblNewLabel_11, "cell 0 0");
		
		JComboBox<String> cbxHistoryFilter = new JComboBox<>();
		
		cbxHistoryFilter.setBackground(new Color(240, 248, 255));
		cbxHistoryFilter.setModel(new DefaultComboBoxModel<String>(new String[] {"None","Tag ID", "Product line ID", "Product line name"}));
		rightHistoryPanel.add(cbxHistoryFilter, "cell 0 1, growx");
		
		JTextField txtHistoryFilter = new JTextField();
		txtHistoryFilter.setBackground(new Color(255, 255, 255));
		txtHistoryFilter.setEnabled(false);
		JLabel lblNewLabel_12 = new JLabel("Keyword");
		rightHistoryPanel.add(lblNewLabel_12, "cell 0 2");
		rightHistoryPanel.add(txtHistoryFilter, "cell 0 3, growx");
		
		cbxHistoryFilter.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(cbxHistoryFilter.getSelectedItem().equals("None")) {
					txtHistoryFilter.setText("");
					txtHistoryFilter.setEnabled(false);
				}
				else {
					txtHistoryFilter.setEnabled(true);
				}
				
			}
		});
		
		JLabel lblNewLabel11 = new JLabel("From");
		rightHistoryPanel.add(lblNewLabel11, "cell 0 4");
	
		
		dpHistoryFrom = new DatePicker();
		dpHistoryFrom.getComponentDateTextField().setBackground(new Color(240, 248, 255));
		rightHistoryPanel.add(dpHistoryFrom, "cell 0 5,growx");
		
		JLabel lblNewLabel_13 = new JLabel("To");
		rightHistoryPanel.add(lblNewLabel_13, "cell 0 6");
		
		dpHistoryTo = new DatePicker();
		dpHistoryTo.getComponentDateTextField().setBackground(new Color(240, 248, 255));
		rightHistoryPanel.add(dpHistoryTo, "cell 0 7,growx");
		
		JLabel lblNewLabel_21 = new JLabel("Status");
		rightHistoryPanel.add(lblNewLabel_21, "cell 0 8");
		
		JComboBox<String> cbxHistoryStatus = new JComboBox<>();
		cbxHistoryStatus.setBackground(new Color(240, 248, 255));
		cbxHistoryStatus.setModel(new DefaultComboBoxModel<String>(new String[] {"All", "Purchased", "Not purchased"}));
		
		rightHistoryPanel.add(cbxHistoryStatus, "cell 0 9,growx");
		
		JLabel lblNewLabel_22 = new JLabel("Order by");
		rightHistoryPanel.add(lblNewLabel_22, "cell 0 10");
		
		JComboBox<String> cbxHistoryOrderBy = new JComboBox<>();
		cbxHistoryOrderBy.setBackground(new Color(240, 248, 255));
		cbxHistoryOrderBy.setModel(new DefaultComboBoxModel<String>(new String[] {"None", "Tag ID (A-Z)", "Tag ID (Z-A)", "Product line ID (A-Z)", "Product line ID (Z-A)",
				"Product line name (A-Z)", "Product line name (Z-A)", "Time passed (Newest)", "Time passed (Oldest)", "Gate number (Asc)", "Gate number (Desc)"}));
		
		rightHistoryPanel.add(cbxHistoryOrderBy, "cell 0 11,growx");
		
		Component verticalStrut2 = Box.createVerticalStrut(20);
		rightHistoryPanel.add(verticalStrut2, "cell 0 12");
		
		JButton btnViewHistory = new JButton("View history");
		
		rightHistoryPanel.add(btnViewHistory, "cell 0 13,growx");
		
		btnViewHistory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(dpHistoryFrom.getDateStringOrEmptyString().isEmpty() || dpHistoryTo.getDateStringOrEmptyString().isEmpty()) {
					alert("Please select the time");
					return;
				}
				if(dpHistoryFrom.getDate().isAfter(dpHistoryTo.getDate()) || dpHistoryFrom.getDate().isEqual(dpHistoryTo.getDate())) {
					alert("The selected time is not valid");
					return;
				}
				FilterQuery query = new FilterQuery(dpHistoryFrom.getDateStringOrEmptyString(),
						dpHistoryTo.getDateStringOrEmptyString(), cbxHistoryStatus.getSelectedItem().toString(),
						cbxHistoryFilter.getSelectedItem().toString(), txtHistoryFilter.getText(), cbxHistoryOrderBy.getSelectedItem().toString());
				
				histories = SqlQuery.getHistory(query);
				if(histories.isEmpty())
					alert("No data for this filter");
				updateHistory();
				
			}
		});
		
		/////////////////////  Report /////////////////////////
		
		JPanel reportPanel = new JPanel();
		reportPanel.setBackground(new Color(248, 248, 255));
		
		tabbedPane.addTab("Report", null, reportPanel, null);
		reportPanel.setLayout(new MigLayout("", "[grow,fill][200:300:400]", "[grow]"));
		
		JPanel leftReportPanel = new JPanel();
		leftReportPanel.setBackground(new Color(248, 248, 255));
		reportPanel.add(leftReportPanel, "cell 0 0,grow");
		leftReportPanel.setLayout(new MigLayout("", "[grow]", "[grow]"));		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		leftReportPanel.add(scrollPane_1, "cell 0 0,grow");
		
		reportModel = new DefaultTableModel();
		Object[] reportColumn = {"Product line ID", "Product line name", "Quantity"};
		reportRow = new Object[3];
		reportModel.setColumnIdentifiers(reportColumn);
		
		tblReport = new JTable();
		tblReport.setBackground(Color.WHITE);
		scrollPane_1.setViewportView(tblReport);
		tblReport.setModel(reportModel);
		tblReport.setDefaultEditor(Object.class, null);
		
		JPanel rightReportPanel = new JPanel();
		rightReportPanel.setBackground(new Color(248, 248, 255));
		reportPanel.add(rightReportPanel, "cell 1 0,grow");
		rightReportPanel.setLayout(new MigLayout("", "[grow]", "[][][][][][][][][][][][][][][][]"));
		
		JLabel lblNewLabel_4 = new JLabel("Filter");
		rightReportPanel.add(lblNewLabel_4, "cell 0 0");
		
		JComboBox<String> cbxReportFilter = new JComboBox<>();
		cbxReportFilter.setBackground(new Color(240, 248, 255));
		cbxReportFilter.setModel(new DefaultComboBoxModel<String>(new String[] {"None", "Product line name", "Product line ID"}));
		
		rightReportPanel.add(cbxReportFilter, "cell 0 1, growx");
		
		JTextField txtReportFilter = new JTextField();
		txtReportFilter.setBackground(new Color(255, 255, 255));
		txtReportFilter.setEnabled(false);
		JLabel lblNewLabel_5 = new JLabel("Keyword");
		rightReportPanel.add(lblNewLabel_5, "cell 0 2");
		rightReportPanel.add(txtReportFilter, "cell 0 3, growx");
		
		cbxReportFilter.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(cbxReportFilter.getSelectedItem().equals("None")) {
					txtReportFilter.setText("");
					txtReportFilter.setEnabled(false);
				}
				else {
					txtReportFilter.setEnabled(true);
				}
				
			}
		});
		
		JLabel lblNewLabel = new JLabel("From");
		rightReportPanel.add(lblNewLabel, "cell 0 4");
	
		
		dpReportFrom = new DatePicker();
		dpReportFrom.getComponentDateTextField().setBackground(new Color(240, 248, 255));
		rightReportPanel.add(dpReportFrom, "cell 0 5,growx");
		
		JLabel lblNewLabel_1 = new JLabel("To");
		rightReportPanel.add(lblNewLabel_1, "cell 0 6");
		
		dpReportTo = new DatePicker();
		dpReportTo.getComponentDateTextField().setBackground(new Color(240, 248, 255));
		rightReportPanel.add(dpReportTo, "cell 0 7,growx");
		
		JLabel lblNewLabel_2 = new JLabel("Status");
		rightReportPanel.add(lblNewLabel_2, "cell 0 8");
		
		JComboBox<String> cbxReportStatus = new JComboBox<>();
		cbxReportStatus.setBackground(new Color(240, 248, 255));
		cbxReportStatus.setModel(new DefaultComboBoxModel<String>(new String[] {"All", "Purchased", "Not purchased"}));
		
		rightReportPanel.add(cbxReportStatus, "cell 0 9,growx");
		
		JLabel lblNewLabel_24 = new JLabel("Order by");
		rightReportPanel.add(lblNewLabel_24, "cell 0 10");
		
		JComboBox<String> cbxReportOrderBy = new JComboBox<>();
		cbxReportOrderBy.setBackground(new Color(240, 248, 255));
		cbxReportOrderBy.setModel(new DefaultComboBoxModel<String>(new String[] {"None", "Product line ID (A-Z)", "Product line ID (Z-A)",
				"Product line name (A-Z)", "Product line name (Z-A)", "Quantity (Asc)", "Quantity (Desc)"}));
		
		rightReportPanel.add(cbxReportOrderBy, "cell 0 11,growx");
		
		Component verticalStrut = Box.createVerticalStrut(20);
		rightReportPanel.add(verticalStrut, "cell 0 12");
		
		JButton btnReport = new JButton("View report");
		
		rightReportPanel.add(btnReport, "cell 0 13,growx");
		
		JButton btnExportReport = new JButton("Export to Excel file");
		btnExportReport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(reports.isEmpty()) {
					alert("No data to export");
					return;
				}
				Workbook wb = new Workbook();
				Worksheet sheet = wb.getWorksheets().get(0);
				String[] sheetHeadline = new String[]{"Product line ID", "Product line name", "Quantity"};
				sheet.insertArray(sheetHeadline, 1, 1, false);
				int line = 2;
				for(ReportResult rp : reports) {
					sheet.insertArray(
							new String[]{rp.getProductLineID(), rp.getProductLineName(), String.valueOf(rp.getQuantity())}, line++, 1, false);
				}
				sheet.insertArray(new String[]{"", "Total", String.valueOf(totalQuantity)}, reports.size()+3, 1, false);
				String reportFileName = "SecurityReport_" 
						+ dpReportFrom.getDateStringOrEmptyString() + "_"
						+ dpReportTo.getDateStringOrEmptyString()
						+ ".xlsx";
				String reportFilePath = System.getProperty("user.home")  + "\\Downloads" + "\\" + reportFileName;
				wb.saveToFile(reportFilePath, ExcelVersion.Version2016);
				alert("Report saved at " + reportFilePath);

			}
		});
		rightReportPanel.add(btnExportReport, "cell 0 14,growx");
		
		
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		rightReportPanel.add(verticalStrut_1, "cell 0 15");
		
		JLabel lblNewLabel_3 = new JLabel("Total quantity");
		rightReportPanel.add(lblNewLabel_3, "cell 0 16");
		
		txtReportTotalQuantity = new JTextField();
		txtReportTotalQuantity.setBackground(new Color(248, 248, 255));
		txtReportTotalQuantity.setEditable(false);
		rightReportPanel.add(txtReportTotalQuantity, "cell 0 17,growx");
		txtReportTotalQuantity.setColumns(10);
		txtReportTotalQuantity.setText(String.valueOf(totalQuantity));
		
		
		
		btnReport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(dpReportFrom.getDateStringOrEmptyString().isEmpty() || dpReportTo.getDateStringOrEmptyString().isEmpty()) {
					alert("Please select the time");
					return;
				}
				if(dpReportFrom.getDate().isAfter(dpReportTo.getDate()) || dpReportFrom.getDate().isEqual(dpReportTo.getDate())) {
					alert("The selected time is not valid");
					return;
				}
				FilterQuery query = new FilterQuery(dpReportFrom.getDateStringOrEmptyString(),
						dpReportTo.getDateStringOrEmptyString(), cbxReportStatus.getSelectedItem().toString(),
						cbxReportFilter.getSelectedItem().toString(), txtReportFilter.getText(), cbxReportOrderBy.getSelectedItem().toString());
				
				reports = SqlQuery.getReport(query);
				if(reports.isEmpty())
					alert("No data for this filter");
				updateReport();
				
			}
		});
	}

	public static void addNewLineToCheckoutTable(CheckoutInfo tag) {
		checkoutRow[0] = tag.getTagId();
		checkoutRow[1] = tag.getProductId();
		checkoutRow[2] = tag.getProductName();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		checkoutRow[3] = tag.getTime().format(formatter).toString();
		checkoutRow[4] = tag.getGateNumber();
		checkoutRow[5] = tag.isPurchased() ? "Purchased" : "Not purchased";
		checkoutModel.addRow(checkoutRow);
		tblCheckout.revalidate();
		tblCheckout.repaint();
	}
	
	public static void updateReport() {
		reportModel.setRowCount(0);
		totalQuantity = 0;
		for(ReportResult rp : reports) {
			System.out.println(rp.getProductLineID() + " " + rp.getProductLineName() + " " + rp.getQuantity());
			reportRow[0] = rp.getProductLineID();
			reportRow[1] = rp.getProductLineName();
			reportRow[2] = String.valueOf(rp.getQuantity());
			reportModel.addRow(reportRow);
			totalQuantity += rp.getQuantity();
		}
		txtReportTotalQuantity.setText(String.valueOf(totalQuantity));
		tblReport.revalidate();
		tblReport.repaint();
	}
	
	public static void updateHistory() {
		historyModel.setRowCount(0);
		for(HistoryResult hr : histories) {
			historyRow[0] = hr.getTagId();
			historyRow[1] = hr.getProductLineID();
			historyRow[2] = hr.getProductLineName();
			historyRow[3] = hr.getTimePassed();
			historyRow[4] = String.valueOf(hr.getGateNumber());
			historyRow[5] = hr.getStatus();
			historyModel.addRow(historyRow);
		}
		tblHistory.revalidate();
		tblHistory.repaint();
	}
	
	void alert(String str) {
		JOptionPane.showMessageDialog(null, str);
	}

}
