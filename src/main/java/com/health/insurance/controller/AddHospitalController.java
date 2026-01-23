package com.health.insurance.controller;

import com.health.insurance.DAO.HospitalDAO;
import com.health.insurance.DAOImpl.HospitalDAOImpl;
import com.health.insurance.Main;
import com.health.insurance.beans.Hospital;
import com.health.insurance.util.NavigationUtil;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import static com.health.insurance.util.AlertUtil.showAlert;

public class AddHospitalController {

    @FXML
    JFXTextField hospitalName;
    @FXML
    JFXTextField address;
    @FXML
    JFXTextField contactNumber;
    @FXML
    JFXTextField email;
    @FXML
    JFXTextField website;

    @FXML
    public void addHospital() {
        String name = hospitalName.getText();
        String hospitalAddress = address.getText();
        String number = contactNumber.getText();
        String emailAddress = email.getText();
        String websiteUrl = website.getText();
        Hospital hospital = new Hospital(name, number, emailAddress, websiteUrl,hospitalAddress);
        HospitalDAO hospitalDAO = new HospitalDAOImpl();
        boolean isAdded = hospitalDAO.saveHospital(hospital);
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
