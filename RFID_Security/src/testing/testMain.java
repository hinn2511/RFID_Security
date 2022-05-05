package testing;
import gui.MainApplication;
import sqlHandler.SqlConnector;

public class testMain {

	public static void main(String[] args) {
		
		//Connect to MySQL
		SqlConnector connector = new SqlConnector();
		connector.connect();
		
		//Show application
		MainApplication app = new MainApplication();
		app.setVisible(true);
//		
		RfidTag rfidTag = new RfidTag();
		rfidTag.setVisible(true);
		
		
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
	        public void run() {
	        	connector.close();
	        }
	    }, "Shutdown"));
	}

}
