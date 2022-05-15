package Controller;

import com.impinj.octane.*;

import Model.SqlConnector;

import java.util.Scanner;


public class ReadTags {
	static ImpinjReader reader = new ImpinjReader();
	
	public static void startReading() {
		try {
			reader.start();
		} catch (OctaneSdkException e) {
			e.printStackTrace();
		}
	}
	
	public static void stopReading() {
		try {
			reader.stop();
		} catch (OctaneSdkException e) {
			e.printStackTrace();
		}
	}

    public ReadTags() {
    	try {
            String hostname = "192.168.1.1";            

            System.out.println("Connecting");
            reader.connect(hostname);

            Settings settings = reader.queryDefaultSettings();

            ReportConfig report = settings.getReport();
            report.setIncludeAntennaPortNumber(true);
            report.setMode(ReportMode.Individual);

            settings.setReaderMode(ReaderMode.AutoSetDenseReader);

            AntennaConfigGroup antennas = settings.getAntennas();
            antennas.disableAll();
            antennas.enableById(new short[]{1});
            antennas.getAntenna((short) 1).setIsMaxRxSensitivity(false);
            antennas.getAntenna((short) 1).setIsMaxTxPower(false);
            antennas.getAntenna((short) 1).setTxPowerinDbm(20.0);
            antennas.getAntenna((short) 1).setRxSensitivityinDbm(-70);

            reader.setTagReportListener(new TagReportListenerImplementation());
            reader.applySettings(settings);

         
        } catch (OctaneSdkException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace(System.out);
        }
	}
}
