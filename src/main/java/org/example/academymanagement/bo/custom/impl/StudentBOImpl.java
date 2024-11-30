package org.example.academymanagement.bo.custom.impl;

import org.example.academymanagement.bo.custom.StudentBO;
import org.example.academymanagement.dao.DAOFactory;
import org.example.academymanagement.dao.custom.StudentDAO;
import org.example.academymanagement.dto.StudentDTO;
import org.example.academymanagement.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {


    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public boolean saveStudent(StudentDTO studentDTO) throws Exception {
        return studentDAO.add(new Student(studentDTO.getStudentId(), studentDTO.getStudentNic(), studentDTO.getDob(), studentDTO.getFullName(), studentDTO.getAddress(), studentDTO.getEmail(), studentDTO.getPhone(), studentDTO.getRegistrationDate(), studentDTO.getRegistrationTime()));
    }

    @Override
    public boolean deleteStudent(String id) {
        try {
            studentDAO.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) throws Exception {
        return studentDAO.update(new Student(studentDTO.getStudentId(), studentDTO.getStudentNic(), studentDTO.getDob(), studentDTO.getFullName(), studentDTO.getAddress(), studentDTO.getEmail(), studentDTO.getPhone()));
    }

    @Override
    public StudentDTO searchStudentByStudentId(String id) {
        return null;
    }

    @Override
    public StudentDTO searchStudentByProgram(String name) {
        return null;
    }

    @Override
    public List<StudentDTO> getAllStudents() throws Exception {
        ArrayList<StudentDTO> studentDTOS = new ArrayList<>();
        List<Student> students = studentDAO.getAll();

        for (Student student : students) {
            studentDTOS.add(new StudentDTO(student.getStudentId(), student.getStudentNic(), student.getDob(), student.getFullName(), student.getAddress(), student.getEmail(), student.getPhone()));
        }
        return studentDTOS;
    }

    @Override
    public boolean saveStudentAndPrograms(StudentDTO studentDto, String[][] programDetailsArray, String[][] paymentDetailsArray) {
        return false;
    }
}
