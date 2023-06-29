package com.test.cilia.error;

import java.time.Instant;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;


@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class ApiError {
    
    private int  status;
    private String message;
    private Long timestamp;
    private String path;
    private HashMap<String, String> validationErrorsFields;


    public ApiError(){
    }


    public ApiError(int status, String message, String path) {
        this.status = status;
        this.message = message;
        this.path = path;
        this.timestamp = Instant.now().toEpochMilli();
    }



    
    
}
