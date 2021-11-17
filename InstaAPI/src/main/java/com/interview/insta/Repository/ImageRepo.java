package com.interview.insta.Repository;


import java.util.List;
import com.interview.insta.entity.ImageDetails;
import com.interview.insta.entity.Users;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ImageRepo extends CrudRepository<ImageDetails,Integer>   {
    
    List<ImageDetails> findAllByuserid(Users userid);

    @Modifying
    @Query(value="UPDATE ImageDetails img set img.numofdownloads = img.numofdownloads + 1 WHERE img.id = :id",nativeQuery = true)
    void updatenumofdownloads(@Param("id") int id);

    /* ----------------Old Query-----------

    @Query(value="SELECT * FROM imagedetails i WHERE i.id IN ("
        +"SELECT img.id FROM imagedetails img WHERE userid = :id UNION "
        +"SELECT img.id FROM imagedetails img WHERE visibility ='public' UNION "
        +"SELECT img.id FROM imagedetails img WHERE visibility ='private' AND userid IN ("
        +"SELECT childid FROM followerlist WHERE parentid= :id)) ORDER BY numofdownloads DESC;",nativeQuery = true)
        
        ------------------------------------*69**/

    @Query(value="SELECT * FROM imagedetails WHERE id in( SELECT img.id FROM  imagedetails img "
    +"INNER JOIN users u  ON img.userid=u.id WHERE u.id =:id OR img.visibility='public' "
    +"OR exists ( SELECT 1 FROM followerlist f WHERE f.childid = img.userid AND f.parentid =:id)) ORDER BY numofdownloads DESC LIMIT 100",nativeQuery = true)
    List<ImageDetails> getHottestPicture(@Param("id") int id);

    /* ----------------Old Query-----------

    @Query(value="SELECT * FROM imagedetails WHERE title LIKE %:text% AND id IN ("
    +"SELECT img.id FROM imagedetails img WHERE userid = :id UNION "
    +"SELECT img.id FROM imagedetails img WHERE visibility ='public' UNION "
    +"SELECT img.id FROM imagedetails img WHERE visibility ='private' AND userid IN ("
    +"SELECT childid FROM followerlist WHERE parentid= :id))",nativeQuery = true) 
    
    ---------------------------------------*/

    // creating index ON title will increase the performance

    @Query(value="SELECT * FROM imagedetails WHERE id in( SELECT img.id FROM  imagedetails img "
        +"INNER JOIN users u  ON img.userid=u.id WHERE u.id =:id OR img.visibility='public' "
        +"OR exists ( SELECT 1 FROM followerlist f WHERE f.childid = img.userid AND f.parentid =:id)) and "
        +"title like %:text% ORDER BY numofdownloads DESC LIMIT 100",nativeQuery = true)
    List<ImageDetails> findAllByTitle(@Param("id") int id,@Param("text") String text);

    /* ----------------Old Query-----------
    
    @Query(value="SELECT CASE WHEN Exists ("
        +"SELECT * FROM imagedetails i WHERE   i.id =:imgid and id in ("
        +"SELECT img.id FROM imagedetails img WHERE userid = :userid UNION "
        +"SELECT img.id FROM imagedetails img WHERE visibility ='public' UNION "
        +"SELECT img.id FROM imagedetails img WHERE visibility ='public' AND userid IN ("
        +"SELECT childid FROM followerlist WHERE parentid= :userid))) THEN 'true' ELSE 'false' END",nativeQuery = true)
        
        ------------------------------------*/

    @Query(value="SELECT CASE WHEN Exists ( SELECT 1 FROM  imagedetails img "
        +"INNER JOIN users u  ON img.userid=u.id WHERE u.id =3 OR img.visibility='public' "
        +"OR exists ( SELECT 1 FROM followerlist f WHERE f.childid = img.userid AND f.parentid =3)) THEN 'true' ELSE 'false' END",nativeQuery = true)
    Boolean isHavingAccess(@Param("userid") int id,@Param("imgid") int imgid);

    
}

