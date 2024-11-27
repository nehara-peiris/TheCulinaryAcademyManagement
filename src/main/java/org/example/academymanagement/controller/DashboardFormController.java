package org.example.academymanagement.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert;

import java.io.IOException;

public class DashboardFormController {

    @FXML private AnchorPane rootNode;
    @FXML private AnchorPane DashLoadNode;

    // Consolidated method to load and set any AnchorPane into the root or DashLoadNode
    private void loadPane(String fxmlPath, AnchorPane targetNode) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            AnchorPane pane = loader.load();

            targetNode.getChildren().clear();
            targetNode.getChildren().add(pane);

            AnchorPane.setTopAnchor(pane, 0.0);
            AnchorPane.setRightAnchor(pane, 0.0);
            AnchorPane.setBottomAnchor(pane, 0.0);
            AnchorPane.setLeftAnchor(pane, 0.0);

        } catch (IOException e) {
            e.printStackTrace();
            showErrorAlert("Error", "Failed to load the requested page.");
        }
    }

    // Helper method to show alerts
    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void btnDashOnAction(ActionEvent event) {
        loadPane("/org/example/academymanagement/dashboard_form.fxml", rootNode);
    }

    @FXML
    void btnRegOnAction(ActionEvent event) {
        loadPane("/org/example/academymanagement/student_reg_form.fxml", DashLoadNode);
    }

    @FXML
    void btnCourseOnAction(ActionEvent event) {
        loadPane("/org/example/academymanagement/courses_form.fxml", DashLoadNode);
    }

    @FXML
    void btnStudentOnAction(ActionEvent event) {
        loadPane("/org/example/academymanagement/student_form.fxml", DashLoadNode);
    }

    @FXML
    void btnStuCoursesOnAction(ActionEvent event) {
        // Implement functionality or remove if unnecessary
    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) {
        // Implement functionality or remove if unnecessary
    }

    @FXML
    void btnUserAccOnAction(ActionEvent event) {
        // Implement functionality or remove if unnecessary
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) {
        loadPane("/org/example/academymanagement/login_form.fxml", rootNode);
    }
}
