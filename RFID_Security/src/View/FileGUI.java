package View;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileGUI {
	static JFileChooser fileChooser = new JFileChooser();
	static FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel files", "xlsx");

	StringBuilder sb = new StringBuilder();
	static String path;

	public static String getPath() {
		return path;
	}

	public static void setPath(String path) {
		FileGUI.path = path;
	}

	public static void pickingFile() throws FileNotFoundException {
		fileChooser.setFileFilter(filter);
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();

			Scanner input = new Scanner(file);

			setPath(file.getAbsolutePath());

			input.close();
		}
	}


}
