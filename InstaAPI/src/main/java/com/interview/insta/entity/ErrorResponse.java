package com.interview.insta.entity;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

public class ErrorResponse {
    
    private HttpStatus statuscode;
    private String message;
    private String date;
    private String status;

    public ErrorResponse(String status,String message,HttpStatus statuscode){
        this.status= status;
        this.date = LocalDateTime.now().toString();
        this.statuscode = statuscode;
        this.message = message;
      }

    public HttpStatus getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(HttpStatus statuscode) {
        this.statuscode = statuscode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ErrorResponse(String status,HttpStatus statuscode, Exception e) {
        this.status = status;
        this.message = e.getLocalizedMessage();
        this.date= LocalDateTime.now().toString();
        this.statuscode = statuscode;
    }
}
