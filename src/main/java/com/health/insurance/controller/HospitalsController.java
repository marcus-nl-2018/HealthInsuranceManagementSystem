package com.health.insurance.controller;

import com.health.insurance.DAO.HospitalDAO;
import com.health.insurance.DAOImpl.HospitalDAOImpl;
import com.health.insurance.Main;
import com.health.insurance.beans.Hospital;
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

public class HospitalsController implements Initializable {

    @FXML
    public TableView<Hospital> tableView;
    @FXML
    public TableColumn<Hospital, String> id;
    @FXML
    public TableColumn<Hospital, String> name;
    @FXML
    public TableColumn<Hospital, String> contact;
    @FXML
    public TableColumn<Hospital, String> email;
    @FXML
    public TableColumn<Hospital, String> website;
    @FXML
    public TableColumn<Hospital, String> address;

    private HospitalDAO hospitalDAO;

    @FXML
    public void addHospital() {
        NavigationUtil.goTo(Main.primaryStage, "/fxml/AddHospital.fxml");
    }

    @FXML
    public void removeHospital() {
        Hospital hospital = tableView.getSelectionModel().getSelectedItem();
        if(hospital != null) {
            boolean isRemoved = hospitalDAO.removeHospital(hospital);
            if(isRemoved) {
                showAlert(Alert.AlertType.CONFIRMATION, "Success!", "Record Saved Successfully");
                initialize(null, null);
            }else {
                showAlert(Alert.AlertType.ERROR, "Failure!", "Failed to Save Record");
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hospitalDAO = new HospitalDAOImpl();
        List<Hospital> hospitals = hospitalDAO.getHospitals();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory("name"));
        contact.setCellValueFactory(new PropertyValueFactory("contact"));
        email.setCellValueFactory(new PropertyValueFactory("email"));
        website.setCellValueFactory(new PropertyValueFactory<>("website"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        //Adding data to the table
        ObservableList<Hospital> list = FXCollections.observableList(hospitals);
        tableView.setItems(list);
    }

    @FXML
    public void back() {
        NavigationUtil.goTo(Main.primaryStage, "/fxml/Dashboard.fxml");
    }
}
