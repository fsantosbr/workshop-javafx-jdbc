package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

// class to be the Controller of the elements from screen (from MainView.fxml)
public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItemSeller;
	
	@FXML
	private MenuItem menuItemDepartment;
	
	@FXML
	private MenuItem menuItemAbout;
	
	
	
	@FXML
	public void onMenuItemSellerAction() {
	
		System.out.println("onMenuItemSellerAction");
	}
	
	
	@FXML
	public void onMenuItemDepartmentAction() {
		loadView("/gui/DepartmentList.fxml");
	}
	
	
	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml");
	}
	
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		// TODO Auto-generated method stub
		
	}
	
	// Method to call another screen. We'll pass a full path name of a file .fxml as an argument
	private synchronized void loadView(String absoluteName) {
		// synchronized will guarantee that the screen change will occur and not stopped.
		try {
			FXMLLoader  loader = new FXMLLoader(getClass().getResource(absoluteName)); // object to load a screen
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene(); // calling an scene from the Main class
			
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			// mainScene.getRoot() will get the first element from the MainView.fxml, The ScrollPane. 
			// Then, we make a casting to ScrollPane class.
			// .getContent() will get the content of the ScrollPane. It's already a reference to what it's inside of the ScrollPane, the VBox.
			// Then, we make a casting to VBox
			
			Node mainMenu = mainVBox.getChildren().get(0);
			// we must keep the menus. So, we'll store a reference of it.	
			// Getting the first children in the VBox.
			
			mainVBox.getChildren().clear(); // Clearing all children of mainVBox
			
			// now we're adding all the elements cause we want to keep the menu across all screens
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
		}
		catch(IOException e) {
			Alerts.showAlert("IO Exception", "Error loading View", e.getMessage(), AlertType.ERROR);
		}
	}

	
	
}
