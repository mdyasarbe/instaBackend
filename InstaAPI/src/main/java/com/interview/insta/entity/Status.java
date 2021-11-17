package com.interview.insta.entity;

public class Status {

    private String status;
    private String message;
    private Object response;

    public Status(){
        
    }
    public Status(String status,String message,Object res){
        this.status = status;
        this.message = message;
        this.response = res;
    }
    

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getResponse() {
        return response;
    }
    public void setResponse(Object response) {
        this.response = response;
    }
    
}
