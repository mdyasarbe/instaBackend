package com.interview.insta.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class UnAuthorizedException extends RuntimeException {

     
    HttpStatus statuscode;
    String message;
    LocalDateTime date;
    String status;

    public UnAuthorizedException(String status,String message,HttpStatus statuscode){
        this.status= status;
        this.date = LocalDateTime.now();
        this.statuscode = statuscode;
        this.message = message;
      }

    public UnAuthorizedException(String status,HttpStatus statuscode, Exception e) {
        this.status = status;
        this.message = e.getLocalizedMessage();
        this.date= LocalDateTime.now();
        this.statuscode = statuscode;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(HttpStatus statuscode) {
        this.statuscode = statuscode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
