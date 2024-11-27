package org.example.academymanagement.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentRegFormController implements Initializable {

    @FXML
    private Label lblTotOfStudents;

    @FXML
    private AnchorPane stuRegNode;

    @FXML
    private TextField txtContact;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtProgram;

    @FXML
    private TextField txtSem;

    @FXML
    void btnRegisterOnAction(ActionEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}
