package org.example.academymanagement.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colDateofreg;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colProgram;

    @FXML
    private TableColumn<?, ?> colSContact;

    @FXML
    private TableColumn<?, ?> colSEmail;

    @FXML
    private TableColumn<?, ?> colSID;

    @FXML
    private TableColumn<?, ?> colSName;

    @FXML
    private TableColumn<?, ?> colSemester;

    @FXML
    private Label lblTotOfStudents;

    @FXML
    private TableView<?> tblAllStudents;

    @FXML
    private TableView<?> tblSearchedStudents;

    @FXML
    private AnchorPane stuNode;

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void txtSortbyOnAction(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}
