package Controller;

import Model.SqlConnector;
import View.ApplicationGUI;

public class Startup {
	static ApplicationGUI applicationGui;

	public static void main(String[] args) {
		
		//Connect to MySQL
		SqlConnector connector = new SqlConnector();
		connector.connect();
		
		applicationGui = new ApplicationGUI();
		applicationGui.setVisible(true);
		
		
		ReadTags readTags = new ReadTags();
		
		
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
	        public void run() {
	        	connector.close();
	        }
	    }, "Shutdown"));
	}

}
