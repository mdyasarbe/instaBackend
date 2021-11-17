package com.interview.insta.service;

import java.util.List;

import com.interview.insta.Repository.FollowersRepo;
import com.interview.insta.Repository.UserRepo;
import com.interview.insta.entity.FollowerList;
import com.interview.insta.entity.Status;
import com.interview.insta.entity.Users;
import com.interview.insta.exception.BadRequestException;
import com.interview.insta.exception.NotFoundException;
import com.interview.insta.service.interfaces.IFollowersService;
import com.interview.insta.util.CacheMain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class FollowersService implements IFollowersService {

    @Autowired
    private FollowersRepo followersRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    // @Caching( 
    //     evict={ @CacheEvict(cacheNames="searchimages", key="#parent.getId()"),
    //             @CacheEvict(cacheNames="follower",key="#parent.getId()" )})
    public Status addFollower(Users parent, Users child) throws Exception {
        try {
            if(CacheMain.hasCache("follower", String.valueOf(parent.getId()))){

                CacheMain.deleteCache("follower", String.valueOf(parent.getId()));
            }
            if(CacheMain.hasCache("searchimages", String.valueOf(parent.getId()))){

                CacheMain.deleteCache("searchimages", String.valueOf(parent.getId()));
            }
            System.out.println("From -> addFollower()  Calling Repo to access DB");
            if (userRepo.findByuserName(child.getUserName()) != null) {
                if (!followersRepo.findByparentIdAndchildId(parent, child)) {
                    FollowerList followerlist = new FollowerList();
                    followerlist.setParentid(parent);
                    followerlist.setChildid(child);
                    followersRepo.save(followerlist);
                return new Status("Success", "Follower Added Successfully", null);
                } else {

                    throw new BadRequestException("Failed"," Requester already Following the User",HttpStatus.BAD_REQUEST);
                }
            }
            throw new BadRequestException("Failed"," User Does Not Exist",HttpStatus.BAD_REQUEST);
        }
        catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    //@Cacheable(cacheNames="follower",key="#user.getId()")
    public List<Users> getFollower(Users user) throws Exception {
        try {
            if(CacheMain.hasCache("follower", String.valueOf(user.getId()))){
                @SuppressWarnings("unchecked")
                List<Users> result  = (List<Users>) CacheMain.getCache("follower", String.valueOf(user.getId()));
                return result;
            }
            System.out.println("From -> getFollower()  Calling Repo to access DB");
            List<Users> result = followersRepo.findallfollowers(user, user.getId());
            if(result.size()<1){
                throw new NotFoundException("Result yeild Empty List", "Reason : No users available to Follow",
                HttpStatus.NOT_FOUND);
            }
            CacheMain.insertCache("follower", String.valueOf(user.getId()),result);
            return result;

        }catch (NotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

}
