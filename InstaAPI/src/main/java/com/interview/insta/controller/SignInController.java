package com.interview.insta.controller;

import com.interview.insta.entity.Status;
import com.interview.insta.entity.Users;
import com.interview.insta.exception.BadRequestException;
import com.interview.insta.exception.ExceptionController;
import com.interview.insta.exception.NotFoundException;
import com.interview.insta.service.SignUpService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice 
@RestController
public class SignInController {

    
    @Autowired
    private ExceptionController exceptionresolver;
    
    @Autowired
    private SignUpService signupService;

    @PostMapping({ "/signup" })
    public ResponseEntity<?> signUp(@RequestBody Users user) {
        try {
            Status status = signupService.signUp(user);
            return ResponseEntity.ok(status);

        }catch(BadRequestException ex){
            return exceptionresolver.handleBadRequest(ex);
        } catch(NotFoundException ex){
            return exceptionresolver.handleNotFound(ex);
        } catch (Exception ex) {
            return exceptionresolver.handleInternalSeverError(ex);
        }
    }

    @PostMapping({ "/signin" })
    public ResponseEntity<?> signin(@RequestBody Users user) {
        try{
            Status status = signupService.signIn(user);
            return ResponseEntity.ok(status);
            
        }catch(BadRequestException ex){
            return exceptionresolver.handleBadRequest(ex);
        } catch(NotFoundException ex){
            return exceptionresolver.handleNotFound(ex);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return exceptionresolver.handleInternalSeverError(ex);
        }
    }

    // @PostMapping({"/check"})
    // public ResponseEntity<?> check(Authentication authentication, Principal
    // principal){
    // Users status = new Users();
    // status.setUserName(authentication.getName());
    // status.setUserFirstName(principal.getName());

    // return ResponseEntity.ok();
    // }

}
