package org.example.academymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDTO {
    private Integer paymentId;
    private String paymentDescription;
    private Double amount;
    private LocalDate paymentDate;
    private LocalTime paymentTime;
}
