package com.interview.insta.service.interfaces;

import com.interview.insta.entity.Status;
import com.interview.insta.entity.Users;

public interface ISignUpService {

    public Status signUp(Users user) throws Exception;

    public Status signIn(Users user) throws Exception;

}
