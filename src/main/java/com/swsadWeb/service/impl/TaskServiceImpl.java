package com.swsadWeb.service.impl;

import com.swsadWeb.dao.TaskDao;
import com.swsadWeb.entity.Task;
import com.swsadWeb.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDao taskDao;

    @Override
    public void insertTask(Task task){
        taskDao.insertTask(task);
    }

    @Override
    public void updateByTaskId(Task task){
        taskDao.updateByTaskId(task);
    }

    @Override
    public List<Long> getAllTaskId() {
        return taskDao.getAllTaskId();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskDao.getTaskById(id);
    }
}
