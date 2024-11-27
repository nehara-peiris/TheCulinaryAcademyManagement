package org.example.academymanagement.bo.custom;

import org.example.academymanagement.bo.SuperBO;
import org.example.academymanagement.dto.ProgramDTO;

import java.util.List;

public interface ProgramBO extends SuperBO {
    List<ProgramDTO> getAllPrograms() throws Exception;

    boolean addProgram(ProgramDTO programDto) throws Exception;

    boolean updateProgram(ProgramDTO programDto) throws Exception;

    boolean deleteProgram(String programId);

    ProgramDTO getProgramDetails(String programId);
}
