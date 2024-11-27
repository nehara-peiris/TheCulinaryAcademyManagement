package org.example.academymanagement.bo;

import org.example.academymanagement.bo.custom.impl.ProgramBOImpl;
import org.example.academymanagement.bo.custom.impl.StudentBOImpl;
import org.example.academymanagement.bo.custom.impl.UserBOImpl;

public class BOFactory {

    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
        PROGRAM, USER, STUDENT
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case USER:
                return new UserBOImpl();
            case PROGRAM:
                return new ProgramBOImpl();
            case STUDENT:
                return new StudentBOImpl();
            default:
                return null;
        }
    }
}