package com.health.insurance.controller;

import com.health.insurance.DAO.InsuranceContractDAO;
import com.health.insurance.DAOImpl.InsuranceContractDAOImpl;
import com.health.insurance.Main;
import com.health.insurance.beans.InsuranceContract;
import com.health.insurance.util.NavigationUtil;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static com.health.insurance.util.AlertUtil.showAlert;

public class CreateContractController implements Initializable {
    @FXML
    JFXTextField contractName;
    @FXML
    JFXComboBox<String> contractTerm;
    @FXML
    JFXTextField interest;
    @FXML
    JFXTextField contractAmount;
    @FXML
    JFXTextField faceAmount;

    @FXML
    public void saveContract() {
        String name = contractName.getText();
        String term = contractTerm.getSelectionModel().getSelectedItem();
        String interestPercent = interest.getText();
        String contractAmountText = contractAmount.getText();
        String faceAmountText= faceAmount.getText();
        InsuranceContract insuredContract = new InsuranceContract(name, term, contractAmountText, faceAmountText, interestPercent);
        InsuranceContractDAO insuranceContractDAO = new InsuranceContractDAOImpl();
        boolean isAdded = insuranceContractDAO.saveInsuranceContract(insuredContract);
        if(isAdded) {
            showAlert(Alert.AlertType.CONFIRMATION, "Success!", "Record Saved Successfully");
        }else {
            showAlert(Alert.AlertType.ERROR, "Failure!", "Failed to Save Record");
        }
    }

    @FXML
    public void cancel() {
        Stage stage = (Stage) contractName.getScene().getWindow();
        stage.close();
        gotoDashboard();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        contractTerm.getItems().addAll("1","2","3","4","5");
    }

    public void gotoDashboard(){
        NavigationUtil.goTo(Main.primaryStage, "/fxml/Dashboard.fxml");
    }
}
