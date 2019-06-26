package com.swsadWeb.controller;


import com.swsadWeb.entity.Group;
import com.swsadWeb.entity.Msg;
import com.swsadWeb.entity.User;
import com.swsadWeb.service.GroupService;
import com.swsadWeb.service.UserInfoService;
import com.swsadWeb.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/group")
public class GroupController {


    @Autowired
    private GroupService groupService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/insertGroup")
    @ResponseBody
    public Msg insertGroup(@RequestBody Group group) {

        groupService.insertGroup(group);

        return Msg.success("添加成功");
    }

    @RequestMapping(value = "/findById")
    @ResponseBody
    public Msg getGroupById(@RequestParam Long id) {

        Group group = groupService.findById(id);

        Map<String, Object> map = new HashMap<>();

        map.put("group", group);
        Msg msg = Msg.success("成功");
        msg.setData(map);

        return msg;
    }

    @RequestMapping(value = "/addUserInGroup")
    @ResponseBody
    public Msg addUserInGroup(@RequestParam(value = "groupId") Long groupId) {

        Subject subject = SecurityUtils.getSubject();

        String username = subject.getPrincipal().toString();

        User user = userService.findByName(username);

        userService.correlationGroup(user.getId(), groupId);


        return Msg.success("添加成功");
    }

    @RequestMapping(value = "/deleteUserInGroup")
    @ResponseBody
    public Msg deleteUserInGroup(@RequestParam(value = "groupId") Long groupId) {

        Subject subject = SecurityUtils.getSubject();

        String username = subject.getPrincipal().toString();

        User user = userService.findByName(username);

        userService.uncorrelationGroup(user.getId(), groupId);


        return Msg.success("删除成功");
    }




}
