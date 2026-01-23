package com.health.insurance.controller;

import com.health.insurance.DAO.InsuranceContractDAO;
import com.health.insurance.DAO.InsuredPersonDAO;
import com.health.insurance.DAOImpl.InsuranceContractDAOImpl;
import com.health.insurance.DAOImpl.InsuredPersonDAOImpl;
import com.health.insurance.Main;
import com.health.insurance.beans.InsuranceContract;
import com.health.insurance.beans.InsuredPerson;
import com.health.insurance.util.NavigationUtil;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.health.insurance.util.AlertUtil.showAlert;

public class BuyInsuranceController implements Initializable {

    @FXML
    JFXTextField personName;
    @FXML
    JFXTextField address;
    @FXML
    JFXTextField contactNumber;
    @FXML
    JFXTextField email;
    @FXML
    JFXComboBox<String> insurance;

    @FXML
    public void buyInsurance() {
        String pName = personName.getText();
        String pAddress = address.getText();
        String cNumber = contactNumber.getText();
        String type = insurance.getSelectionModel().getSelectedItem();

        InsuredPerson insuredPerson = new InsuredPerson(pName, type, cNumber,pAddress);
        InsuredPersonDAO insuredPersonDAO = new InsuredPersonDAOImpl();
        boolean isAdded = insuredPersonDAO.saveInsuredPerson(insuredPerson);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        InsuranceContractDAO insuranceContractDAO = new InsuranceContractDAOImpl();
        List<InsuranceContract> insuranceContracts = insuranceContractDAO.getInsuranceContracts();
        for(InsuranceContract insuranceContract: insuranceContracts) {
            insurance.getItems().add(insuranceContract.getId() + " " +insuranceContract.getName());
        }
    }

    public void gotoDashboard(){
        NavigationUtil.goTo(Main.primaryStage, "/fxml/Dashboard.fxml");
    }
}
