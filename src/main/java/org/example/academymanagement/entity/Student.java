package org.example.academymanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "student_id")
    private String studentId;

    @Column(name = "student_nic", nullable = false)
    private String studentNic;

    @Column(name = "dob", nullable = false)
    private String dob;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @Column(name = "registration_time")
    private LocalTime registrationTime;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StudentProgram> studentPrograms;

    public Student(String studentId, String studentNic, String dob, String fullName, String address, String email, String phone, LocalDate registrationDate, LocalTime registrationTime) {
        this.studentId = studentId;
        this.studentNic = studentNic;
        this.dob = dob;
        this.fullName = fullName;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.registrationDate = registrationDate;
        this.registrationTime = registrationTime;
    }
}

