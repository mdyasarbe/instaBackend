package com.interview.insta.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import org.hibernate.annotations.DynamicUpdate;


@Entity
@Table(name = "Imagedetails")
@DynamicUpdate
public class ImageDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne()
    @JoinColumn(name="userid", referencedColumnName = "id", nullable = false)    
    private Users userid;
    @Column(nullable = false)
    private String url;
    @Column(nullable = false)
    private String title;
    private String description;
    private String tags;
    private String pictureDetails;
    @Column(nullable = false)
    private String visibility;
    @Column(nullable = false)
    private int numofdownloads;
    @Version
    private Long version;

    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Users getUserid() {
        return userid;
    }
    public void setUserid(Users userid) {
        this.userid = userid;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getTags() {
        return tags;
    }
    public void setTags(String tags) {
        this.tags = tags;
    }
    public String getPictureDetails() {
        return pictureDetails;
    }
    public void setPictureDetails(String pictureDetails) {
        this.pictureDetails = pictureDetails;
    }
    public String getVisibility() {
        return visibility;
    }
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }
    public int getNumofdownloads() {
        return numofdownloads;
    }
    public void setNumofdownloads(int numofdownloads) {
        this.numofdownloads = numofdownloads;
    }
}
