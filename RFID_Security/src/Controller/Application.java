package Controller;
import Model.SqlConnector;
import View.MainApplication;

public class Application {

	public static void main(String[] args) {
		
		//Connect to MySQL
		SqlConnector connector = new SqlConnector();
		connector.connect();
		
		//Start reading 
		ReadTags readTags = new ReadTags();
		
		//Show application
		MainApplication app = new MainApplication();
		app.setVisible(true);
		
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
	        public void run() {
	        	connector.close();
	        }
	    }, "Shutdown"));
	}

}
