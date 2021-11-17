package com.interview.insta.service.interfaces;

import java.util.List;

import com.interview.insta.entity.ImageDetails;
import com.interview.insta.entity.Users;

public interface IImageServices {

    public ImageDetails uploadImage(Users user, ImageDetails image) throws Exception;
    
    public List<ImageDetails> getMyImages(Users user) throws Exception;

    public List<ImageDetails> getHottestImage(Users user);

    public ImageDetails findById(int imageid) throws Exception;

    public boolean isHavingAccess(int userid,int imgid);

    public ImageDetails downloadImage(Users user, ImageDetails image) throws Exception;

    public List<ImageDetails> searchImage(Users user, String title) throws Exception;
}
