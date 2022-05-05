package tagReader;

import com.impinj.octane.ImpinjReader;
import com.impinj.octane.Tag;
import com.impinj.octane.TagReport;
import com.impinj.octane.TagReportListener;

import gui.MainApplication;
import sqlHandler.SqlQuery;
import sqlHandler.model.CheckoutInfo;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class TagReportListenerImplementation implements TagReportListener {
	HashMap<String, Long> tagTimestamp = new HashMap<>();
	int delayTimeInMilis = 10000;

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
				checkTagId(tagId, t.getAntennaPortNumber() > 0 ? t.getAntennaPortNumber() : 0);
			}
			// Neu tag da ton tai, kiem tra tag
			else {
				if (timestamp - tagTimestamp.get(tagId) > delayTimeInMilis) {
					tagTimestamp.put(tagId, System.currentTimeMillis());
					checkTagId(tagId, t.getAntennaPortNumber() > 0 ? t.getAntennaPortNumber() : 0);
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

	void alert() {
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
