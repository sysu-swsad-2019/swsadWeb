package com.swsadWeb.service.impl;

import com.swsadWeb.dao.GroupDao;
import com.swsadWeb.entity.Group;
import com.swsadWeb.entity.Task;
import com.swsadWeb.entity.User;
import com.swsadWeb.entity.UserInfo;
import com.swsadWeb.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupDao groupDao;

    @Override
    public void insertGroup(Group group) {
        groupDao.insertGroup(group);
    }

    @Override
    public void updateByGroupId(Group group) {
        groupDao.updateByGroupId(group);
    }


    //can use pagehelper
    @Override
    public List<Group> findByName(String name) {
        return groupDao.findByName(name);
    }

    @Override
    public Group findById(Long id){
        return groupDao.findById(id);
    }

    @Override
    public List<UserInfo> findAllUserInGroup(Long groupId){
        return groupDao.findAllUserInGroup(groupId);
    }

    @Override
    public List<Group> findAllGroup(){
        return groupDao.findAllGroup();
    }

    @Override
    public List<Task> findAllTaskInGroup(Long groupId){
        return groupDao.findAllTaskInGroup(groupId);
    }

    @Override
    public List<Group> findAllGroupByUser(Long userId){
        return groupDao.findAllGroupByUser(userId);
    }
}
