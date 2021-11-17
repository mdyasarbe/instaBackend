package com.interview.insta.service;

import java.util.HashSet;
import java.util.Set;

import com.interview.insta.Repository.UserRepo;
import com.interview.insta.entity.JwtResponse;
import com.interview.insta.entity.Status;
import com.interview.insta.entity.Users;
import com.interview.insta.exception.BadRequestException;
import com.interview.insta.exception.NotFoundException;
import com.interview.insta.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtService implements UserDetailsService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    public Status createJwtToken(String userName, String userPassword) throws Exception {
        UserDetails userDetails;
        try {
            userDetails = loadUserByUsername(userName);
            authenticate(userName, userPassword);
            String newGeneratedToken = jwtUtil.generateToken(userDetails);
            Users user = userRepo.findByuserName(userName);
            user.setUserPassword("");
            return new Status("Success", "Login SuccessFull", new JwtResponse(user, newGeneratedToken));
        }catch(UsernameNotFoundException ex) {
            
            throw new NotFoundException("Failed to fetch ",HttpStatus.NOT_FOUND,ex);
        }
        catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepo.findByuserName(username);

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getUserPassword(),
                    getAuthority(user));
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    private Set<SimpleGrantedAuthority> getAuthority(Users user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + " "));

        return authorities;
    }

    private void authenticate(String userName, String userPassword) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
        } catch (DisabledException e) {
            throw new BadRequestException("USER_DISABLED",HttpStatus.BAD_REQUEST, e);
        } catch (BadCredentialsException e) {
            throw new BadRequestException("Invalid Credentials",HttpStatus.BAD_REQUEST, e);
        }
    }

}
