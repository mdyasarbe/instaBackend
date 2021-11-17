package com.interview.insta.service;

import com.interview.insta.Repository.UserRepo;
import com.interview.insta.entity.Status;
import com.interview.insta.entity.Users;
import com.interview.insta.exception.BadRequestException;
import com.interview.insta.service.interfaces.ISignUpService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpService implements ISignUpService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;


    @Override
    public Status signUp(Users user) throws Exception {
        try {
            user.setUserFirstName(user.getUserFirstName().trim());
            user.setUserLastName(user.getUserLastName().trim());
            user.setUserPassword(user.getUserPassword().trim());
            user.setUserName(user.getUserName().trim());
            
            if(user.getUserName().isEmpty()||user.getUserFirstName().isEmpty()||user.getUserLastName().isEmpty()||user.getUserPassword().isEmpty()){
                throw new BadRequestException("Failed", 
                "Cannot have Empty Field for "+
                (user.getUserFirstName().isEmpty() ?" First Name ":"") +  (user.getUserName().isEmpty() ?" User Name ":"") 
                + (user.getUserLastName().isEmpty() ?" Last Name ":"")+  (user.getUserPassword().isEmpty() ?" Password ":"") , 
                HttpStatus.NOT_ACCEPTABLE);
            }else{

            
            if (userRepo.findByuserName(user.getUserName()) == null) {
                
                user.setUserPassword(getEncodedPassword(user.getUserPassword()));
                userRepo.save(user);
                return new Status("Success", "User Registered Successfully", null);
            }
            
            throw new BadRequestException("Failed", "User Already Exist", HttpStatus.NOT_ACCEPTABLE);
        }

        } catch (BadRequestException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    @CacheEvict(cacheNames="follower",allEntries= true )
    public Status signIn(Users user) throws Exception {
        // //userRepo.findById(user.getUserName()).get()
        // Users inDb =userRepo.findByuserName(user.getUserName());
        try {

            Status result = jwtService.createJwtToken(user.getUserName(), user.getUserPassword());

            return result; // new Status("Success", "User Login Successful", jwt);

        } catch (Exception ex) {
            throw ex;
        }
    }

    // public Users check(String jwt) {
    //     // //userRepo.findById(user.getUserName()).get()
    //     // Users inDb =userRepo.findByuserName(user.getUserName());
    //     try {
    //         String usn = jwtUtil.getUsernameFromToken(jwt);
    //         return userRepo.findByuserName(usn);
    //     } catch (Exception ex) {
    //         throw ex;
    //     }

    // }

}
