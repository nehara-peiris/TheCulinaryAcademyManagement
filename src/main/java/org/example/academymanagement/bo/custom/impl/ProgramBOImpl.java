package org.example.academymanagement.bo.custom.impl;

import org.example.academymanagement.bo.custom.ProgramBO;
import org.example.academymanagement.config.FactoryConfiguration;
import org.example.academymanagement.dao.DAOFactory;
import org.example.academymanagement.dao.custom.ProgramDAO;
import org.example.academymanagement.dto.ProgramDTO;
import org.example.academymanagement.entity.Program;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class ProgramBOImpl implements ProgramBO {

    ProgramDAO programDAO = (ProgramDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PROGRAM);

    @Override
    public List<ProgramDTO> getAllPrograms() throws Exception {
        ArrayList<ProgramDTO> programDTOS = new ArrayList<>();
        List<Program> programs = programDAO.getAll();

        for (Program program : programs) {
            programDTOS.add(new ProgramDTO(program.getProgramId(), program.getProgramName(), program.getDuration(), program.getFee()));
        }
        return programDTOS;
    }

    @Override
    public boolean addProgram(ProgramDTO programDto) throws Exception {
        return programDAO.add(new Program(programDto.getProgramId(), programDto.getProgramName(), programDto.getDuration(), programDto.getFee()));
    }

    @Override
    public boolean updateProgram(ProgramDTO programDto) throws Exception {
        return programDAO.update(new Program(programDto.getProgramId(), programDto.getProgramName(), programDto.getDuration(), programDto.getFee()));
    }

    @Override
    public boolean deleteProgram(String programId) {
        try {
            programDAO.delete(programId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return true;
    }


    @Override
    public ProgramDTO getProgramById(String programId) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Program program = session.get(Program.class, programId); // Assuming Program is your entity class
            if (program != null) {
                return new ProgramDTO(program.getProgramId(), program.getFee()); // Convert to DTO if needed
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Return null if not found
    }
}
