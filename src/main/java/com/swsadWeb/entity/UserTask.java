package com.swsadWeb.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *  user_task
 * @author wj 2019-06-23
 */
public class UserTask implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * user_id
     */
    private Long userId;

    /**
     * task_id
     */
    private Long taskId;


    public UserTask() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

}