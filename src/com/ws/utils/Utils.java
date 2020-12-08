package com.ws.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Utils {
    
    public static <T> ResponseEntity<T> getResponseEntity(T body, HttpStatus statusCode) {
        return new ResponseEntity<T>(body,statusCode);
    }
    
    public static String createCode(String x) {
		StringBuilder builder = new StringBuilder();
	    String[] myName = x.split(" ");
	    for (int i = 0; i < myName.length; i++) {
	        String s = myName[i];
	        builder.append(s.charAt(0));
	    }
	    String str = builder.toString();
		return str;
	}
    

}

