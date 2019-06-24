package com.swsadWeb.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *  task
 * @author wj 2019-06-23
 */
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * taskname
     */
    private String taskname;

    /**
     * starttime
     */
    private Date starttime;

    /**
     * endtime
     */
    private Date endtime;

    /**
     * state
     */
    private Integer state;

    /**
     * type
     */
    private Integer type;

    /**
     * release_user
     */
    private Long releaseUser;

    /**
     * accept_num_limit
     */
    private Integer acceptNumLimit;

    /**
     * has_target_limit
     */
    private Integer hasTargetLimit;

    private Long targetLimitId;

    /**
     * description
     */
    private String description;


    public Task() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getReleaseUser() {
        return releaseUser;
    }

    public void setReleaseUser(Long releaseUser) {
        this.releaseUser = releaseUser;
    }

    public Integer getAcceptNumLimit() {
        return acceptNumLimit;
    }

    public void setAcceptNumLimit(Integer acceptNumLimit) {
        this.acceptNumLimit = acceptNumLimit;
    }

    public Integer getHasTargetLimit() {
        return hasTargetLimit;
    }

    public void setHasTargetLimit(Integer hasTargetLimit) {
        this.hasTargetLimit = hasTargetLimit;
    }

    public Long getTargetLimitId() {
        return targetLimitId;
    }

    public void setTargetLimitId(Long targetLimitId) {
        this.targetLimitId = targetLimitId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}