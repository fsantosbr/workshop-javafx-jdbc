package gui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;

public class DepartmentListController implements Initializable {

	@FXML
	private TableView<Department> tableViewDepartment;
	
	@FXML
	private TableColumn<Department, Integer> tableColumnId; // handling the id (an integer)
	
	@FXML
	private TableColumn<Department, String> tableColumnName; // handling the name (a string)
	
	@FXML
	private Button btNew;
	
	
	@FXML
	public void onBtNewAction() {
		System.out.println("onBtNewAction");
	}
	
	@Override
	public void initialize(URL aul, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		// The follow code will adjust the size of the table view screen to be the same as the all screens 
		Stage stage = (Stage) Main.getMainScene().getWindow(); // getting the reference of the scene
		tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());
	}

}
