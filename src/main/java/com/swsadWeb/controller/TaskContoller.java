package com.swsadWeb.controller;


import com.swsadWeb.entity.*;
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
import org.w3c.dom.ls.LSException;

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

        Subject subject = SecurityUtils.getSubject();

        String username = subject.getPrincipal().toString();

        User user = userService.findByName(username);

        task.setStarttime(new Timestamp(new Date().getTime()));

        task.setReleaseUser(user.getId());

        System.out.println(task.getEndtime());

        taskService.insertTask(task);


        return Msg.success("添加成功");
    }

    @RequestMapping(value = "/addUserInTask")
    @ResponseBody
    public Msg addUserInTask(@RequestParam(value = "taskId") Long taskId) {

        Task task = taskService.getTaskById(taskId);
        if (task==null){
            return Msg.error("发生错误");
        }

        Subject subject = SecurityUtils.getSubject();

        String username = subject.getPrincipal().toString();

        UserInfo userInfo = userInfoService.findByUsername(username);

        if (userInfo.getId().equals(task.getReleaseUser())){
            return Msg.error("不能接收自己发布的任务");
        }

        if (userService.doesUserIsInTask(userInfo.getId(), taskId)!=null){
            return Msg.error("已经加入");
        }

        if (task.getCreditMin()==0){

        }else if (task.getCreditMin()==1){
            if (userInfo.getCredit() < 100){
                return Msg.error("信誉不足");
            }
        }else if (task.getCreditMin()==2){
            if (userInfo.getCredit() < 95){
                return Msg.error("信誉不足");
            }
        }else if (task.getCreditMin()==3){
            if (userInfo.getCredit() < 90){
                return Msg.error("信誉不足");
            }
        }else {
            return Msg.error("信誉设置错误");
        }

        userService.correlationTask(userInfo.getId(), taskId);


        return Msg.success("添加成功");
    }

    @RequestMapping(value = "/deleteUserInTask")
    @ResponseBody
    public Msg deleteUserInTask(@RequestParam(value = "taskId") Long taskId) {

        Subject subject = SecurityUtils.getSubject();

        String username = subject.getPrincipal().toString();


        User user = userService.findByName(username);

        if (userService.doesUserIsInTask(user.getId(), taskId)==null){
            return Msg.error("尚未加入");
        }

        userService.uncorrelationTask(user.getId(), taskId);


        return Msg.success("删除成功");
    }

    @RequestMapping(value = "/finishTask")
    @ResponseBody
    public Msg deleteUserInTask(@RequestParam(value = "userId") Long userId,
                                @RequestParam(value = "taskId") Long taskId) {

        Subject subject = SecurityUtils.getSubject();

        String username = subject.getPrincipal().toString();


        UserInfo userInfo = userInfoService.findByUsername(username);

        UserInfo finishUser = userInfoService.findById(userId);

        Task task = taskService.getTaskById(taskId);

        if (userService.doesUserIsInTask(finishUser.getId(), taskId)==null){
            return Msg.error("尚未加入");
        }
        if (task.getReward() > userInfo.getMoney()){
            return Msg.error("钱不够");
        }

        task.setState(1);

        taskService.updateByTaskId(task);

        userService.uncorrelationTask(finishUser.getId(), taskId);

        finishUser.setMoney(finishUser.getMoney() + task.getReward());
        userInfo.setMoney(userInfo.getMoney() - task.getReward());

        userInfoService.updateUserInfo(finishUser);
        userInfoService.updateUserInfo(userInfo);

        return Msg.success("成功结束任务");
    }

    @RequestMapping(value = "/getAllTaskId")
    @ResponseBody
    public Msg getAllTaskId(){
        List<Long> list = taskService.getAllTaskId();

        Map<String, Object> map = new HashMap<>();

        map.put("taskIdList", list);
        map.put("count", list.size());

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


    //the task the user join in
    @RequestMapping(value = "/getTaskByUsername")
    @ResponseBody
    public Msg getTaskByUsername() {
        Subject subject = SecurityUtils.getSubject();

        String username = subject.getPrincipal().toString();

        User user = userService.findByName(username);

        List<Task> list = taskService.getTaskByUser(user.getId());

        Msg msg = Msg.success("查找成功");

        Map<String, Object> map = new HashMap<>();

        map.put("list", list);

        msg.setData(map);

        return msg;
    }


    @RequestMapping(value = "/findAllUserInTask")
    @ResponseBody
    public Msg findAllUserInTask(@RequestParam(value = "taskId")Long taskId){
        Task task = taskService.getTaskById(taskId);
        if (task==null){
            return Msg.error("发生错误");
        }

        List<User> list = taskService.findAllUserInTask(taskId);
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        map.put("count", list.size());
        Msg msg = Msg.success("查找成功");
        msg.setData(map);
        return msg;
    }


    //the task the user release
    @RequestMapping(value = "/getReleaseTaskByUser")
    @ResponseBody
    public Msg getReleaseTaskByUser() {
        Subject subject = SecurityUtils.getSubject();

        String username = subject.getPrincipal().toString();

        User user = userService.findByName(username);

        List<Task> list = taskService.getReleaseTaskByUser(user.getId());

        Msg msg = Msg.success("查找成功");

        Map<String, Object> map = new HashMap<>();

        map.put("list", list);

        msg.setData(map);

        return msg;
    }

    @RequestMapping(value = "/getAllTask")
    @ResponseBody
    public Msg getAllTask() {
        List<Task> list = taskService.getAllTask();
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        map.put("count", list.size());
        Msg msg = Msg.success("成功");
        msg.setData(map);
        return msg;
    }
}
