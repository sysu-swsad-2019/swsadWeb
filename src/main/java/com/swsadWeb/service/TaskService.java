package com.swsadWeb.service;

import com.swsadWeb.entity.Task;
import com.swsadWeb.entity.User;

import java.util.List;

public interface TaskService {

    void insertTask(Task task);

    void updateByTaskId(Task task);

    List<Long> getAllTaskId();

    Task getTaskById(Long id);

    List<Task> getTaskByUser(Long userId);

    List<User> findAllUserInTask(Long taskId);

    List<Task> getReleaseTaskByUser(Long userId);

    List<Task> getAllTask();
}
