package org.example.academymanagement.bo.custom;

import org.example.academymanagement.bo.SuperBO;
import org.example.academymanagement.dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {
    void saveStudent(StudentDTO studentDTO);

    void deleteStudent(String id);

    void updateStudent(StudentDTO studentDTO);

    StudentDTO searchStudentByStudentId(String id);

    List<StudentDTO> getAllStudents();

    boolean saveStudentAndPrograms(StudentDTO studentDto, String[][] programDetailsArray, String[][] paymentDetailsArray);
}
