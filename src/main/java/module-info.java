module org.example.academymanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql; // if you use SQL, for example

    opens org.example.academymanagement.controller to javafx.fxml;
    exports org.example.academymanagement.controller; // Make sure this line is present
    exports org.example.academymanagement; // Add other packages if needed
}
