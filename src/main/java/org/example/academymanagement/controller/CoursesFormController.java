package org.example.academymanagement.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
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
        Validations();
        addTextChangeListener(txtName);
        addTextChangeListener(txtFee);

    }


    private void Validations() {
        txtID.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[C0-9]")) {
                event.consume();
            }
        });

    }

    private void addTextChangeListener(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {

            if (textField == txtName) {
                if (!newValue.isEmpty()) {
                    if (!Character.isUpperCase(newValue.charAt(0))) {
                        textField.setText(oldValue != null ? oldValue : "");
                    } else {
                        String correctedValue = Character.toUpperCase(newValue.charAt(0)) + newValue.substring(1);
                        if (!newValue.equals(correctedValue)) {
                            textField.setText(correctedValue);
                        }
                    }
                }
            }

            if (textField == txtFee && !newValue.matches("^-?\\d+(\\.\\d+)?$")) {
            }

        });
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


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void btnAddOnAction(ActionEvent event) throws Exception {
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

        boolean isAdded = programBO.addProgram(new ProgramDTO(id, name, duration, fee));
        if (isAdded) {
            showAlert("Success", "Program added successfully.");
            loadProgramData();
            clearFields();
        } else {
            showAlert("Error", "Could not add program.");
        }
    }

    @FXML
    void btnUpdateOnaAction(ActionEvent event) throws Exception {
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

        boolean isUpdated = programBO.updateProgram(new ProgramDTO(id, name, duration, fee));
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

