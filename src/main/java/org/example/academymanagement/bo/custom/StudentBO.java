package org.example.academymanagement.bo.custom;

import org.example.academymanagement.bo.SuperBO;
import org.example.academymanagement.dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {
    boolean saveStudent(StudentDTO studentDTO) throws Exception;

    boolean deleteStudent(String id);

    boolean updateStudent(StudentDTO studentDTO) throws Exception;

    StudentDTO searchStudentByStudentId(String id);

    StudentDTO searchStudentByProgram(String name);

    List<StudentDTO> getAllStudents() throws Exception;

    boolean saveStudentAndPrograms(StudentDTO studentDto, String[][] programDetailsArray, String[][] paymentDetailsArray);
}
