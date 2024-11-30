package org.example.academymanagement.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert;
import org.example.academymanagement.bo.BOFactory;
import org.example.academymanagement.bo.custom.StudentProgramBO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class DashboardFormController {

    @FXML private AnchorPane rootNode;
    @FXML private AnchorPane DashLoadNode;

    @FXML
    private CategoryAxis axisProgramName;

    @FXML
    private NumberAxis axisStudentCount;

    @FXML
    private BarChart<String, Number> barchrtStudetsProgram;

    StudentProgramBO studentProgramBO = (StudentProgramBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENTPROGRAM);

    public void initialize() throws Exception {
        populateBarChart();
    }

    private void populateBarChart() throws Exception {
        barchrtStudetsProgram.getData().clear();

        Map<String, Integer> stuProgCount = studentProgramBO.getAllStudentProgramtoChart();
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        stuProgCount.forEach((type, count) -> {
            XYChart.Data<String, Number> data = new XYChart.Data<>(type, count);
            series.getData().add(data);
            data.nodeProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    newValue.setStyle("-fx-bar-fill: #1c7850;");
                }
            });
        });

        barchrtStudetsProgram.getData().add(series);
    }

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
    void btnCourseOnAction(ActionEvent event) {
        loadPane("/org/example/academymanagement/courses_form.fxml", DashLoadNode);
    }

    @FXML
    void btnStudentOnAction(ActionEvent event) {
        loadPane("/org/example/academymanagement/student_form.fxml", DashLoadNode);
    }

    @FXML
    void btnUserAccOnAction(ActionEvent event) {
        loadPane("/org/example/academymanagement/user_form.fxml", DashLoadNode);
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) {
        loadPane("/org/example/academymanagement/login_form.fxml", rootNode);
    }
}
