package com.swsadWeb.service;

import com.swsadWeb.entity.Task;

public interface TaskService {

    void insertTask(Task task);

    void updateByTaskId(Task task);
}
