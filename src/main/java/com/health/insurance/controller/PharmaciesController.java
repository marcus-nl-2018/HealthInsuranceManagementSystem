package com.health.insurance.controller;

import com.health.insurance.DAO.PharmacyDAO;
import com.health.insurance.DAOImpl.PharmacyDAOImpl;
import com.health.insurance.Main;
import com.health.insurance.beans.Pharmacy;
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

public class PharmaciesController implements Initializable {

    @FXML
    public TableView<Pharmacy> tableView;
    @FXML
    public TableColumn<Pharmacy, String> id;
    @FXML
    public TableColumn<Pharmacy, String> name;
    @FXML
    public TableColumn<Pharmacy, String> contact;
    @FXML
    public TableColumn<Pharmacy, String> email;
    @FXML
    public TableColumn<Pharmacy, String> website;
    @FXML
    public TableColumn<Pharmacy, String> address;

    private PharmacyDAO pharmacyDAO;

    @FXML
    public void addPharmacy() {
        NavigationUtil.goTo(Main.primaryStage, "/fxml/AddPharmacy.fxml");
    }

    @FXML
    public void removePharmacy() {
        Pharmacy pharmacy = tableView.getSelectionModel().getSelectedItem();
        if(pharmacy != null) {
            boolean isRemoved = pharmacyDAO.removePharmacy(pharmacy);
            if(isRemoved) {
                showAlert(Alert.AlertType.CONFIRMATION, "Success!", "Record Saved Successfully");
                initialize(null, null);
            }else {
                showAlert(Alert.AlertType.ERROR, "Failure!", "Failed to Save Record");
            }
        }
    }

    @FXML
    public void back() {
        NavigationUtil.goTo(Main.primaryStage, "/fxml/Dashboard.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pharmacyDAO = new PharmacyDAOImpl();
        List<Pharmacy> pharmacies = pharmacyDAO.getPharmacies();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory("name"));
        contact.setCellValueFactory(new PropertyValueFactory("contact"));
        email.setCellValueFactory(new PropertyValueFactory("email"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        website.setCellValueFactory(new PropertyValueFactory<>("website"));
        //Adding data to the table
        ObservableList<Pharmacy> list = FXCollections.observableList(pharmacies);
        tableView.setItems(list);
    }
}
