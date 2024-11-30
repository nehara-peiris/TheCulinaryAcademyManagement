package org.example.academymanagement.bo;

import org.example.academymanagement.bo.custom.impl.*;

public class BOFactory {

    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
        PROGRAM, USER, STUDENT, PAYMENT, STUDENTPROGRAM
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case USER:
                return new UserBOImpl();
            case PROGRAM:
                return new ProgramBOImpl();
            case STUDENT:
                return new StudentBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case STUDENTPROGRAM:
                return new StudentProgramBOImpl();
            default:
                return null;
        }
    }
}