package com.interview.insta.controller;

import java.util.List;

import com.interview.insta.Repository.UserRepo;
import com.interview.insta.entity.Status;
import com.interview.insta.entity.Users;
import com.interview.insta.exception.BadRequestException;
import com.interview.insta.exception.ExceptionController;
import com.interview.insta.exception.NotFoundException;
import com.interview.insta.service.FollowersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class FollowersController {

    @Autowired
    private ExceptionController exceptionresolver;

    @Autowired
    private FollowersService followersService;
    
    @Autowired
    private UserRepo userRepo;

    @PostMapping({ "/addfollower" })
    public ResponseEntity<?> addFollower(Authentication authentication, @RequestBody Users user) {

        try {

            Users parent = userRepo.findByuserName(authentication.getName());
            Status status = followersService.addFollower(parent, user);
            return ResponseEntity.ok(status);

        } catch (BadRequestException ex) {
            return exceptionresolver.handleBadRequest(ex);
        } catch (Exception ex) {
            return exceptionresolver.handleInternalSeverError(ex);
        }

    }

    @GetMapping({ "/getfollower" })
    public ResponseEntity<?> getFollower(Authentication authentication) {
        List<Users> getfollowers;
        try {
            Users user = userRepo.findByuserName(authentication.getName());
            getfollowers = followersService.getFollower(user);
            if (getfollowers.size() < 1) {
                throw new NotFoundException("Result yeild Empty List",
                        "Reason : You may Following all available users/ No Users available", HttpStatus.NO_CONTENT);
            }
        } catch (NotFoundException ex) {
            return exceptionresolver.handleNotFound(ex);
        } catch (Exception ex) {
            return exceptionresolver.handleInternalSeverError(ex);
        }

        return ResponseEntity.ok(getfollowers);

    }
}
