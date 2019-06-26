package com.swsadWeb.dao;


import com.swsadWeb.entity.Group;
import com.swsadWeb.entity.User;

import java.util.List;

public interface GroupDao {
    void insertGroup(Group group);

    void updateByGroupId(Group group);

    List<Group> findByName(String name);

    Group findById(Long id);

    List<User> findAllUserInGroup(Long groupId);

    List<Group> findAllGroup();

}
