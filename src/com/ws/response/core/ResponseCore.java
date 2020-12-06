package com.ws.response.core;

import org.springframework.http.HttpStatus;

public class ResponseCore {
	
	private HttpStatus httpStatus;

    public String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
    
}
