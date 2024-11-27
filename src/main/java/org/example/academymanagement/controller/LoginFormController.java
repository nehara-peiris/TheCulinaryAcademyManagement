package org.example.academymanagement.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.academymanagement.bo.BOFactory;
import org.example.academymanagement.bo.custom.UserBO;
import org.example.academymanagement.dto.UserDTO;
import org.example.academymanagement.util.BCryptHasher;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {

    @FXML
    private AnchorPane LoginMainNode;

    @FXML
    private AnchorPane anchorPane1;

    @FXML
    private AnchorPane anchorPane2;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private CheckBox showPw;

    @FXML
    private TextField txtUserName;

    @FXML
    private PasswordField txtpw;

    @FXML
    private TextField txtVisiblePassword;

    private boolean isPasswordVisible = false;


    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        animateAnchorPanes();
        initializePasswordToggle();
    }

    public void animateAnchorPanes() {
        TranslateTransition translate1 = new TranslateTransition(Duration.seconds(2), anchorPane1);
        translate1.setByY(-540); // Move up

        TranslateTransition translate2 = new TranslateTransition(Duration.seconds(2), anchorPane2);
        translate2.setByY(540); // Move down

        FadeTransition fade1 = new FadeTransition(Duration.seconds(1.5), anchorPane1);
        fade1.setFromValue(1.0);
        fade1.setToValue(0.0);

        FadeTransition fade2 = new FadeTransition(Duration.seconds(1.5), anchorPane2);
        fade2.setFromValue(1.0);
        fade2.setToValue(0.0);

        SequentialTransition sequence1 = new SequentialTransition(translate1, fade1);
        SequentialTransition sequence2 = new SequentialTransition(translate2, fade2);

        sequence1.setOnFinished(e -> anchorPane1.setVisible(false));
        sequence2.setOnFinished(e -> anchorPane2.setVisible(false));

        sequence1.play();
        sequence2.play();
    }

    private void initializePasswordToggle() {
        txtVisiblePassword.setVisible(false);

        showPw.setOnAction(event -> {
            if (isPasswordVisible) {
                txtpw.setText(txtVisiblePassword.getText());
                txtpw.setVisible(true);
                txtVisiblePassword.setVisible(false);
            } else {
                txtVisiblePassword.setText(txtpw.getText());
                txtVisiblePassword.setVisible(true);
                txtpw.setVisible(false);
            }
            isPasswordVisible = !isPasswordVisible;
        });
    }

    public void btnLoginOnAction(ActionEvent event) {
        try {
            String username = txtUserName.getText().trim();
            String password = isPasswordVisible ? txtVisiblePassword.getText().trim() : txtpw.getText().trim();

            if (username.isEmpty() || password.isEmpty()) {
                showAlert("Invalid Input", "Username or Password cannot be empty.", Alert.AlertType.WARNING);
                return;
            }

            List<UserDTO> users = userBO.getAllUsers();

            for (UserDTO user : users) {
                if (user.getUsername().equals(username) && BCryptHasher.verifyPassword(password, user.getPassword())) {
                    redirectToDashboard();
                    return;
                }
            }

            showAlert("Login Failed", "Invalid username or password.", Alert.AlertType.ERROR);

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred during login.", Alert.AlertType.ERROR);
        }
    }

    private void redirectToDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/academymanagement/dashboard_form.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load the dashboard.", Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
