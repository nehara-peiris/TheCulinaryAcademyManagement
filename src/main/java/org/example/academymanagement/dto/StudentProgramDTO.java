package org.example.academymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentProgramDTO {
    private StudentDTO student;
    private ProgramDTO program;
    private Integer payID;
    private double installmentFee;
    private double totalDue;
}
