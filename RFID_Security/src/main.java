import GUI.MainApplication;
import GUI.RfidTag;
import sqlHandler.SqlConnector;
import tagReader.ReadTagsFromReader;

public class main {

	public static void main(String[] args) {
		
		//Connect to MySQL
		SqlConnector connector = new SqlConnector();
		connector.connect();
		
		//Start reading 
		//ReadTagsFromReader readTagsFromReader = new ReadTagsFromReader();
		
		//Show application
		MainApplication app = new MainApplication();
		app.setVisible(true);
		
		//Stimulating rfid tag swipe
		RfidTag tag = new RfidTag();
		tag.setVisible(true);
		
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
	        public void run() {
	        	connector.close();
	        }
	    }, "Shutdown"));
	}

}
