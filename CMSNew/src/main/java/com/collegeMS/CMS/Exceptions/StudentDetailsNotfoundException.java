package com.collegeMS.CMS.Exceptions;

public class StudentDetailsNotfoundException extends RuntimeException{
    public StudentDetailsNotfoundException(String message){
        super(message);
    }
}
