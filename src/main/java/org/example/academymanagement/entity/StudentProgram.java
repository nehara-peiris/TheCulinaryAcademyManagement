package org.example.academymanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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

    @Column(name = "pay_id")
    private Integer payID;

    @Column(name = "installment_fee")
    private double installmentFee;

    @Column(name = "total_due")
    private double totalDue;

    public StudentProgram(Student student, Program program, Integer payID, double installmentFee, double totalDue) {
        this.student = student;
        this.program = program;
        this.payID = payID;
        this.installmentFee = installmentFee;
        this.totalDue = totalDue;
    }

    public StudentProgram(Student student, Program program, Integer payID, double installmentFee) {
        this.student = student;
        this.program = program;
        this.payID = payID;
        this.installmentFee = installmentFee;
    }
}