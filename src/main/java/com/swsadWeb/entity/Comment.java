package com.swsadWeb.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.sql.Timestamp;

/**
 *  comments
 * @author 大狼狗 2019-06-21
 */
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * groupid
     */
    private String groupid;

    /**
     * username
     */
    private String username;

    /**
     * _detail varchar(255)
     */
    private String commentDetail;

    /**
     * _time datetime
     */
    private Timestamp commentTime;


    public Comment() {
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCommentDetail() {
        return commentDetail;
    }

    public void setCommentDetail(String commentDetail) {
        this.commentDetail = commentDetail;
    }

    public Timestamp getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Timestamp commentTime) {
        this.commentTime = commentTime;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", groupid='" + groupid + '\'' +
                ", username='" + username + '\'' +
                ", commentDetail=" + commentDetail + '\'' +
                ", commentTime=" + commentTime.toString() +
                '}';
    }

}
