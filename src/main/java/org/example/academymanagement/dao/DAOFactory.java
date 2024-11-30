package org.example.academymanagement.dao;

import org.example.academymanagement.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDAOFactory(){
        return(daoFactory==null)? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        PROGRAM, USER, STUDENT, PAYMENT, STUDENTPROGRAM
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types){
            case USER:
                return new UserDAOImpl();
            case PROGRAM:
                return new ProgramDAOImpl();
            case STUDENT:
                return new StudentDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case STUDENTPROGRAM:
                return new StudentProgramDAOImpl();
            default:
                return null;
        }
    }
}