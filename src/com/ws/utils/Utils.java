package com.ws.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Utils {
    
    public static <T> ResponseEntity<T> getResponseEntity(T body, HttpStatus statusCode) {
        return new ResponseEntity<T>(body,statusCode);
    }

}

