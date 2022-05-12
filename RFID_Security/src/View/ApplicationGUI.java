package View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JTabbedPane;
import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.github.lgooddatepicker.components.DatePicker;
import Controller.Controller;
import Controller.FileController;
import Controller.ReadTags;
import Model.Entities.Recent;
import Model.Entities.FilterQuery;
import Model.Entities.HistoryResult;
import Model.Entities.ProductLine;
import Model.Entities.ReportResult;
import Model.Entities.TagResult;

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
import java.io.FileNotFoundException;

import javax.swing.Box;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.SwingConstants;

public class ApplicationGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private static JPanel contentPane;

	static DefaultTableModel recentModel;
	private static JTable tblRecent;
	static Object[] recentRow;

	static DefaultTableModel historyModel;
	private static JTable tblHistory;
	static Object[] historyRow;

	static DefaultTableModel reportModel;
	private static JTable tblReport;
	static Object[] reportRow;

	static DefaultTableModel tagModel;
	private static JTable tblTag;
	static Object[] tagRow;

	static DefaultTableModel productModel;
	private static JTable tblProduct;
	static Object[] productRow;

	private DatePicker dpHistoryFrom;
	private DatePicker dpHistoryTo;

	private DatePicker dpReportFrom;
	private DatePicker dpReportTo;

	private static JTextField txtReportTotalQuantity;

	private static ArrayList<HistoryResult> histories = new ArrayList<>();

	private static ArrayList<ReportResult> reports = new ArrayList<>();

	private static ArrayList<TagResult> tags = new ArrayList<>();

	private static ArrayList<ProductLine> productLines = new ArrayList<>();

	static int totalQuantity = 0;

	String selectedTagID = "";
	int selectedUserID = 0;

	public ApplicationGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1006, 689);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[grow 0][grow]"));

		JPanel headPanel = new JPanel();
		headPanel.setBackground(new Color(248, 248, 255));
		contentPane.add(headPanel, "cell 0 0,grow");
		headPanel.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow][grow]", "[]"));
		
		JLabel lblNewLabel_4 = new JLabel("RFID Security");
		lblNewLabel_4.setFont(new Font("Malgun Gothic", Font.PLAIN, 20));
		headPanel.add(lblNewLabel_4, "cell 0 0");
		
		JButton btnStartStop = new JButton("Start");
		btnStartStop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(btnStartStop.getText().equals("Start")) {
					ReadTags.startReading();
					btnStartStop.setText("Stop");
				}
				else {
					ReadTags.stopReading();
					btnStartStop.setText("Start");
				}
			}
		});
		btnStartStop.setFont(new Font("Malgun Gothic", Font.PLAIN, 14));
		headPanel.add(btnStartStop, "cell 6 0, growx");

		recentModel = new DefaultTableModel();
		Object[] recentColumn = { "Tag ID", "Product line ID", "Product line name", "Time passed", "Gate number",
				"Status" };
		recentRow = new Object[6];
		recentModel.setColumnIdentifiers(recentColumn);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(Color.DARK_GRAY);
		tabbedPane.setFont(new Font("Malgun Gothic", Font.PLAIN, 16));
		contentPane.add(tabbedPane, "cell 0 1,grow");

		// Check out

		JPanel recentPanel = new JPanel();
		tabbedPane.addTab("Recent", null, recentPanel, null);
		recentPanel.setLayout(new BoxLayout(recentPanel, BoxLayout.X_AXIS));

		JScrollPane scrollPane = new JScrollPane();
		recentPanel.add(scrollPane);

		tblRecent = new JTable();
		tblRecent.setFont(new Font("Malgun Gothic", Font.PLAIN, 12));

		scrollPane.setViewportView(tblRecent);
		tblRecent.setModel(recentModel);
		TableColumnModel columnModel = tblRecent.getColumnModel();

		columnModel.getColumn(0).setPreferredWidth(200);
		columnModel.getColumn(0).setMaxWidth(220);
		columnModel.getColumn(1).setPreferredWidth(100);
		columnModel.getColumn(1).setMaxWidth(120);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(2).setMaxWidth(220);

		tblRecent.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
		tblRecent.setDefaultEditor(Object.class, null);

		// History

		JPanel historyPanel = new JPanel();
		historyPanel.setBackground(new Color(248, 248, 255));

		tabbedPane.addTab("History", null, historyPanel, null);
		historyPanel.setLayout(new MigLayout("", "[grow,fill][200:300:400]", "[grow]"));

		JPanel leftHistoryJPanel = new JPanel();
		leftHistoryJPanel.setBackground(new Color(248, 248, 255));
		historyPanel.add(leftHistoryJPanel, "cell 0 0,grow");
		leftHistoryJPanel.setLayout(new MigLayout("", "[grow]", "[grow]"));

		JScrollPane scrollPane_2 = new JScrollPane();
		leftHistoryJPanel.add(scrollPane_2, "cell 0 0,grow");

		historyModel = new DefaultTableModel();
		Object[] historyColumn = { "Tag ID", "Product line ID", "Product line name", "Time passed", "Gate number",
				"Status" };
		historyRow = new Object[6];
		historyModel.setColumnIdentifiers(historyColumn);

		tblHistory = new JTable();
		tblHistory.setFont(new Font("Malgun Gothic", Font.PLAIN, 12));
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
		cbxHistoryFilter.setFont(new Font("Malgun Gothic", Font.PLAIN, 14));

		cbxHistoryFilter.setBackground(new Color(240, 248, 255));
		cbxHistoryFilter.setModel(new DefaultComboBoxModel<String>(
				new String[] { "None", "Tag ID", "Product line ID", "Product line name" }));
		rightHistoryPanel.add(cbxHistoryFilter, "cell 0 1, growx");

		JTextField txtHistoryFilter = new JTextField();
		txtHistoryFilter.setFont(new Font("Malgun Gothic", Font.PLAIN, 12));
		txtHistoryFilter.setBackground(new Color(255, 255, 255));
		txtHistoryFilter.setEnabled(false);
		JLabel lblNewLabel_12 = new JLabel("Keyword");
		rightHistoryPanel.add(lblNewLabel_12, "cell 0 2");
		rightHistoryPanel.add(txtHistoryFilter, "cell 0 3, growx");

		cbxHistoryFilter.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (cbxHistoryFilter.getSelectedItem().equals("None")) {
					txtHistoryFilter.setText("");
					txtHistoryFilter.setEnabled(false);
				} else {
					txtHistoryFilter.setEnabled(true);
				}

			}
		});

		JLabel lblNewLabel11 = new JLabel("From");
		rightHistoryPanel.add(lblNewLabel11, "cell 0 4");

		dpHistoryFrom = new DatePicker();
		dpHistoryFrom.getComponentDateTextField().setFont(new Font("Malgun Gothic", Font.PLAIN, 14));
		dpHistoryFrom.getComponentDateTextField().setBackground(new Color(240, 248, 255));
		rightHistoryPanel.add(dpHistoryFrom, "cell 0 5,growx");

		JLabel lblNewLabel_13 = new JLabel("To");
		rightHistoryPanel.add(lblNewLabel_13, "cell 0 6");

		dpHistoryTo = new DatePicker();
		dpHistoryTo.getComponentDateTextField().setFont(new Font("Malgun Gothic", Font.PLAIN, 14));
		dpHistoryTo.getComponentDateTextField().setBackground(new Color(240, 248, 255));
		rightHistoryPanel.add(dpHistoryTo, "cell 0 7,growx");

		JLabel lblNewLabel_21 = new JLabel("Status");
		rightHistoryPanel.add(lblNewLabel_21, "cell 0 8");

		JComboBox<String> cbxHistoryStatus = new JComboBox<>();
		cbxHistoryStatus.setFont(new Font("Malgun Gothic", Font.PLAIN, 14));
		cbxHistoryStatus.setBackground(new Color(240, 248, 255));
		cbxHistoryStatus
				.setModel(new DefaultComboBoxModel<String>(new String[] { "All", "Purchased", "Not purchased" }));

		rightHistoryPanel.add(cbxHistoryStatus, "cell 0 9,growx");

		JLabel lblNewLabel_22 = new JLabel("Order by");
		rightHistoryPanel.add(lblNewLabel_22, "cell 0 10");

		JComboBox<String> cbxHistoryOrderBy = new JComboBox<>();
		cbxHistoryOrderBy.setFont(new Font("Malgun Gothic", Font.PLAIN, 14));
		cbxHistoryOrderBy.setBackground(new Color(240, 248, 255));
		cbxHistoryOrderBy.setModel(new DefaultComboBoxModel<String>(
				new String[] { "None", "Tag ID (A-Z)", "Tag ID (Z-A)", "Product line ID (A-Z)", "Product line ID (Z-A)",
						"Product line name (A-Z)", "Product line name (Z-A)", "Time passed (Newest)",
						"Time passed (Oldest)", "Gate number (Asc)", "Gate number (Desc)" }));

		rightHistoryPanel.add(cbxHistoryOrderBy, "cell 0 11,growx");

		Component verticalStrut2 = Box.createVerticalStrut(20);
		rightHistoryPanel.add(verticalStrut2, "cell 0 12");

		JButton btnViewHistory = new JButton("View history");
		btnViewHistory.setFont(new Font("Malgun Gothic", Font.PLAIN, 14));

		rightHistoryPanel.add(btnViewHistory, "cell 0 13,growx");

		btnViewHistory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (dpHistoryFrom.getDateStringOrEmptyString().isEmpty()
						|| dpHistoryTo.getDateStringOrEmptyString().isEmpty()) {
					alert("Please select the time");
					return;
				}
				if (dpHistoryFrom.getDate().isAfter(dpHistoryTo.getDate())
						|| dpHistoryFrom.getDate().isEqual(dpHistoryTo.getDate())) {
					alert("The selected time is not valid");
					return;
				}
				FilterQuery query = new FilterQuery(dpHistoryFrom.getDateStringOrEmptyString(),
						dpHistoryTo.getDateStringOrEmptyString(), cbxHistoryStatus.getSelectedItem().toString(),
						cbxHistoryFilter.getSelectedItem().toString(), txtHistoryFilter.getText(),
						cbxHistoryOrderBy.getSelectedItem().toString());

				histories = Controller.getHistories(query);
				if (histories.isEmpty())
					alert("No data for this filter");
				updateHistory();

			}
		});

		// Report

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
		Object[] reportColumn = { "Product line ID", "Product line name", "Quantity" };
		reportRow = new Object[3];
		reportModel.setColumnIdentifiers(reportColumn);

		tblReport = new JTable();
		tblReport.setFont(new Font("Malgun Gothic", Font.PLAIN, 12));
		tblReport.setBackground(Color.WHITE);
		scrollPane_1.setViewportView(tblReport);
		tblReport.setModel(reportModel);
		tblReport.setDefaultEditor(Object.class, null);

		JPanel rightReportPanel = new JPanel();
		rightReportPanel.setBackground(new Color(248, 248, 255));
		reportPanel.add(rightReportPanel, "cell 1 0,grow");
		rightReportPanel.setLayout(new MigLayout("", "[grow]", "[][][][][][][][][][][][][][][][]"));

		JLabel lblNewLabel = new JLabel("From");
		rightReportPanel.add(lblNewLabel, "cell 0 0");

		dpReportFrom = new DatePicker();
		dpReportFrom.getComponentDateTextField().setBackground(new Color(240, 248, 255));
		rightReportPanel.add(dpReportFrom, "cell 0 1,growx");

		JLabel lblNewLabel_1 = new JLabel("To");
		rightReportPanel.add(lblNewLabel_1, "cell 0 2");

		dpReportTo = new DatePicker();
		dpReportTo.getComponentDateTextField().setBackground(new Color(240, 248, 255));
		rightReportPanel.add(dpReportTo, "cell 0 3,growx");

		JLabel lblNewLabel_2 = new JLabel("Status");
		rightReportPanel.add(lblNewLabel_2, "cell 0 4");

		JComboBox<String> cbxReportStatus = new JComboBox<>();
		cbxReportStatus.setFont(new Font("Malgun Gothic", Font.PLAIN, 14));
		cbxReportStatus.setBackground(new Color(240, 248, 255));
		cbxReportStatus
				.setModel(new DefaultComboBoxModel<String>(new String[] { "All", "Purchased", "Not purchased" }));

		rightReportPanel.add(cbxReportStatus, "cell 0 5,growx");

		JLabel lblNewLabel_24 = new JLabel("Order by");
		rightReportPanel.add(lblNewLabel_24, "cell 0 6");

		JComboBox<String> cbxReportOrderBy = new JComboBox<>();
		cbxReportOrderBy.setFont(new Font("Malgun Gothic", Font.PLAIN, 14));
		cbxReportOrderBy.setBackground(new Color(240, 248, 255));
		cbxReportOrderBy.setModel(new DefaultComboBoxModel<String>(
				new String[] { "None", "Product line ID (A-Z)", "Product line ID (Z-A)", "Product line name (A-Z)",
						"Product line name (Z-A)", "Quantity (Asc)", "Quantity (Desc)" }));

		rightReportPanel.add(cbxReportOrderBy, "cell 0 7,growx");

		Component verticalStrut = Box.createVerticalStrut(20);
		rightReportPanel.add(verticalStrut, "cell 0 8");

		JButton btnReport = new JButton("View report");
		btnReport.setFont(new Font("Malgun Gothic", Font.PLAIN, 14));

		rightReportPanel.add(btnReport, "cell 0 9,growx");

		JButton btnExportReport = new JButton("Export to Excel file");
		btnExportReport.setFont(new Font("Malgun Gothic", Font.PLAIN, 14));
		btnExportReport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (reports.isEmpty()) {
					alert("No data to export");
					return;
				}
				String result = FileController.exportReportFile(dpReportFrom.getDateStringOrEmptyString(),
						dpReportTo.getDateStringOrEmptyString(), cbxReportStatus.getSelectedItem().toString(),
						totalQuantity, reports);
				alert(result);

			}
		});

		rightReportPanel.add(btnExportReport, "cell 0 10,growx");

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		rightReportPanel.add(verticalStrut_1, "cell 0 11");

		JLabel lblNewLabel_3 = new JLabel("Total quantity");
		rightReportPanel.add(lblNewLabel_3, "cell 0 12");

		txtReportTotalQuantity = new JTextField();
		txtReportTotalQuantity.setFont(new Font("Malgun Gothic", Font.PLAIN, 14));
		txtReportTotalQuantity.setBackground(new Color(248, 248, 255));
		txtReportTotalQuantity.setEditable(false);
		rightReportPanel.add(txtReportTotalQuantity, "cell 0 13,growx");
		txtReportTotalQuantity.setColumns(10);
		txtReportTotalQuantity.setText(String.valueOf(totalQuantity));

		btnReport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (dpReportFrom.getDateStringOrEmptyString().isEmpty()
						|| dpReportTo.getDateStringOrEmptyString().isEmpty()) {
					alert("Please select the time");
					return;
				}
				if (dpReportFrom.getDate().isAfter(dpReportTo.getDate())
						|| dpReportFrom.getDate().isEqual(dpReportTo.getDate())) {
					alert("The selected time is not valid");
					return;
				}
				FilterQuery query = new FilterQuery(dpReportFrom.getDateStringOrEmptyString(),
						dpReportTo.getDateStringOrEmptyString(), cbxReportStatus.getSelectedItem().toString(), "none",
						"none", cbxReportOrderBy.getSelectedItem().toString());

				reports = Controller.getReports(query);
				if (reports.isEmpty())
					alert("No data for this filter");
				updateReport();

			}
		});

		// Tag

		JPanel tagPanel = new JPanel();
		tagPanel.setBackground(new Color(248, 248, 255));

		tabbedPane.addTab("Tag", null, tagPanel, null);
		tagPanel.setLayout(new MigLayout("", "[grow,fill][200:300:400]", "[grow]"));

		JPanel leftTagPanel = new JPanel();
		leftTagPanel.setBackground(new Color(248, 248, 255));
		tagPanel.add(leftTagPanel, "cell 0 0,grow");
		leftTagPanel.setLayout(new MigLayout("", "[grow]", "[grow]"));

		JScrollPane scrollPane_12 = new JScrollPane();
		leftTagPanel.add(scrollPane_12, "cell 0 0,grow");

		tagModel = new DefaultTableModel();
		Object[] tagColumn = { "Tag ID", "Product line ID", "Product line name", "Status" };
		tagRow = new Object[4];
		tagModel.setColumnIdentifiers(tagColumn);

		tblTag = new JTable();
		tblTag.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				selectedTagID = (String) tblTag.getValueAt(tblTag.getSelectedRow(), 0);
			}
		});
		tblTag.setFont(new Font("Malgun Gothic", Font.PLAIN, 12));
		tblTag.setBackground(Color.WHITE);
		scrollPane_12.setViewportView(tblTag);
		tblTag.setModel(tagModel);
		tblTag.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
		tblTag.setDefaultEditor(Object.class, null);

		JPanel rightTagPanel = new JPanel();
		rightTagPanel.setBackground(new Color(248, 248, 255));
		tagPanel.add(rightTagPanel, "cell 1 0,grow");
		rightTagPanel.setLayout(new MigLayout("", "[grow]", "[][][][][][][][][][][][][][][][]"));

		JButton btnUpdateTag = new JButton("Update tag");
		btnUpdateTag.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (selectedTagID == "") {
					alert("Select a tag to update");
					return;
				}
				UpdateTagGUI updateTag = new UpdateTagGUI(selectedTagID);
				updateTag.setVisible(true);
			}
		});
		btnUpdateTag.setFont(new Font("Malgun Gothic", Font.PLAIN, 14));
		rightTagPanel.add(btnUpdateTag, "cell 0 0, growx");

		updateTag();

		JButton btnUpdateTagFromFile = new JButton("Update tag from file");
		btnUpdateTagFromFile.setFont(new Font("Malgun Gothic", Font.PLAIN, 14));
		btnUpdateTagFromFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					FileGUI.pickingFile();
					String path = FileGUI.getPath();
					if (path != null && path != "") {
						alert(FileController.updateTagFromFile(path));
						updateTag();
						FileGUI.setPath("");
					}

				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		rightTagPanel.add(btnUpdateTagFromFile, "cell 0 1, growx");

		// Product
		JPanel productPanel = new JPanel();
		productPanel.setBackground(new Color(248, 248, 255));

		tabbedPane.addTab("Product", null, productPanel, null);
		productPanel.setLayout(new MigLayout("", "[grow]", "[grow]"));

		JScrollPane scrollPane_21 = new JScrollPane();
		productPanel.add(scrollPane_21, "cell 0 0,grow");

		productModel = new DefaultTableModel();
		Object[] productColumn = { "Product line ID", "Product line name"};
		productRow = new Object[2];
		productModel.setColumnIdentifiers(productColumn);

		tblProduct = new JTable();
		tblProduct.setFont(new Font("Malgun Gothic", Font.PLAIN, 12));
		tblProduct.setBackground(Color.WHITE);
		scrollPane_21.setViewportView(tblProduct);
		tblProduct.setModel(productModel);
		tblProduct.setDefaultEditor(Object.class, null);

		updateProduct();

	}

	public static void addNewLineToRecentTable(Recent tag) {
		recentRow[0] = tag.getTagId();
		recentRow[1] = tag.getProductId();
		recentRow[2] = tag.getProductName();
		recentRow[3] = tag.getTime().format(Controller.formatter).toString();
		recentRow[4] = tag.getGateNumber();
		recentRow[5] = tag.isPurchased() ? "Purchased" : "Not purchased";
		recentModel.insertRow(0, recentRow);
		tblRecent.revalidate();
		tblRecent.repaint();
	}

	public static void updateReport() {
		reportModel.setRowCount(0);
		totalQuantity = 0;
		for (ReportResult rp : reports) {
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
		for (HistoryResult hr : histories) {
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

	public static void updateTag() {
		tags = Controller.getTags();
		tagModel.setRowCount(0);
		for (TagResult tr : tags) {
			tagRow[0] = tr.getTagId();
			tagRow[1] = tr.getProductLineID();
			tagRow[2] = tr.getProductLineName();
			tagRow[3] = tr.getStatus();
			tagModel.addRow(tagRow);
		}
		tblTag.revalidate();
		tblTag.repaint();
	}

	public static void updateProduct() {
		productLines = Controller.getProductLines();
		productModel.setRowCount(0);
		for (ProductLine tr : productLines) {
			productRow[0] = tr.getProductLineID();
			productRow[1] = tr.getProductLineName();
			productModel.addRow(productRow);
		}
		tblProduct.revalidate();
		tblProduct.repaint();
	}

	public static void alert(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
}
