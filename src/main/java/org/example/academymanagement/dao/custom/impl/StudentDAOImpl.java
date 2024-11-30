package org.example.academymanagement.dao.custom.impl;

import org.example.academymanagement.config.FactoryConfiguration;
import org.example.academymanagement.dao.custom.StudentDAO;
import org.example.academymanagement.entity.Student;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public class StudentDAOImpl implements StudentDAO {

    @Override
    public boolean add(Student entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.getTransaction().begin();
        session.save(entity);
        session.getTransaction().commit();
        session.close();

        return true;
    }

    @Override
    public boolean update(Student entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.getTransaction().begin();
        session.update(entity);
        session.getTransaction().commit();
        session.close();

        return true;
    }

    @Override
    public void delete(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.getTransaction().begin();
        Student student = session.get(Student.class, id);
        session.delete(student);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Student search(Student entity) throws Exception {
        return null;
    }

    @Override
    public List<Student> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.getTransaction().begin();
        List<Student> students = session.createQuery("from Student").list();
        session.getTransaction().commit();
        session.close();

        return students;
    }

    @Override
    public Student exist(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.getTransaction().begin();
        Student student = session.get(Student.class, id);
        session.getTransaction().commit();
        session.close();

        return student;
    }

}