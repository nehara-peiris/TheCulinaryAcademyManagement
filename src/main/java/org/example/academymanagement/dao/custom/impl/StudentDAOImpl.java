package org.example.academymanagement.dao.custom.impl;

import org.example.academymanagement.config.FactoryConfiguration;
import org.example.academymanagement.dao.custom.StudentDAO;
import org.example.academymanagement.entity.Program;
import org.example.academymanagement.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;


public class StudentDAOImpl implements StudentDAO {

    @Override
    public boolean add(Student entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(Student entity) throws Exception {
        return false;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Student search(Student entity) throws Exception {
        return null;
    }

    @Override
    public List<Student> getAll() throws Exception {
        return null;
    }

    @Override
    public Student exist(String id) throws Exception {
        return null;
    }
}