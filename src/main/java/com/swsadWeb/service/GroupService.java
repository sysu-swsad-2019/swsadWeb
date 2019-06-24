package com.swsadWeb.service;

import com.swsadWeb.entity.Group;
import org.springframework.stereotype.Service;

import java.util.List;


public interface GroupService {
    void insertGroup(Group group);

    void updateByGroupId(Group group);

    List<Group> findByName(String name);
}

