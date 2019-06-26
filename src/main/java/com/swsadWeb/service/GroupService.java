package com.swsadWeb.service;

import com.swsadWeb.entity.Group;
import com.swsadWeb.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface GroupService {
    void insertGroup(Group group);

    void updateByGroupId(Group group);

    List<Group> findByName(String name);

    Group findById(Long id);

    List<User> findAllUserInGroup(Long groupId);

    List<Group> findAllGroup();
}

