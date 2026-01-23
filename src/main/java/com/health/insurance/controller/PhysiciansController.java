package com.health.insurance.controller;

import com.health.insurance.DAO.PhysicianDAO;
import com.health.insurance.DAOImpl.PhysicianDAOImpl;
import com.health.insurance.Main;
import com.health.insurance.beans.Physician;
import com.health.insurance.util.NavigationUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.health.insurance.util.AlertUtil.showAlert;


public class PhysiciansController implements Initializable {

    @FXML
    public TableView<Physician> tableView;
    @FXML
    public TableColumn<Physician, String> id;
    @FXML
    public TableColumn<Physician, String> name;
    @FXML
    public TableColumn<Physician, String> contact;
    @FXML
    public TableColumn<Physician, String> email;
    @FXML
    public TableColumn<Physician, String> address;

    private PhysicianDAO physicianDAO;

    @FXML
    public void addPhysician() {
        NavigationUtil.goTo(Main.primaryStage, "/fxml/AddPhysicians.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        physicianDAO = new PhysicianDAOImpl();
        List<Physician> physicians = physicianDAO.getPhysicians();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory("name"));
        contact.setCellValueFactory(new PropertyValueFactory("contact"));
        email.setCellValueFactory(new PropertyValueFactory("email"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        //Adding data to the table
        ObservableList<Physician> list = FXCollections.observableList(physicians);
        tableView.setItems(list);
    }

    @FXML
    public void back() {
        NavigationUtil.goTo(Main.primaryStage, "/fxml/Dashboard.fxml");
    }

    @FXML
    public void removePhysician(){
        Physician physician = tableView.getSelectionModel().getSelectedItem();
        if(physician != null) {
            boolean isRemoved = physicianDAO.removePhysician(physician);
            if(isRemoved) {
                showAlert(Alert.AlertType.CONFIRMATION, "Success!", "Record Saved Successfully");
                initialize(null, null);
            }else {
                showAlert(Alert.AlertType.ERROR, "Failure!", "Failed to Save Record");
            }
        }
    }
}
