package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import sqlHandler.TagInfo;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.JTextField;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JTextPane;

public class MainApplication extends JFrame {

	private JPanel contentPane;
	private static JTextField txtProductId;
	private static JTextField txtProductName;
	private static JTextField txtTagId;
	private static JTextField txtTime;
	private static JTextField txtAntenna;
	private static JPanel panelIsPuschased;
	private static JLabel lblIsPuschased;
	private static JTextPane txtHistory;
	private static StyledDocument doc;
	private static SimpleAttributeSet left = new SimpleAttributeSet();
	

	/**
	 * Launch the application.
	 */
//	public static void MainApplication(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainApplication frame = new MainApplication();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public MainApplication() {
		setBackground(SystemColor.menu);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 727, 598);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		panelIsPuschased = new JPanel();
		panelIsPuschased.setBackground(SystemColor.controlShadow);
		panelIsPuschased.setBounds(10, 481, 325, 60);
		panel.add(panelIsPuschased);
		panelIsPuschased.setLayout(new BorderLayout(0, 0));

		lblIsPuschased = new JLabel("NO INFORMATION");
		panelIsPuschased.add(lblIsPuschased, BorderLayout.CENTER);
		lblIsPuschased.setForeground(Color.WHITE);
		lblIsPuschased.setHorizontalAlignment(SwingConstants.CENTER);
		lblIsPuschased.setFont(new Font("Calibri", Font.PLAIN, 20));

		JLabel lblNewLabel = new JLabel("RFID SECURITY GATE");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 30));
		lblNewLabel.setBounds(10, 10, 303, 51);
		panel.add(lblNewLabel);

		txtProductId = new JTextField();
		txtProductId.setEditable(false);
		txtProductId.setBounds(10, 183, 325, 36);
		panel.add(txtProductId);
		txtProductId.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Product ID");
		lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(10, 163, 149, 23);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Product name");
		lblNewLabel_2_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(10, 243, 149, 23);
		panel.add(lblNewLabel_2_1);

		txtProductName = new JTextField();
		txtProductName.setEditable(false);
		txtProductName.setColumns(10);
		txtProductName.setBounds(10, 262, 325, 36);
		panel.add(txtProductName);

		JLabel lblNewLabel_2_3 = new JLabel("Tag ID");
		lblNewLabel_2_3.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel_2_3.setBounds(10, 84, 149, 23);
		panel.add(lblNewLabel_2_3);

		txtTagId = new JTextField();
		txtTagId.setEditable(false);
		txtTagId.setColumns(10);
		txtTagId.setBounds(10, 104, 325, 36);
		panel.add(txtTagId);

		JLabel lblNewLabel_2_2 = new JLabel("Time");
		lblNewLabel_2_2.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel_2_2.setBounds(10, 322, 149, 23);
		panel.add(lblNewLabel_2_2);

		txtTime = new JTextField();
		txtTime.setEditable(false);
		txtTime.setColumns(10);
		txtTime.setBounds(10, 341, 325, 36);
		panel.add(txtTime);

		JLabel lblNewLabel_2_1_1 = new JLabel("Antenna");
		lblNewLabel_2_1_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel_2_1_1.setBounds(10, 401, 149, 23);
		panel.add(lblNewLabel_2_1_1);

		txtAntenna = new JTextField();
		txtAntenna.setEditable(false);
		txtAntenna.setColumns(10);
		txtAntenna.setBounds(10, 420, 325, 36);
		panel.add(txtAntenna);

		JLabel lblNewLabel_2_3_1 = new JLabel("History");
		lblNewLabel_2_3_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel_2_3_1.setBounds(366, 84, 149, 23);
		panel.add(lblNewLabel_2_3_1);

		txtHistory = new JTextPane();
		txtHistory.setEditable(false);
		txtHistory.setBounds(366, 104, 327, 437);
		panel.add(txtHistory);
		
		doc = txtHistory.getStyledDocument();
		StyleConstants.setAlignment(left, StyleConstants.ALIGN_LEFT);
	}

	public static void loading() {
		panelIsPuschased.setBackground(SystemColor.LIGHT_GRAY);
		lblIsPuschased.setText("LOADING");
	}

	public static void noInformation() {
		panelIsPuschased.setBackground(SystemColor.DARK_GRAY);
		lblIsPuschased.setText("NO INFORMATION");
		clearSecurityInformation();
	}

	public static void purchased() {
		panelIsPuschased.setBackground(SystemColor.GREEN);
		lblIsPuschased.setText("PURCHASED");
	}

	public static void notPurchased() {
		panelIsPuschased.setBackground(SystemColor.RED);
		lblIsPuschased.setText("NOT PURCHASED");
	}

	public static void displaySecurityInformation(TagInfo information) {
		txtTagId.setText(information.getTagId());
		txtProductId.setText(information.getProductInstanceId());
		txtProductName.setText(information.getProductName());

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatedDateTime = information.getTime().format(formatter);
		txtTime.setText(formatedDateTime);

		txtAntenna.setText(information.getAntenna());
	}

	public static void clearSecurityInformation() {
		txtTagId.setText("");
		txtProductId.setText("");
		txtProductName.setText("");
		txtTime.setText("");
		txtAntenna.setText("");
	}

	public static void alert() {
		File soundFile = new File("src/assets/alarm_1.wav");
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void addToLog(String tagID, String tagInfo){
		try {
			int length = doc.getLength();
			doc.insertString(doc.getLength(), tagID + " - " + tagInfo + "\n" , null);
			doc.setParagraphAttributes(length + 1, 1, left, false);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
}
