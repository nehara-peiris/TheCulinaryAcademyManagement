package org.example.academymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentProgramDTO {
    private Long studentProgramId;
    private StudentDTO student;
    private ProgramDTO program;
    private String payOption;
    private double installmentFee;
    private double totalDue;
}
