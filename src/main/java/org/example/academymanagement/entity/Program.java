package org.example.academymanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.academymanagement.dto.ProgramDTO;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "program")
public class Program {
    @Id
    @Column(name = "program_id")
    private String programId;

    @Column(name = "program_name", nullable = false)
    private String programName;

    @Column(name = "duration", nullable = false)
    private String duration;

    @Column(name = "fee", nullable = false)
    private Double fee;

    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StudentProgram> studentPrograms;

    public Program(String programId) {
        this.programId = programId;
    }

    public Program(String programId, String programName, String duration, Double fee) {
        this.programId = programId;
        this.programName = programName;
        this.duration = duration;
        this.fee = fee;
    }
}