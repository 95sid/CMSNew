package com.collegeMS.CMS.Advices;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {
    T data;
    ApiError error;
    @JsonFormat(pattern="hh:mm:ss dd-MM-yyyy")
    LocalDateTime timeStamp;

    ApiResponse(){
        this.timeStamp = LocalDateTime.now();
    }

    ApiResponse(ApiError error){
        this();
        this.error = error;
    }

    ApiResponse(T data){
        this();
        this.data = data;
    }
}
