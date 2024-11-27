module org.example.academymanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.jfoenix;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires jbcrypt;
    requires static lombok;

    opens org.example.academymanagement.controller to javafx.fxml;
    opens org.example.academymanagement.entity;
    exports org.example.academymanagement.controller;
    exports org.example.academymanagement;



}