package tagReader;

import com.impinj.octane.ImpinjReader;
import com.impinj.octane.Tag;
import com.impinj.octane.TagReport;
import com.impinj.octane.TagReportListener;

import GUI.MainApplication;
import sqlHandler.TagInfo;
import sqlHandler.SqlQuery;

import java.awt.SystemColor;
import java.time.Instant;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class TagReportListenerImplementation implements TagReportListener {

	@Override
	public void onTagReported(ImpinjReader reader, TagReport report) {
		List<Tag> tags = report.getTags();

		for (Tag t : tags) {
			
			String tagId = t.getEpc().toString();
			TagInfo information = new TagInfo();
			information = SqlQuery.isProductPurchased(tagId);
			
			if(information == null) {
				MainApplication.noInformation();
				MainApplication.clearSecurityInformation();
			}
			else {
				if(information.isPurchased())
				{
					MainApplication.purchased();
				}
				
				else {
					MainApplication.notPurchased();
				}
				MainApplication.displaySecurityInformation(information);
				SqlQuery.updateTagRead(information);
			}
		}
	}
}
