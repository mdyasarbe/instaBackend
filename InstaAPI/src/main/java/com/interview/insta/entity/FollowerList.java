package com.interview.insta.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "Followerlist")
public class FollowerList implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne()
    @JoinColumn(name="parentid", referencedColumnName = "id",nullable = false) 
    private Users parentid;

    @ManyToOne()
    @JoinColumn(name="childid", referencedColumnName = "id",nullable = false) 
    private Users childid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Users getParentid() {
        return parentid;
    }

    public void setParentid(Users parentid) {
        this.parentid = parentid;
    }

    public Users getChildid() {
        return childid;
    }

    public void setChildid(Users childid) {
        this.childid = childid;
    }
    
}
