package com.swsadWeb.dao;

import com.swsadWeb.entity.Task;

public interface TaskDao {

    void insertTask(Task task);

    void updateByTaskId(Task task);
}
