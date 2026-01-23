package com.health.insurance.controller;

import com.health.insurance.Main;
import com.health.insurance.util.NavigationUtil;
import javafx.fxml.FXML;

public class DashboardController {

    @FXML
    public void formContractOnClick() {
        NavigationUtil.goTo(Main.primaryStage, "/fxml/InsuranceContract.fxml");
    }

    @FXML
    public void hospitalOnClick() {
        NavigationUtil.goTo(Main.primaryStage, "/fxml/Hospitals.fxml");
    }

    @FXML
    public void pharmaciesOnClick() {
        NavigationUtil.goTo(Main.primaryStage, "/fxml/Pharmacies.fxml");
    }

    @FXML
    public void physicianOnClick() {
        NavigationUtil.goTo(Main.primaryStage, "/fxml/Physicians.fxml");
    }

    @FXML
    public void buyInsurance() {
        NavigationUtil.goTo(Main.primaryStage, "/fxml/InsuredPersons.fxml");
    }
}
