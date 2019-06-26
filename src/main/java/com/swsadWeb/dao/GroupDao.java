package com.swsadWeb.dao;


import com.swsadWeb.entity.Group;

import java.util.List;

public interface GroupDao {
    void insertGroup(Group group);

    void updateByGroupId(Group group);

    List<Group> findByName(String name);

    Group findById(Long id);


}
