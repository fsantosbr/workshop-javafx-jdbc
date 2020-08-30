package gui.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

// class to show alerts for some kind of messages (e.g. error messages, warning messages)
public class Alerts {
	
	public static void showAlert(String title, String header, String content, AlertType type) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.show();
	}
}