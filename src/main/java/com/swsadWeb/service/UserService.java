package com.swsadWeb.service;

import com.swsadWeb.entity.Permission;
import com.swsadWeb.entity.Role;
import com.swsadWeb.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @auther wangjing
 * @date 2019/6/5
 */
public interface UserService{

    /**
     * 添加用户-角色关系
     *
     * @param userId
     * @param roleIds
     */
    void correlationRoles(Long userId, Long... roleIds);

    /**
     * 移除用户-角色关系
     *
     * @param userId
     * @param roleIds
     */
    void uncorrelationRoles(Long userId, Long... roleIds);


    void correlationTask(Long userId, Long taskId);

    void uncorrelationTask(Long userId, Long taskId);


    void correlationGroup(Long userId, Long groupId);

    void uncorrelationGroup(Long userId, Long groupId);

    /**
     * 根据用户名查找其他角色
     *
     * @param username
     * @return
     */
    List<Role> findRoles(String username);

    /**
     * 根据用户名查找其他权限
     *
     * @param username
     * @return
     */
    List<Permission> findPermissions(String username);

    /**
     * 修改密码
     *
     * @param userId
     * @param newPassword
     */
    void changePassword(Long userId, String newPassword);

    /**
     * 根据用户名查询
     *
     * @param username
     * @return
     */
    User findByName(String username);

    /**
     * 删除此用户关联的所有角色信息
     * @param id
     */
    void deleteAllUserRoles(Long id);

    void create(User user);

    void delete(Long id);

    void update(User user);

    List<User> findAll();

    User findById(Long id);

}
