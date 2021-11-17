package com.interview.insta.exception;

import java.time.LocalDateTime;

public class NullValueException extends Exception {

    String message;
    LocalDateTime date;
    String status;

    public NullValueException(String status,String message){
        this.status= status;
        this.date = LocalDateTime.now();
        this.message = message;
      }

    public NullValueException(String status, Exception e) {
        this.status = status;
        this.message = e.getLocalizedMessage();
        this.date= LocalDateTime.now();
    }

    public String getMessage() {
        return message;
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
