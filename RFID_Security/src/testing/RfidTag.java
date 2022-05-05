package testing;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.MainApplication;
import sqlHandler.SqlQuery;
import sqlHandler.model.CheckoutInfo;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.HashMap;

public class RfidTag extends JFrame {
	private static final long serialVersionUID = 1L;
	HashMap<String, Long> tagTimestamp = new HashMap<>();
	int delayTimeInMilis = 10000;

	private JPanel contentPane;
	private JTextField txtTagId;
	
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
				String tagId = txtTagId.getText();

				long timestamp = System.currentTimeMillis();

				if (tagTimestamp.get(tagId) == null) {
					tagTimestamp.put(tagId, timestamp);
					checkTagId(tagId, 1);
				} else {
					if (timestamp - tagTimestamp.get(tagId) > delayTimeInMilis) {
						tagTimestamp.put(tagId, System.currentTimeMillis());
						checkTagId(tagId, 1);
					}
				}

			}
		});
		btnSwipe.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSwipe.setBounds(42, 138, 325, 38);
		panel.add(btnSwipe);
	}

	void checkTagId(String tagId, int gateNumber) {
			CheckoutInfo information = new CheckoutInfo();
			information = SqlQuery.isProductPurchased(tagId);

			if (information != null) {
				if (!information.isPurchased())
					alert();
				information.setGateNumber(gateNumber);
				MainApplication.addNewLineToCheckoutTable(information);
				SqlQuery.addLog(information);
			}
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

}
