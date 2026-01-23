package com.health.insurance.controller;

import com.health.insurance.DAO.PhysicianDAO;
import com.health.insurance.DAOImpl.PhysicianDAOImpl;
import com.health.insurance.Main;
import com.health.insurance.beans.Physician;
import com.health.insurance.util.NavigationUtil;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import static com.health.insurance.util.AlertUtil.showAlert;

public class AddPhysicianController {
    @FXML
    JFXTextField physicianName;
    @FXML
    JFXTextField address;
    @FXML
    JFXTextField contactNumber;
    @FXML
    JFXTextField email;

    @FXML
    public void addPhysician() {
        String name = physicianName.getText();
        String physicianAddress = address.getText();
        String number = contactNumber.getText();
        String emailAddress = email.getText();

        Physician physician = new Physician(name, number, emailAddress, physicianAddress);
        PhysicianDAO physicianDAO = new PhysicianDAOImpl();
        boolean isAdded = physicianDAO.savePhysician(physician);
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
