package com.swsadWeb.dao;

import com.swsadWeb.entity.Task;

import java.util.List;

public interface TaskDao {

    void insertTask(Task task);

    void updateByTaskId(Task task);

    List<Long> getAllTaskId();

    Task getTaskById(Long id);
}
