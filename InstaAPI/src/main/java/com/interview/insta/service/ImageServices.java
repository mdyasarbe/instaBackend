package com.interview.insta.service;

import java.util.List;
import java.util.NoSuchElementException;

import com.interview.insta.Repository.ImageRepo;
import com.interview.insta.entity.ImageDetails;
import com.interview.insta.entity.Users;
import com.interview.insta.exception.BadRequestException;
import com.interview.insta.exception.NotFoundException;
import com.interview.insta.service.interfaces.IImageServices;
import com.interview.insta.util.CacheMain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ImageServices implements IImageServices {

    @Autowired
    private ImageRepo imageRepo;

    @Autowired
    private ImageServices self;

    @Override
    // @Caching( 
    //     evict={ @CacheEvict(cacheNames="userimages",key ="#user.getId()" )})
    public ImageDetails uploadImage(Users user, ImageDetails image) throws Exception {
        try {
            if(image.getTitle().isEmpty()){
                throw new BadRequestException("Failed","Title cannot be empty",HttpStatus.BAD_REQUEST);
            }
            if(CacheMain.hasCache("userimages", String.valueOf(user.getId()))){
                CacheMain.deleteCache("userimages", String.valueOf(user.getId()));
            }
            System.out.println("From -> uploadImage()  Calling Repo to access DB");
            image.setUserid(user);
            image.setNumofdownloads(0);
            imageRepo.save(image);
            ImageDetails result = imageRepo.save(image);
            result.getUserid().setUserPassword(null);
            return result;
        }catch (BadRequestException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    // @Cacheable(cacheNames="userimages",key="#user.getId()")
    public List<ImageDetails> getMyImages(Users user) throws Exception {
        
        try {
            if(CacheMain.hasCache("userimages", String.valueOf(user.getId()))){
                @SuppressWarnings("unchecked")
                List<ImageDetails> result  = (List<ImageDetails>) CacheMain.getCache("userimages", String.valueOf(user.getId()));
                 return result;
            }
            System.out.println("From -> getMyImages()  Calling Repo to access DB");
            List<ImageDetails> result = imageRepo.findAllByuserid(user); 
            if(result.size()<1){
                throw new NotFoundException("Result yeild Empty List", "Reason : No Images Available",
                HttpStatus.NOT_FOUND);
            }
            for(ImageDetails i : result){
                i.getUserid().setUserPassword(null);
            }
            CacheMain.insertCache("userimages", String.valueOf(user.getId()),result);
            return result;
        }catch (NotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public List<ImageDetails> getHottestImage(Users user) {
        try {
            System.out.println("From -> getHottestImage()  Calling Repo to access DB");
            List<ImageDetails> result = imageRepo.getHottestPicture(user.getId());
            if(result.size()<1){
                throw new NotFoundException("Result yeild Empty List", "Reason : No Images Available",
                HttpStatus.NOT_FOUND);
            }
            for (ImageDetails i : result) {
                Users temp = i.getUserid();
                temp.setUserPassword(null);
            }
            return result;
        }catch (NotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    // @Cacheable(cacheNames="images",key="#imageid")
    public ImageDetails findById(int imageid) throws Exception{
        if(CacheMain.hasCache("images",  String.valueOf(imageid))){
            ImageDetails result  =  (ImageDetails) CacheMain.getCache("images",  String.valueOf(imageid));
             return result;
        }
        System.out.println("From -> findById()  Calling Repo to access DB");
        ImageDetails res = imageRepo.findById(imageid).get();
        CacheMain.insertCache("images",  String.valueOf(imageid),res);
       return res;
    }


    @Override
    // @Cacheable(cacheNames="haveAccessimages",key="#userid+' '+#imgid")
    public boolean isHavingAccess(int userid,int imgid){
        if(CacheMain.hasCache("haveAccessimages", userid+" "+imgid)){
            boolean result  =  (boolean) CacheMain.getCache("haveAccessimages", userid+" "+imgid);
             return result;
        }
        System.out.println("From -> isHavingAccess()  Calling Repo to access DB");

        return imageRepo.isHavingAccess(userid, imgid);
    }
    
    //@CachePut(cacheNames="images",key="#image.getId()")
    public ImageDetails downloadImage(Users user, ImageDetails image) throws Exception {
        try {
            int imageid = image.getId();
            ImageDetails isExist = self.findById(imageid);

            if (isExist == null) {
                throw new NotFoundException("Result yeild Empty List", "Reason : Provided Image Id is Invalid",
                        HttpStatus.BAD_REQUEST);
            }

            boolean hasAccess = self.isHavingAccess(user.getId(), image.getId());
            if (hasAccess) {
                System.out.println("From -> downloadImage()  Calling Repo to access DB");
                imageRepo.updatenumofdownloads(imageid);
                ImageDetails res = imageRepo.findById(imageid).get();
                res.getUserid().setUserPassword(null);
                return res;
            }
            throw new NotFoundException("Un Authorized Access",
                    "Reason : You don't have permission to download this image", HttpStatus.UNAUTHORIZED);

        } catch (NoSuchElementException ex) {
            throw new NotFoundException("Result yeild Empty List", ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        } catch (NotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    // @Cacheable(cacheNames="searchimages",key="#user.getId()+' '+#title")
    public List<ImageDetails> searchImage(Users user, String title) throws Exception {
        try {
            if(CacheMain.hasCache("searchimages", String.valueOf(user.getId())+" "+title)){
                @SuppressWarnings("unchecked")
                List<ImageDetails> result  = (List<ImageDetails>) CacheMain.getCache("searchimages", String.valueOf(user.getId())+" "+title);
                 return result;
            }
            System.out.println("From -> searchimages()  Calling Repo to access DB");
            List<ImageDetails> result = imageRepo.findAllByTitle(user.getId(), title);
            for (ImageDetails i : result) {
                Users temp = i.getUserid();
                temp.setUserPassword(null);
            }
            CacheMain.insertCache("searchimages", String.valueOf(user.getId())+" "+title,result);
            return result;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
