package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Controller.Controller;

import Model.Entities.ProductLine;
import Model.Entities.Tag;
import net.miginfocom.swing.MigLayout;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JComboBox;

public class UpdateTagGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtTagId;
	private ArrayList<ProductLine> productLines = new ArrayList<>();

	public UpdateTagGUI(String tagId) {
		setBackground(new Color(248, 248, 255));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 522, 450);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(248, 248, 255));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[grow]", "[][][][][][][][][][][][]"));
		
		Component verticalStrut = Box.createVerticalStrut(20);
		panel.add(verticalStrut, "cell 0 0");
		
		JLabel lblNewLabel = new JLabel("Edit tag");
		lblNewLabel.setFont(new Font("Malgun Gothic", Font.PLAIN, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblNewLabel, "cell 0 1, growx");
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		panel.add(verticalStrut_1, "cell 0 2");
		
		JLabel lblNewLabel_1 = new JLabel("Tag\r\n ID");
		lblNewLabel_1.setFont(new Font("Malgun Gothic", Font.PLAIN, 18));
		panel.add(lblNewLabel_1, "cell 0 3");
		
		txtTagId = new JTextField();
		txtTagId.setBackground(new Color(240, 248, 255));
		txtTagId.setEditable(false);
		txtTagId.setText(tagId);
		txtTagId.setFont(new Font("Malgun Gothic", Font.PLAIN, 19));
		panel.add(txtTagId, "cell 0 4,growx");
		txtTagId.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Product");
		lblNewLabel_2.setFont(new Font("Malgun Gothic", Font.PLAIN, 18));
		panel.add(lblNewLabel_2, "cell 0 5");
		
		
		productLines = Controller.getProductLines();
		String[] array = new String[productLines.size()];
		for(int i = 0; i < array.length; i++) {
		    array[i] = productLines.get(i).getProductLineName();
		}
		
		JComboBox<Object> cbxProductLine = new JComboBox<Object>(array);
		cbxProductLine.setBackground(new Color(240, 248, 255));
		cbxProductLine.setFont(new Font("Malgun Gothic", Font.PLAIN, 18));
		panel.add(cbxProductLine, "cell 0 6,growx");
		
		JLabel lblNewLabel_2_1 = new JLabel("Status");
		lblNewLabel_2_1.setFont(new Font("Malgun Gothic", Font.PLAIN, 18));
		panel.add(lblNewLabel_2_1, "cell 0 7");
		
		JComboBox<String> cbxStatus = new JComboBox();
		cbxStatus.setBackground(new Color(240, 248, 255));
		cbxStatus.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Purchased", "Not purchased"}));
		cbxStatus.setFont(new Font("Malgun Gothic", Font.PLAIN, 18));
		panel.add(cbxStatus, "cell 0 8,growx");
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		panel.add(verticalStrut_2, "cell 0 9");
		
		JButton btnDone = new JButton("Done");
		btnDone.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Tag tag = new Tag(tagId, productLines.get(cbxProductLine.getSelectedIndex()).getProductLineID(),
										cbxStatus.getSelectedItem().toString().equals("Purchased") ? true : false);
				Boolean result = Controller.updateTag(tag);
				if(result) {
					ApplicationGUI.alert("Update successfully");
					ApplicationGUI.updateTag();
				}
				else
					ApplicationGUI.alert("Update failed");
				dispose();
			}
		});
		btnDone.setFont(new Font("Malgun Gothic", Font.PLAIN, 16));
		panel.add(btnDone, "cell 0 10, growx");
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnCancel.setFont(new Font("Malgun Gothic", Font.PLAIN, 14));
		panel.add(btnCancel, "cell 0 11, growx");
	}

}
