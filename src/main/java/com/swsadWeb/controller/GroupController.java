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
import java.util.List;
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
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.findByName(username);
        group.setCreator(user.getId());

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

        Group group = groupService.findById(groupId);
        if (group==null){
            return Msg.error("发生错误");
        }


        Subject subject = SecurityUtils.getSubject();

        String username = subject.getPrincipal().toString();

        User user = userService.findByName(username);

        if (userService.doesUserIsInGroup(user.getId(), groupId)!=null){
            return Msg.error("已经加入");
        }

        userService.correlationGroup(user.getId(), groupId);


        return Msg.success("添加成功");
    }

    @RequestMapping(value = "/addUserInGroupByCreator")
    @ResponseBody
    public Msg addUserInGroupByCreator(@RequestParam(value = "userId")Long userId,
                                     @RequestParam(value = "groupId") Long groupId) {

        Group group = groupService.findById(groupId);
        if (group==null){
            return Msg.error("发生错误");
        }

        String username = SecurityUtils.getSubject().getPrincipal().toString();

        User user = userService.findByName(username);

        if (userService.doesUserIsInGroup(userId, groupId)!=null){
            return Msg.error("已经加入");
        }

        if (!user.getId().equals(group.getCreator())){
            return Msg.error("你不是管理员");
        }

        userService.correlationGroup(userId, groupId);


        return Msg.success("添加成功");
    }

    @RequestMapping(value = "/deleteUserInGroup")
    @ResponseBody
    public Msg deleteUserInGroup(@RequestParam(value = "groupId") Long groupId) {

        Group group = groupService.findById(groupId);
        if (group==null){
            return Msg.error("发生错误");
        }

        Subject subject = SecurityUtils.getSubject();

        String username = subject.getPrincipal().toString();

        User user = userService.findByName(username);

        if (userService.doesUserIsInGroup(user.getId(), groupId)==null){
            return Msg.error("尚未加入");
        }

        userService.uncorrelationGroup(user.getId(), groupId);


        return Msg.success("删除成功");
    }

    @RequestMapping(value = "/deleteUserInGroupByCreator")
    @ResponseBody
    public Msg deleteUserInGroupByCreator(@RequestParam(value = "userId")Long userId,
                                          @RequestParam(value = "groupId") Long groupId) {

        Group group = groupService.findById(groupId);
        if (group==null){
            return Msg.error("发生错误");
        }

        String username = SecurityUtils.getSubject().getPrincipal().toString();

        User user = userService.findByName(username);

        if (userService.doesUserIsInGroup(userId, groupId)==null){
            return Msg.error("尚未加入");
        }

        if (!user.getId().equals(group.getCreator())){
            return Msg.error("你不是管理员");
        }

        userService.uncorrelationGroup(userId, groupId);


        return Msg.success("删除成功");
    }


    @RequestMapping(value = "/findAllUserInGroup")
    @ResponseBody
    public Msg findAllUserInGroup(@RequestParam(value = "groupId")Long groupId) {
        List<User> list = groupService.findAllUserInGroup(groupId);
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);

        Msg msg = Msg.success("返回成功");
        msg.setData(map);
        return msg;
    }



    @RequestMapping(value = "/findAllGroup")
    @ResponseBody
    public Msg findAllGroup() {
        List<Group> list = groupService.findAllGroup();
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        Msg msg = Msg.success("查找成功");
        msg.setData(map);
        return msg;
    }




}
