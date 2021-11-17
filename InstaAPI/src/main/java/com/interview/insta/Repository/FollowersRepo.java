package com.interview.insta.Repository;


import java.util.List;

import com.interview.insta.entity.FollowerList;
import com.interview.insta.entity.Users;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface FollowersRepo extends CrudRepository<FollowerList, Integer> { 

    @Query(value="SELECT CASE WHEN Exists(SELECT f.id from Followerlist f where f.parentid=:parentid AND f.childid=:childid) THEN 'true' ELSE 'false' END",nativeQuery = true)
    boolean findByparentIdAndchildId(@Param("parentid") Users parent,@Param("childid") Users child);

     //Another query 
    //Select * from users u inner join follower_list f ON u.id != f.child_id where u.id!="1";
    @Query("SELECT NEW com.interview.insta.entity.Users( u.id,u.userName,u.userFirstName ,u.userLastName ,'Null',u.profileType) "
            +"from Users u where u.id!=:userid AND u.id NOT IN ("+ "SELECT childid from Followerlist f where f.parentid=:user "+")")
    List<Users> findallfollowers(@Param("user") Users user,@Param("userid") int userid);    
}
