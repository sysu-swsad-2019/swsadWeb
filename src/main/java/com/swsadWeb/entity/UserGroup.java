package com.swsadWeb.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *  user_group
 * @author wj 2019-06-22
 */
public class UserGroup implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * user_id
     */
    private Long userId;

    /**
     * group_id
     */
    private Long groupId;


    public UserGroup() {
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

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

}