package com.swsadWeb.entity;

import java.io.Serializable;

/**
 * @auther wangjing
 * @date 2019/6/13
 */
public class UserRole implements Serializable {

    private Long id; //编号
    private Long userId; //用户ID
    private Long roleId; //角色ID

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

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                '}';
    }
}
