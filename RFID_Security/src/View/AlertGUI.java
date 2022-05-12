package View;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;

public class AlertGUI extends JFrame {
 
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static String title = "product is not purchased";
	private JTextField txtTagId;
	private JTextField txtProductLineId;
	private JTextField txtProductLineName;
	private JTextField txtTime;
	private JTextField txtGateNumber;
	
	public AlertGUI(String tagId, String productLineId, String productLineName, String time,
			String gateNumber) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 522, 480);
		setResizable(false);
		setTitle("Alert");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[grow]", "[][][][][][][][][][][][][][][]"));
		
		JLabel lblNewLabel = new JLabel(title.toUpperCase());
		lblNewLabel.setBackground(new Color(255, 0, 0));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBorder(BorderFactory.createEmptyBorder(20, 5, 20, 5));
		lblNewLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel, "cell 0 0, growx");
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		panel.add(verticalStrut_1, "cell 0 1");
		
		JLabel lblNewLabel_1 = new JLabel("Tag ID");
		lblNewLabel_1.setForeground(new Color(128, 128, 128));
		lblNewLabel_1.setFont(new Font("Malgun Gothic", Font.PLAIN, 18));
		panel.add(lblNewLabel_1, "cell 0 2");
		
		txtTagId = new JTextField();
		txtTagId.setEditable(false);
		txtTagId.setFont(new Font("Malgun Gothic", Font.PLAIN, 18));
		panel.add(txtTagId, "cell 0 3,growx");
		txtTagId.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Product line ID");
		lblNewLabel_2.setForeground(new Color(128, 128, 128));
		lblNewLabel_2.setFont(new Font("Malgun Gothic", Font.PLAIN, 18));
		panel.add(lblNewLabel_2, "cell 0 4");
		
		txtProductLineId = new JTextField();
		txtProductLineId.setEditable(false);
		txtProductLineId.setFont(new Font("Malgun Gothic", Font.PLAIN, 18));
		txtProductLineId.setColumns(10);
		panel.add(txtProductLineId, "cell 0 5,growx");
		
		JLabel lblNewLabel_1_1 = new JLabel("Product line name");
		lblNewLabel_1_1.setForeground(new Color(128, 128, 128));
		lblNewLabel_1_1.setFont(new Font("Malgun Gothic", Font.PLAIN, 18));
		panel.add(lblNewLabel_1_1, "cell 0 6");
		
		txtProductLineName = new JTextField();
		txtProductLineName.setEditable(false);
		txtProductLineName.setFont(new Font("Malgun Gothic", Font.PLAIN, 18));
		txtProductLineName.setColumns(10);
		panel.add(txtProductLineName, "cell 0 7,growx");
		
		JLabel lblNewLabel_1_2 = new JLabel("Time passed");
		lblNewLabel_1_2.setForeground(new Color(128, 128, 128));
		lblNewLabel_1_2.setFont(new Font("Malgun Gothic", Font.PLAIN, 18));
		panel.add(lblNewLabel_1_2, "cell 0 8");
		
		txtTime = new JTextField();
		txtTime.setEditable(false);
		txtTime.setFont(new Font("Malgun Gothic", Font.PLAIN, 18));
		txtTime.setColumns(10);
		panel.add(txtTime, "cell 0 9,growx");
		
		JLabel lblNewLabel_1_3 = new JLabel("Gate number");
		lblNewLabel_1_3.setForeground(new Color(128, 128, 128));
		lblNewLabel_1_3.setFont(new Font("Malgun Gothic", Font.PLAIN, 18));
		panel.add(lblNewLabel_1_3, "cell 0 10");
		
		txtGateNumber = new JTextField();
		txtGateNumber.setEditable(false);
		txtGateNumber.setFont(new Font("Malgun Gothic", Font.PLAIN, 18));
		txtGateNumber.setColumns(10);
		panel.add(txtGateNumber, "cell 0 11,growx");
		
		txtTagId.setText(tagId);
		txtProductLineId.setText(productLineId);
		txtProductLineName.setText(productLineName);
		txtTime.setText(time);
		txtGateNumber.setText(gateNumber);
	} 

}
