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
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        animateAnchorPanes();
    }


    public void animateAnchorPanes() {
        // Create TranslateTransition for the first AnchorPane
        TranslateTransition translate1 = new TranslateTransition(Duration.seconds(2), anchorPane1);
        translate1.setByY(-540); // Move up by 200 pixels

        // Create TranslateTransition for the second AnchorPane
        TranslateTransition translate2 = new TranslateTransition(Duration.seconds(2), anchorPane2);
        translate2.setByY(540); // Move down by 200 pixels

        // Create FadeTransitions to fade out the AnchorPanes after they move
        FadeTransition fade1 = new FadeTransition(Duration.seconds(1.5), anchorPane1);
        fade1.setFromValue(1.0);
        fade1.setToValue(0.0);

        FadeTransition fade2 = new FadeTransition(Duration.seconds(1.5), anchorPane2);
        fade2.setFromValue(1.0);
        fade2.setToValue(0.0);

        // Chain the translations and fades in sequence
        SequentialTransition sequence1 = new SequentialTransition(translate1, fade1);
        SequentialTransition sequence2 = new SequentialTransition(translate2, fade2);

        // Play the animations for both AnchorPanes together
        sequence1.setOnFinished(e -> anchorPane1.setVisible(false)); // Hide after animation
        sequence2.setOnFinished(e -> anchorPane2.setVisible(false)); // Hide after animation

        sequence1.play();
        sequence2.play();
    }


    public void btnLoginOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/academymanagement/student_form.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}