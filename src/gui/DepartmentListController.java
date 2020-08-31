package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

//class to be the Controller of the elements from the DepartmentList screen (from DepartmentList.fxml)
public class DepartmentListController implements Initializable {

	
	private DepartmentService service;
	// We won't instantiate DepartmentService here because we wanna avoid the strong relation. We'll use a public set method
	
	@FXML
	private TableView<Department> tableViewDepartment;
	
	@FXML
	private TableColumn<Department, Integer> tableColumnId; // handling the id (an integer)
	
	@FXML
	private TableColumn<Department, String> tableColumnName; // handling the name (a string)
	
	@FXML
	private Button btNew;
	
	private ObservableList<Department> obsList; // we'll load the Departments in this.
	
	
	@FXML
	public void onBtNewAction() {
		System.out.println("onBtNewAction");
	}
	
	
	public void setDepartmentService(DepartmentService service) {
		this.service = service;
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
	
	// This method will access the service, load the Departments and pass them to the ObservableList<T>
	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Department> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewDepartment.setItems(obsList);
	}

}
