package com.swsadWeb.controller;


import com.swsadWeb.entity.Msg;
import com.swsadWeb.entity.Task;
import com.swsadWeb.entity.User;
import com.swsadWeb.service.TaskService;
import com.swsadWeb.service.UserInfoService;
import com.swsadWeb.service.UserService;
import javafx.scene.chart.ValueAxis;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/task")
public class TaskContoller {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;


    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/insertTask")
    @ResponseBody
    public Msg insertTask(@RequestBody Task task){

        task.setStarttime(new Timestamp(new Date().getTime()));

        System.out.println(task.getEndtime());

        taskService.insertTask(task);


        return Msg.success("添加成功");
    }

    @RequestMapping(value = "/addUserInTask")
    @ResponseBody
    public Msg addUserInTask(@RequestParam(value = "groupId") Long groupId) {

        Subject subject = SecurityUtils.getSubject();

        String username = subject.getPrincipal().toString();

        User user = userService.findByName(username);

        userService.correlationTask(user.getId(), groupId);


        return Msg.success("添加成功");
    }

    @RequestMapping(value = "/deleteUserInTask")
    @ResponseBody
    public Msg deleteUserInTask(@RequestParam(value = "groupId") Long groupId) {

        Subject subject = SecurityUtils.getSubject();

        String username = subject.getPrincipal().toString();

        User user = userService.findByName(username);

        userService.uncorrelationTask(user.getId(), groupId);


        return Msg.success("删除成功");
    }

    @RequestMapping(value = "/getAllTaskId")
    @ResponseBody
    public Msg getAllTaskId(){
        List<Long> list = taskService.getAllTaskId();

        Map<String, Object> map = new HashMap<>();

        map.put("taskIdList", list);

        Msg msg = Msg.success("成功");

        msg.setData(map);

        return msg;
    }

    @RequestMapping(value = "/getTaskById")
    @ResponseBody
    public Msg getTaskById(@RequestParam(value = "id")Long id){
        Task task= taskService.getTaskById(id);

        Map<String, Object> map = new HashMap<>();

        map.put("task", task);

        Msg msg = Msg.success("成功");

        msg.setData(map);

        return msg;
    }
}
