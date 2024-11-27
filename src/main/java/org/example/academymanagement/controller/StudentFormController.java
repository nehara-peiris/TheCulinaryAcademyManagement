package org.example.academymanagement.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.example.academymanagement.dto.StudentDTO;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentFormController implements Initializable {

    @FXML
    private TableColumn<StudentDTO, String> colAddress;

    @FXML
    private TableColumn<StudentDTO, String> colContact;

    @FXML
    private TableColumn<StudentDTO, String> colDOB;

    @FXML
    private TableColumn<StudentDTO, String> colEmail;

    @FXML
    private TableColumn<StudentDTO, String> colGender;

    @FXML
    private TableColumn<StudentDTO, String> colID;

    @FXML
    private TableColumn<StudentDTO, String> colName;

    @FXML
    private TableColumn<StudentDTO, String> colSContact;

    @FXML
    private TableColumn<StudentDTO, String> colSEmail;

    @FXML
    private TableColumn<StudentDTO, String> colSID;

    @FXML
    private TableColumn<StudentDTO, String> colSName;

    @FXML
    private Label lblTotOfStudents;

    @FXML
    private AnchorPane stuNode;

    @FXML
    private TableView<StudentDTO> tblAllStudents;

    @FXML
    private TableView<StudentDTO> tblSearchedStudents;

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
