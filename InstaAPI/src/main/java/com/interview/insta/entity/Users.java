package com.interview.insta.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "Users")
public class Users implements Serializable {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable=false)
    private String userName;
    @Column(nullable=false)
    private String userFirstName;
    @Column(nullable=false)
    private String userLastName;
    @Column(nullable=false)
    private String userPassword;
    private String profileType;

    // @OneToMany(mappedBy="userid",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    // private Collection<ImageDetails> imagedetails;


    public Users(int id, String userName, String userFirstName, String userLastName, String userPassword,
            String profileType) {
        this.id = id;
        this.userName = userName;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userPassword = userPassword;
        this.profileType = profileType;
    }
    public Users(){
        
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserFirstName() {
        return userFirstName;
    }
    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }
    public String getUserLastName() {
        return userLastName;
    }
    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }
    public String getUserPassword() {
        return userPassword;
    }
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    public String getProfileType() {
        return profileType;
    }
    public void setProfileType(String profileType) {
        this.profileType = profileType;
    }

    @Override
    public String toString() {
        
        return String.valueOf(getId());
    }
}
