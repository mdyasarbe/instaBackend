package com.interview.insta.exception;

import javax.servlet.ServletRequest;

import com.interview.insta.entity.ErrorResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public @ResponseBody ErrorResponse BadRequest(ServletRequest request,
        Exception ex ) {

        // Map<String, Object> body = new LinkedHashMap<>();
        // body.put("timestamp", LocalDateTime.now());
        // body.put("message", ex.getMessage());
        ErrorResponse res =new ErrorResponse("Failed ",ex.getLocalizedMessage(),HttpStatus.BAD_REQUEST);
        return res;
    }

    public ResponseEntity<ErrorResponse> handleNotFound(NotFoundException ex) {
        ErrorResponse res =new ErrorResponse("Failed ",ex.getLocalizedMessage(),ex.statuscode);
        return ResponseEntity.status(ex.statuscode).body(res);
    }
    public ResponseEntity<ErrorResponse> handleBadRequest(Exception ex) {

        ErrorResponse res =new ErrorResponse("Failed ",ex.getLocalizedMessage(),HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }
    public ResponseEntity<ErrorResponse> handleInternalSeverError(Exception ex) {

        ErrorResponse res =new ErrorResponse("Failed ",ex.getLocalizedMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
    }
}
