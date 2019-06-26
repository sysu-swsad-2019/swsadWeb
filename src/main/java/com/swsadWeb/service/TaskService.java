package com.swsadWeb.service;

import com.swsadWeb.entity.Task;

import java.util.List;

public interface TaskService {

    void insertTask(Task task);

    void updateByTaskId(Task task);

    List<Long> getAllTaskId();

    Task getTaskById(Long id);
}
