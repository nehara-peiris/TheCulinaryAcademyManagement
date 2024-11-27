package org.example.academymanagement.dao.custom.impl;

import org.example.academymanagement.config.FactoryConfiguration;
import org.example.academymanagement.dao.custom.ProgramDAO;
import org.example.academymanagement.entity.Program;
import org.example.academymanagement.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProgramDAOImpl implements ProgramDAO {

    @Override
    public boolean add(Program entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.getTransaction().begin();
        session.save(entity);
        session.getTransaction().commit();
        session.close();

        return true;
    }

    @Override
    public boolean update(Program entity) throws Exception {
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
        Program program = session.get(Program.class, id);
        session.delete(program);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Program search(Program entity) throws Exception {
        return null;
    }

    @Override
    public List<Program> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.getTransaction().begin();
        List<Program> programs = session.createQuery("from Program").list();
        session.getTransaction().commit();
        session.close();

        return programs;
    }

    @Override
    public Program exist(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.getTransaction().begin();
        Program program = session.get(Program.class, id);
        session.getTransaction().commit();
        session.close();

        return program;
    }
}
