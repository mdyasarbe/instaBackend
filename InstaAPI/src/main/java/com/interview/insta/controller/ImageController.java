package com.interview.insta.controller;

import java.util.List;

import com.interview.insta.Repository.UserRepo;
import com.interview.insta.entity.ImageDetails;
import com.interview.insta.entity.Status;
import com.interview.insta.entity.Users;
import com.interview.insta.exception.ExceptionController;
import com.interview.insta.exception.NotFoundException;
import com.interview.insta.service.ImageServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController {

    @Autowired
    private ExceptionController exceptionresolver;

    @Autowired
    private ImageServices imageServices;

    @Autowired
    private UserRepo userRepo;

    @PostMapping({ "/uploadimage" })
    public ResponseEntity<?> uploadImage(Authentication authentication, @RequestBody ImageDetails image) {
        try {
            Users user = userRepo.findByuserName(authentication.getName());
            ImageDetails res = imageServices.uploadImage(user, image);
            Status status = new Status("Success","Image Uploaded Successfully",res);
            return ResponseEntity.ok(status);
        } catch (Exception ex) {
            return exceptionresolver.handleInternalSeverError(ex);
        }

    }

    @GetMapping({ "/getmyimages" })
    public ResponseEntity<?> getMyImages(Authentication authentication) {
        try {
            Users user = userRepo.findByuserName(authentication.getName());
            List<ImageDetails> result = imageServices.getMyImages(user);
            if (result.size() < 1) {
                throw new NotFoundException("Result yeild Empty List", "Reason : You have not uploaded any images yet",
                        HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.ok(result);
        } catch (NotFoundException ex) {
            return exceptionresolver.handleNotFound(ex);
        } catch (Exception ex) {
            return exceptionresolver.handleInternalSeverError(ex);
        }

    }

    @GetMapping({ "/gethottestimage" })
    public ResponseEntity<?> getHottestImage(Authentication authentication) {
        try {
            Users user = userRepo.findByuserName(authentication.getName());
            List<ImageDetails> result = imageServices.getHottestImage(user);

            if (result.size() < 1) {
                throw new NotFoundException("Result yeild Empty List",
                        "Reason : You have may not have access to view photo/ No photo to show", HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.ok(result);
        } catch (NotFoundException ex) {
            return exceptionresolver.handleNotFound(ex);
        } catch (Exception ex) {
            return exceptionresolver.handleInternalSeverError(ex);
        }

    }

    @PutMapping({ "/downloadimage" })
    public ResponseEntity<?> downloadImage(Authentication authentication, @RequestBody ImageDetails image) {
        try {
            Users user = userRepo.findByuserName(authentication.getName());
            ImageDetails result = imageServices.downloadImage(user, image);
            return ResponseEntity.ok(result);
        } catch (NotFoundException ex) {
            return exceptionresolver.handleNotFound(ex);
        } catch (Exception ex) {
            return exceptionresolver.handleInternalSeverError(ex);
        }

    }

    @GetMapping(value = "/searchimage/{title}")
    public ResponseEntity<?> searchImage(Authentication authentication, @PathVariable String title) {
        try {
            Users user = userRepo.findByuserName(authentication.getName());
            List<ImageDetails> result = imageServices.searchImage(user, title);
            if (result.size() < 1) {
                String message = "No Image with title :" + title;
                throw new NotFoundException("Result yeild Empty List", message, HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.ok(result);

        } catch (NotFoundException ex) {
            return exceptionresolver.handleNotFound(ex);
        } catch (Exception ex) {
            return exceptionresolver.handleInternalSeverError(ex);
        }

    }
}
