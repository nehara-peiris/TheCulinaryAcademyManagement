package org.example.academymanagement.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentDTO {
    private String studentId;
    private String studentNic;
    private String dob;
    private String fullName;
    private String address;
    private String email;
    private String phone;
    private LocalDate registrationDate;
    private LocalTime registrationTime;
}