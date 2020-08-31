package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

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
import model.services.DepartmentService;

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
		loadView("/gui/DepartmentList.fxml", (DepartmentListController controller) -> {
			controller.setDepartmentService(new DepartmentService());
			controller.updateTableView();
		});
		// Added a second argument to the method. A function (lambda).
		
	}
	
	
	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml", x -> {});
		// As the About screen doens't have anything to be done/shown, we put a function to return nothing in the loadView() arg
	}
	
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		// TODO Auto-generated method stub
		
	}
	
	// Method to call another screen. We'll pass a full path name of a file .fxml as an argument
	// now this is a generic function. function of type T
	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
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
			
			
			T controller = loader.getController();
			initializingAction.accept(controller);
			
		}
		catch(IOException e) {
			Alerts.showAlert("IO Exception", "Error loading View", e.getMessage(), AlertType.ERROR);
		}
	}

	
	
}
