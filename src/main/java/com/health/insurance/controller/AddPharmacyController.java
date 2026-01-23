package com.health.insurance.controller;

import com.health.insurance.DAO.PharmacyDAO;
import com.health.insurance.DAOImpl.PharmacyDAOImpl;
import com.health.insurance.Main;
import com.health.insurance.beans.Pharmacy;
import com.health.insurance.util.NavigationUtil;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import static com.health.insurance.util.AlertUtil.showAlert;

public class AddPharmacyController {

    @FXML
    JFXTextField pharmacyName;
    @FXML
    JFXTextField address;
    @FXML
    JFXTextField contactNumber;
    @FXML
    JFXTextField email;
    @FXML
    JFXTextField website;

    @FXML
    public void addPharmacy() {
        String name = pharmacyName.getText();
        String pharmacyAddress = address.getText();
        String number = contactNumber.getText();
        String emailAddress = email.getText();
        String websiteUrl = website.getText();
        Pharmacy pharmacy = new Pharmacy(name, number, emailAddress, websiteUrl,pharmacyAddress);
        PharmacyDAO pharmacyDAO = new PharmacyDAOImpl();
        boolean isAdded = pharmacyDAO.savePharmacy(pharmacy);
        if(isAdded) {
            showAlert(Alert.AlertType.CONFIRMATION, "Success!", "Record Saved Successfully");
            gotoDashboard();
        }else {
            showAlert(Alert.AlertType.ERROR, "Failure!", "Failed to Save Record");
        }
    }

    @FXML
    public void cancel() {
        Stage stage = (Stage) email.getScene().getWindow();
        stage.close();
        gotoDashboard();
    }

    public void gotoDashboard(){
        NavigationUtil.goTo(Main.primaryStage, "/fxml/Dashboard.fxml");
    }

}
