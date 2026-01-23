package com.health.insurance.controller;

import com.health.insurance.DAO.InsuranceContractDAO;
import com.health.insurance.DAOImpl.InsuranceContractDAOImpl;
import com.health.insurance.Main;
import com.health.insurance.beans.InsuranceContract;
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


public class InsuranceContractController implements Initializable {
    @FXML
    public TableView<InsuranceContract> tableView;
    @FXML
    public TableColumn<InsuranceContract, String> id;
    @FXML
    public TableColumn<InsuranceContract, String> name;
    @FXML
    public TableColumn<InsuranceContract, String> term;
    @FXML
    public TableColumn<InsuranceContract, String> contractAmount;
    @FXML
    public TableColumn<InsuranceContract, String> faceAmount;
    @FXML
    public TableColumn<InsuranceContract, String> interest;

    private InsuranceContractDAO insuranceContractDAO;

    @FXML
    public void addContractOnAction() {
        NavigationUtil.goTo(Main.primaryStage, "/fxml/CreateContract.fxml");
    }
    @FXML
    public void removeContract() {
        InsuranceContract insuranceContract = tableView.getSelectionModel().getSelectedItem();
        if(insuranceContract != null) {
            boolean isRemoved = insuranceContractDAO.removeInsuranceContract(insuranceContract);
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
        insuranceContractDAO = new InsuranceContractDAOImpl();
        List<InsuranceContract> insuranceContracts = insuranceContractDAO.getInsuranceContracts();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory("name"));
        term.setCellValueFactory(new PropertyValueFactory("term"));
        contractAmount.setCellValueFactory(new PropertyValueFactory("contractAmount"));
        faceAmount.setCellValueFactory(new PropertyValueFactory<>("faceAmount"));
        interest.setCellValueFactory(new PropertyValueFactory<>("interest"));
        //Adding data to the table
        ObservableList<InsuranceContract> list = FXCollections.observableList(insuranceContracts);
        tableView.setItems(list);
    }

    @FXML
    public void back() {
        NavigationUtil.goTo(Main.primaryStage, "/fxml/Dashboard.fxml");
    }
}
