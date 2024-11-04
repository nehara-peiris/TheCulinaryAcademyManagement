package org.example.academymanagement.config;

import org.example.academymanagement.entity.Course;
import org.example.academymanagement.entity.Enrollment;
import org.example.academymanagement.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {

    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration(){
        Configuration configuration = new Configuration()
                .configure()
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Enrollment.class);
        sessionFactory=configuration.buildSessionFactory();
    }
    public static FactoryConfiguration getInstance(){
        return (factoryConfiguration == null)? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }
    public Session getSession(){
        return sessionFactory.openSession();
    }
}
