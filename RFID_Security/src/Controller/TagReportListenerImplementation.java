package Controller;

import com.impinj.octane.ImpinjReader;
import com.impinj.octane.Tag;
import com.impinj.octane.TagReport;
import com.impinj.octane.TagReportListener;

import Model.SqlQuery;
import Model.Entities.CheckoutInfo;
import View.MainApplication;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class TagReportListenerImplementation implements TagReportListener {
	HashMap<String, Long> tagTimestamp = new HashMap<>();
	int delayTimeInMilis = 10000;
	static Clip clip;

	@Override
	public void onTagReported(ImpinjReader reader, TagReport report) {
		List<Tag> tags = report.getTags();

		for (Tag t : tags) {
			// Lay thong tin tag id
			String tagId = t.getEpc().toString();

			// Lay thoi diem hien tai
			long timestamp = System.currentTimeMillis();

			// Neu tag chua ton tai, them tag vao hashmap
			if (tagTimestamp.get(tagId) == null) {
				tagTimestamp.putIfAbsent(tagId, timestamp);
				checkTagId(tagId, t.getAntennaPortNumber() > 0 ? t.getAntennaPortNumber() : 1);
			}
			// Neu tag da ton tai, kiem tra tag
			else {
				if (timestamp - tagTimestamp.get(tagId) > delayTimeInMilis) {
					tagTimestamp.put(tagId, System.currentTimeMillis());
					checkTagId(tagId, t.getAntennaPortNumber() > 0 ? t.getAntennaPortNumber() : 1);
				}
			}
		}
	}

	void checkTagId(String tagId, int gateNumber) {
		CheckoutInfo information = new CheckoutInfo();
		information = SqlQuery.isProductPurchased(tagId);

		if (information != null) {
			information.setGateNumber(gateNumber);
			SqlQuery.addLog(information);
			if (!information.isPurchased())
				alert();
			MainApplication.addNewLineToCheckoutTable(information);
		}
	}

	public static void alert() {
		File soundFile = new File("src/assets/alarm_1.wav");
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			if(clip.isOpen()) {
				clip.close();
			}
			clip.open(audioIn);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
