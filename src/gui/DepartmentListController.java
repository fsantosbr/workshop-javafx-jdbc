package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
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
	public void onBtNewAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Department obj = new Department();
		createDialogForm(obj, "/gui/DepartmentForm.fxml", parentStage);
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
	
	private void createDialogForm(Department obj, String absoluteName, Stage parentStage) {
		try {
			
			FXMLLoader  loader = new FXMLLoader(getClass().getResource(absoluteName)); // object to load a screen
			Pane pane = loader.load();
			
			DepartmentFormController controller = loader.getController();
			controller.setDepartment(obj);
			controller.setDepartmentService(new DepartmentService());
			controller.updateFormData();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Enter Department data");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false); // the window cannot be resizable
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
			
		}
		catch(IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}

}
