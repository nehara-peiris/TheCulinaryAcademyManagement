package org.example.academymanagement.controller;

import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.example.academymanagement.bo.BOFactory;
import org.example.academymanagement.bo.custom.PaymentBO;
import org.example.academymanagement.bo.custom.ProgramBO;
import org.example.academymanagement.bo.custom.StudentBO;
import org.example.academymanagement.bo.custom.StudentProgramBO;
import org.example.academymanagement.config.FactoryConfiguration;
import org.example.academymanagement.dto.PaymentDTO;
import org.example.academymanagement.dto.ProgramDTO;
import org.example.academymanagement.dto.StudentDTO;
import org.example.academymanagement.dto.StudentProgramDTO;
import org.example.academymanagement.entity.StudentProgram;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class StudentFormController {

    @FXML private AnchorPane MainNode;
    @FXML private ComboBox<String> cmbProgramId;
    @FXML private TableColumn<StudentDTO, String> colAddress;
    @FXML private TableColumn<StudentDTO, String> colContact;
    @FXML private TableColumn<StudentDTO, String> colDOB;
    @FXML private TableColumn<StudentDTO, String> colEmail;
    @FXML private TableColumn<StudentDTO, String> colID;
    @FXML private TableColumn<StudentDTO, String> colName;
    @FXML private TableColumn<StudentProgramDTO, String> colPID;
    @FXML private TableColumn<StudentProgramDTO, String> colPayId;
    @FXML private TableColumn<StudentProgramDTO, String> colSID;
    @FXML private TableColumn<StudentProgramDTO, String> colInstallment;
    @FXML private Label lblTotOfStudents;
    @FXML private Label lblTotOfStudents1;
    @FXML private AnchorPane stuNode;
    @FXML private AnchorPane stuRegNode;
    @FXML private AnchorPane EditNode;
    @FXML private TableView<StudentDTO> tblAllStudents;
    @FXML private TableView<StudentProgram> tblSearchedStudents;
    @FXML private TextField txtAddress;
    @FXML private TextField txtContact;
    @FXML private DatePicker txtDOB;
    @FXML private TextField txtEmail;
    @FXML private TextField txtFee;
    @FXML private TextField txtID;
    @FXML private TextField txtNIC;
    @FXML private TextField txtName;
    @FXML private TextField txtPaidAmout;
    @FXML private TextField txtRemaining;
    @FXML private TextField txtSearch;
    @FXML private TextField txtAddress1;
    @FXML private TextField txtContact1;
    @FXML private DatePicker txtDOB1;
    @FXML private TextField txtEmail1;
    @FXML private TextField txtFee1;
    @FXML private TextField txtID1;
    @FXML private TextField txtNIC1;
    @FXML private TextField txtName1;


    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);
    ProgramBO programBO = (ProgramBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PROGRAM);
    PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);
    StudentProgramBO studentProgramBO = (StudentProgramBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENTPROGRAM);

    private ObservableList<StudentProgram> studentProgramData;

    @FXML
    public void initialize() throws Exception {
        // Initialize the second pane off-screen to the right
        stuRegNode.setTranslateX(MainNode.getPrefWidth());

        colID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentId()));
        colName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFullName()));
        colAddress.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
        colContact.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone()));
        colEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        colDOB.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDob().toString()));


        colSID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudent().getStudentId()));
        colPID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProgram().getProgramId()));
        colPayId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPayID().toString()));
        colInstallment.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getInstallmentFee())));


        tblAllStudents.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Double-click to edit
                StudentDTO selectedStudent = tblAllStudents.getSelectionModel().getSelectedItem();
                if (selectedStudent != null) {
                    openEditNode(selectedStudent);
                }
            }
        });


        txtPaidAmout.textProperty().addListener((observable, oldValue, newValue) -> {
            calculateRemainingAmount();
        });

        loadAllStudents();
        loadStudentProgramData();
        loadProgramsIntoComboBox();

        Validations();
        addTextChangeListener(txtName);
        addTextChangeListener(txtName1);
        addTextChangeListener(txtAddress);
        addTextChangeListener(txtAddress1);
        addTextChangeListener(txtContact);
        addTextChangeListener(txtContact1);
        addTextChangeListener(txtEmail);
        addTextChangeListener(txtEmail1);
        addTextChangeListener(txtPaidAmout);
    }


    private void Validations() {
        txtID.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[S0-9]")) {
                event.consume();
            }
        });

        txtID1.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[0-9]")) {
                event.consume();
            }
        });

        txtNIC.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[C0-9]")) {
                event.consume();
            }
        });

        txtNIC1.addEventFilter(KeyEvent.KEY_TYPED, event -> {
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

            if (textField == txtName1) {
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

            if (textField == txtAddress) {
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

            if (textField == txtAddress1) {
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

            if (textField == txtContact) {
                if (!newValue.matches("\\d{0,10}")) {
                    textField.setText(oldValue != null ? oldValue : "");
                    new Alert(Alert.AlertType.ERROR, "Please enter 10 integer numbers.");
                }
            }

            if (textField == txtContact1) {
                if (!newValue.matches("\\d{0,10}")) {
                    textField.setText(oldValue != null ? oldValue : "");
                    new Alert(Alert.AlertType.ERROR, "Please enter 10 integer numbers.");
                }
            }

            if (textField == txtEmail && !newValue.matches("^([A-z])([A-z0-9.]){1,}[@]([A-z0-9]){1,10}[.]([A-z]){2,5}$")) {
            }

            if (textField == txtEmail1 && !newValue.matches("^([A-z])([A-z0-9.]){1,}[@]([A-z0-9]){1,10}[.]([A-z]){2,5}$")) {
            }

            if (textField == txtPaidAmout && !newValue.matches("^-?\\d+(\\.\\d+)?$")) {
            }

        });
    }


    private void calculateRemainingAmount() {
        try {
            // Parse the fee and paid amount values
            double fee = Double.parseDouble(txtFee.getText());
            double paidAmount = Double.parseDouble(txtPaidAmout.getText());

            // Calculate the remaining amount
            double remaining = fee - paidAmount;

            // Update the txtRemaining field with the calculated value
            txtRemaining.setText(String.valueOf(remaining));
        } catch (NumberFormatException e) {
            // Handle the case when the fields contain invalid values
            txtRemaining.clear();
        }
    }

    private void openEditNode(StudentDTO student) {
        // Populate fields in the EditNode with student data
        txtID1.setText(student.getStudentId());
        txtNIC1.setText(student.getStudentNic());
        txtDOB1.setValue(LocalDate.parse(student.getDob()));
        txtName1.setText(student.getFullName());
        txtAddress1.setText(student.getAddress());
        txtEmail1.setText(student.getEmail());
        txtContact1.setText(student.getPhone());


        TranslateTransition slideIn = new TranslateTransition(Duration.seconds(0.5), EditNode);
        slideIn.setToX(0);
        slideIn.play();

    }

    public void loadAllStudents() {
        try {
            List<StudentDTO> students = studentBO.getAllStudents();
            tblAllStudents.getItems().clear();
            tblAllStudents.getItems().addAll(students);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Could not load program data.");
        }
    }

    private void loadStudentProgramData() throws Exception {
        try {
            List<StudentProgram> studentPrograms = studentProgramBO.getAllStudentPrograms();
            tblSearchedStudents.getItems().clear();
            tblSearchedStudents.getItems().addAll(studentPrograms);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Could not load program data.");
        }
    }

    @FXML
    private void loadProgramsIntoComboBox() {
        try {
            List<ProgramDTO> programs = programBO.getAllPrograms();
            cmbProgramId.getItems().clear();
            for (ProgramDTO program : programs) {
                cmbProgramId.getItems().add(program.getProgramId());
            }

            // Add a listener to update the fee when a program is selected
            cmbProgramId.setOnAction(event -> {
                String selectedProgramId = cmbProgramId.getValue();
                if (selectedProgramId != null) {
                    ProgramDTO selectedProgram = programBO.getProgramById(selectedProgramId);
                    if (selectedProgram != null) {
                        txtFee.setText(String.valueOf(selectedProgram.getFee()));
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Could not load program data.");
        }
    }


    @FXML
    void btnRegOnAction(ActionEvent event) {
        TranslateTransition slideIn = new TranslateTransition(Duration.seconds(0.5), stuRegNode);
        slideIn.setToX(0); // Move it to fully cover the basePane
        slideIn.play();
    }

    @FXML
    void btnBackOnAtion(ActionEvent event) {
        TranslateTransition slideOut = new TranslateTransition(Duration.seconds(0.5), stuRegNode);
        slideOut.setToX(MainNode.getPrefWidth()); // Move it off-screen to the right
        slideOut.play();
    }

    @FXML
    void btnRegisterOnAction(ActionEvent event){
        String id = txtID.getText();
        String nic = txtNIC.getText();
        String dob = txtDOB.getValue() != null ? txtDOB.getValue().toString() : null;
        String name = txtName.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String phone = txtContact.getText();
        LocalDate regDate = LocalDate.now();
        LocalTime regTime = LocalTime.now();
        String programId = cmbProgramId.getValue();
        double paidAmount = Double.parseDouble(txtPaidAmout.getText());
        double remaining = Double.parseDouble(txtRemaining.getText());


        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            try {

                Integer paymentId = Integer.valueOf(generateNextPaymentId());
                if (paymentId == null) {
                    throw new Exception("Failed to generate payment ID.");
                }

                StudentDTO studentDTO = new StudentDTO(id, nic, dob, name, address, email, phone, regDate, regTime);

                ProgramDTO programID = new ProgramDTO(programId);

                StudentProgramDTO studentProgramDTO = new StudentProgramDTO(studentDTO, programID, paymentId, paidAmount, remaining);

                PaymentDTO paymentDTO = new PaymentDTO(paymentId, "Program Registration Payment", paidAmount, regDate, regTime);

                // Step 2: Perform operations
                boolean isStudentAdded = studentBO.saveStudent(studentDTO);
                if (!isStudentAdded) {
                    throw new Exception("Failed to add the student.");
                }

                boolean isStudentProgramUpdated = studentProgramBO.saveStudentProgram(studentProgramDTO);
                if (!isStudentProgramUpdated) {
                    throw new Exception("Failed to update the student program.");
                }

                boolean isPaymentAdded = paymentBO.addPayment(paymentDTO);
                if (!isPaymentAdded) {
                    throw new Exception("Failed to add the payment.");
                }

                transaction.commit();
                showAlert("Success", "Student registered successfully.");
                clearFields();

            } catch (Exception e) {
                // Rollback transaction if any step fails
                transaction.rollback();
                e.printStackTrace();
                showAlert("Error", "Failed to register student. " + e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "An unexpected error occurred.");
        }
    }



    private Integer generateNextPaymentId() {
        Integer lastPaymentId = paymentBO.getLastPaymentId(); // Fetch the last ID from your database

        if (lastPaymentId != null) {
            Integer lastId = lastPaymentId;
            return lastId + 1;
        } else {
            return 1;
        }
    }


    @FXML
    void btnupdateOnAction(ActionEvent event) throws Exception {
        String id = txtID1.getText();
        String nic = txtNIC1.getText();
        String dob = txtDOB1.getValue() != null ? txtDOB1.getValue().toString() : null;
        String name = txtName1.getText();
        String address = txtAddress1.getText();
        String email = txtEmail1.getText();
        String phone = txtContact1.getText();


        boolean isUpdated = studentBO.updateStudent(new StudentDTO(id, nic, dob, name, address, email, phone));
        if (isUpdated) {
            showAlert("Success", "Program updated successfully.");
            loadAllStudents();
            clearFields();
        } else {
            showAlert("Error", "Could not update program.");
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtID1.getText();

        boolean isDeleted = studentBO.deleteStudent(id);
        if (isDeleted) {
            showAlert("Success", "Student deleted successfully.");
            loadAllStudents();
            clearFields();
        } else {
            showAlert("Error", "Could not delete student.");
        }
    }


    @FXML
    void btnCancelOnAction(ActionEvent event) {
        TranslateTransition slideOut = new TranslateTransition(Duration.seconds(0.5), EditNode);
        slideOut.setToX(MainNode.getPrefWidth()); // Move it off-screen to the right
        slideOut.play();
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields(TextField... fields) {
        for (TextField field : fields) {
            field.clear();
        }
    }

    @FXML
    void txtSortbyOnAction(MouseEvent event) {

    }

}
