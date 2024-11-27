package org.example.academymanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.academymanagement.entity.Program;
import org.example.academymanagement.entity.Student;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "student_program")
public class StudentProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_program_id")
    private Long studentProgramId;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "program_id", nullable = false)
    private Program program;

    @Column(name = "pay_option")
    private String payOption;

    @Column(name = "installment_fee")
    private double installmentFee;

    @Column(name = "total_due")
    private double totalDue;

    @Column(name = "pay_status")
    private String payStatus;
}