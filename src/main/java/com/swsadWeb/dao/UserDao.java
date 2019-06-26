package com.swsadWeb.dao;

import com.swsadWeb.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @auther wangjing
 * @date 2019/6/13
 */
public interface UserDao {

    void correlationRoles(@Param("userId") Long userId,
                          @Param("roleId") Long roleId);

    void uncorrelationRoles(@Param("userId") Long userId,
                            @Param("roleId") Long roleId);

    List<Role> findRoles(String username);

    List<Permission> findPermissions(String username);

    boolean exists(@Param("userId") Long userId,
                   @Param("roleId") Long roleId);

    void create(User user);

    void delete(Long id);

    void update(User user);

    List<User> findAll();

    User findByName(String username);

    User findById(Long id);

    void deleteAllUserRoles(Long id);


    void correlationTask(@Param("userId") Long userId,
                         @Param("taskId") Long taskId);

    void uncorrelationTask(@Param("userId") Long userId,
                           @Param("taskId") Long taskId);


    void correlationGroup(@Param("userId") Long userId,
                         @Param("groupId") Long groupId);

    void uncorrelationGroup(@Param("userId") Long userId,
                           @Param("groupId") Long groupId);

    UserTask doesUserIsInTask(@Param("userId") Long userId,
                              @Param("groupId") Long taskId);

    UserGroup doesUserIsInGroup(@Param("userId") Long userId,
                                @Param("groupId") Long groupId);



}
