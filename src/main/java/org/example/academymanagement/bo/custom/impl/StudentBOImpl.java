package org.example.academymanagement.bo.custom.impl;

import org.example.academymanagement.bo.custom.StudentBO;
import org.example.academymanagement.dto.StudentDTO;

import java.util.List;

public class StudentBOImpl implements StudentBO {
    @Override
    public void saveStudent(StudentDTO studentDTO) {

    }

    @Override
    public void deleteStudent(String id) {

    }

    @Override
    public void updateStudent(StudentDTO studentDTO) {

    }

    @Override
    public StudentDTO searchStudentByStudentId(String id) {
        return null;
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        return null;
    }

    @Override
    public boolean saveStudentAndPrograms(StudentDTO studentDto, String[][] programDetailsArray, String[][] paymentDetailsArray) {
        return false;
    }
}
