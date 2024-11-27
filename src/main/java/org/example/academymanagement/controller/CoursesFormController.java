package org.example.academymanagement.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.example.academymanagement.bo.BOFactory;
import org.example.academymanagement.bo.custom.ProgramBO;
import org.example.academymanagement.dto.ProgramDTO;

import java.util.List;

public class CoursesFormController {

    @FXML private TableColumn<ProgramDTO, String> colDuration;
    @FXML private TableColumn<ProgramDTO, String> colFee;
    @FXML private TableColumn<ProgramDTO, String> colId;
    @FXML private TableColumn<ProgramDTO, String> colPrName;
    @FXML private TableView<ProgramDTO> tblCourses;
    @FXML private TextField txtDuration;
    @FXML private TextField txtFee;
    @FXML private TextField txtID;
    @FXML private TextField txtName;

    ProgramBO programBO = (ProgramBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PROGRAM);

    public void initialize() {
        colId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProgramId()));
        colPrName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProgramName()));
        colDuration.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDuration()));
        colFee.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getFee())));

        tblCourses.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtID.setText(newValue.getProgramId());
                txtName.setText(newValue.getProgramName());
                txtDuration.setText(newValue.getDuration());
                txtFee.setText(String.valueOf(newValue.getFee()));
            }
        });

        loadProgramData();
    }

    public void loadProgramData() {
        try {
            List<ProgramDTO> programs = programBO.getAllPrograms();
            tblCourses.getItems().clear();
            tblCourses.getItems().addAll(programs);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Could not load program data.");
        }
    }

    private boolean saveOrUpdateProgram(String id, String name, String duration, double fee, boolean isUpdate) {
        try {
            ProgramDTO programDTO = new ProgramDTO(id, name, duration, fee);
            return isUpdate ? programBO.updateProgram(programDTO) : programBO.addProgram(programDTO);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Invalid input. Please check the details.");
            return false;
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String id = txtID.getText();
        String name = txtName.getText();
        String duration = txtDuration.getText();
        double fee;

        try {
            fee = Double.parseDouble(txtFee.getText());
        } catch (NumberFormatException e) {
            showAlert("Error", "Fee must be a valid number.");
            return;
        }

        boolean isAdded = saveOrUpdateProgram(id, name, duration, fee, false);
        if (isAdded) {
            showAlert("Success", "Program added successfully.");
            loadProgramData();
            clearFields();
        } else {
            showAlert("Error", "Could not add program.");
        }
    }

    @FXML
    void btnUpdateOnaAction(ActionEvent event) {
        String id = txtID.getText();
        String name = txtName.getText();
        String duration = txtDuration.getText();
        double fee;

        try {
            fee = Double.parseDouble(txtFee.getText());
        } catch (NumberFormatException e) {
            showAlert("Error", "Fee must be a valid number.");
            return;
        }

        boolean isUpdated = saveOrUpdateProgram(id, name, duration, fee, true);
        if (isUpdated) {
            showAlert("Success", "Program updated successfully.");
            loadProgramData();
            clearFields();
        } else {
            showAlert("Error", "Could not update program.");
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtID.getText();

        boolean isDeleted = programBO.deleteProgram(id);
        if (isDeleted) {
            showAlert("Success", "Program deleted successfully.");
            loadProgramData();
            clearFields();
        } else {
            showAlert("Error", "Could not delete program.");
        }
    }

    private void clearFields() {
        txtID.clear();
        txtName.clear();
        txtDuration.clear();
        txtFee.clear();
    }
}

