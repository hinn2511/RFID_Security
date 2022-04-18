package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sqlHandler.TagInfo;
import sqlHandler.SqlQuery;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RfidTag extends JFrame {

	private JPanel contentPane;
	private JTextField txtTagId;

	/**
	 * Launch the application.
	 */
//	public static void MainApplication(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					RfidTag frame = new RfidTag();
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
	public RfidTag() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		txtTagId = new JTextField();
		txtTagId.setBounds(42, 75, 325, 38);
		panel.add(txtTagId);
		txtTagId.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Tag ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(42, 25, 133, 31);
		panel.add(lblNewLabel);
		
		JButton btnSwipe = new JButton("Swipe");
		btnSwipe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TagInfo information = new TagInfo();
				information = SqlQuery.isProductPurchased(txtTagId.getText());
				MainApplication.loading();
				
				if(information == null) {
					MainApplication.noInformation();
					MainApplication.addToLog(txtTagId.getText(), "No information");
					MainApplication.clearSecurityInformation();
				}
				else {
					if(information.isPurchased())
					{
						MainApplication.purchased();
						MainApplication.addToLog(txtTagId.getText(), "Purchased");
					}
					
					else {
						MainApplication.notPurchased();
						MainApplication.alert();
						MainApplication.addToLog(txtTagId.getText(), "Not purchased");
					}
					MainApplication.displaySecurityInformation(information);
					SqlQuery.updateTagRead(information);
				}
			}
		});
		btnSwipe.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSwipe.setBounds(42, 138, 325, 38);
		panel.add(btnSwipe);
	}

}
