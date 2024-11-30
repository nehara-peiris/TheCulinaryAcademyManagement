package org.example.academymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProgramDTO {
    private String programId;
    private String programName;
    private String duration;
    private Double fee;

    public ProgramDTO(String programId) {
        this.programId = programId;
    }

    public ProgramDTO(String programId, Double fee) {
        this.programId = programId;
        this.fee = fee;
    }
}
