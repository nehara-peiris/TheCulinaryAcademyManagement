package org.example.academymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDTO {
    private Long paymentId;
    private StudentProgramDTO studentProgram;
    private String paymentDescription;
    private Double amount;
    private String paymentDate;
    private String paymentTime;
    private UserDTO user;
    private StudentDTO student;
}
