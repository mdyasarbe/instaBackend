package com.interview.insta.service.interfaces;

import java.util.List;

import com.interview.insta.entity.Status;
import com.interview.insta.entity.Users;

public interface IFollowersService {

    public Status addFollower(Users parent, Users child) throws Exception;
    public List<Users> getFollower(Users user) throws Exception;

}
