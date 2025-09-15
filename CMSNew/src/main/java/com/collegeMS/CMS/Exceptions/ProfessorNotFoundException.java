package com.collegeMS.CMS.Exceptions;

public class ProfessorNotFoundException extends RuntimeException{
    public ProfessorNotFoundException(String message){
        super(message);
    }
}
