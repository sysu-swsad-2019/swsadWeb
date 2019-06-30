package com.swsadWeb.dao;


import com.swsadWeb.entity.Group;
import com.swsadWeb.entity.Task;
import com.swsadWeb.entity.User;
import com.swsadWeb.entity.UserInfo;

import java.util.List;

public interface GroupDao {
    void insertGroup(Group group);

    void updateByGroupId(Group group);

    List<Group> findByName(String name);

    Group findById(Long id);

    List<UserInfo> findAllUserInGroup(Long groupId);

    List<Group> findAllGroup();

    List<Task> findAllTaskInGroup(Long groupId);

    List<Group> findAllGroupByUser(Long userId);

}
