package org.example.academymanagement.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.example.academymanagement.bo.BOFactory;
import org.example.academymanagement.bo.custom.UserBO;
import org.example.academymanagement.dto.UserDTO;
import org.example.academymanagement.entity.User;



public class UserFormController {

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtConfirmPw;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmai;

    @FXML
    private TextField txtJobrole;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNewPw;

    @FXML
    private TextField txtOldPw;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUID;

    @FXML
    private TextField txtUsername;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    public void initialize() {
    }

    @FXML
    void btnDoneOnAction(ActionEvent event) {

    }

    private void setUserDetails() {
        UserDTO user = LoginFormController.getLoginUser();
        System.out.println("User: " + user);

        if (user == null) {
            System.out.println("User is null");
            return;
        }

        UserDTO user1 = new UserDTO();

        user1.setUserId(user.getUserId());


        UserDTO updatedUser=userBO.isUserExist(user1);

        txtUID.setText(updatedUser.getUserId());
        txtName.setText(updatedUser.getFullName());
        txtContact.setText(updatedUser.getPhoneNumber());
        txtEmai.setText(updatedUser.getEmail());
        txtAddress.setText(updatedUser.getAddress());

        txtUsername.setText(updatedUser.getUsername());

    }


}
