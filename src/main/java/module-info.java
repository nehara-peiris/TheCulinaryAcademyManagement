module org.example.academymanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.jfoenix;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;


    opens org.example.academymanagement.controller to javafx.fxml;
    exports org.example.academymanagement.controller;
    exports org.example.academymanagement;
}
