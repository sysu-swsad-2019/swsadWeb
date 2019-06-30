package com.swsadWeb.controller;


import com.swsadWeb.entity.*;
import com.swsadWeb.service.GroupService;
import com.swsadWeb.service.UserInfoService;
import com.swsadWeb.service.UserService;
import com.swsadWeb.util.FileUploadUtil;
import javafx.scene.chart.ValueAxis;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.quartz.impl.jdbcjobstore.MSSQLDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/group")
public class GroupController {

    private static final Log logger = LogFactory.getLog(UserInfoController.class);

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

        userService.correlationGroup(user.getId(), group.getId());
        Map<String, Object> map = new HashMap<>();
        map.put("groupId", group.getId());

        Msg msg = Msg.success("添加成功");
        msg.setData(map);

        return msg;
    }

    @RequestMapping(value = "/findById")
    @ResponseBody
    public Msg findById(@RequestParam(value = "groupId") Long groupId) {

        Group group = groupService.findById(groupId);

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
        List<UserInfo> list = groupService.findAllUserInGroup(groupId);
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);

        Msg msg = Msg.success("返回成功");
        msg.setData(map);
        return msg;
    }

    @RequestMapping(value = "/findAllTaskInGroup")
    @ResponseBody
    public Msg findAllTaskInGroup(@RequestParam(value = "groupId")Long groupId) {
        List<Task> list = groupService.findAllTaskInGroup(groupId);
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

    @RequiresRoles(value={"admin","user"}, logical = Logical.OR)
    @RequestMapping(value = "/setGroupIcon", method = RequestMethod.POST)
    @ResponseBody
    public Msg setGroupIcon(@RequestParam(value = "groupId")Long groupId,HttpServletRequest request) {

        Group group = groupService.findById(groupId);

        // 创建list集合用于获取文件上传返回路径名

        String iconPath = "";
        try {

            // 获取上传完文件返回的路径,判断module模块名称是否为空，如果为空则给default作为文件夹名
            iconPath = FileUploadUtil.uploadGroupIcon(request, groupId);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            logger.error("上传文件发生错误=》》" + e.getMessage());

        }

        group.setIconpath(iconPath);

        groupService.updateByGroupId(group);

        // 转发到uploadTest.jsp页面
        // 返回存有路径的List
        Msg msg = Msg.success("上传成功");
        Map<String, Object> map = new HashMap<>();
        map.put("iconURL", iconPath);
        msg.setData(map);
        return msg;
    }

    @RequestMapping(value = "/updateGroupById")
    @ResponseBody
    public Msg updateGroupById(@RequestBody Group group){
        groupService.updateByGroupId(group);
        return Msg.success("设置成功");
    }

    @RequestMapping(value = "/findAllGroupByUser")
    @ResponseBody
    public Msg findAllGroupByUser(@RequestParam(value = "userId")Long userId){
        List<Group> list = groupService.findAllGroupByUser(userId);
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        Msg msg = Msg.success("查找成功");
        msg.setData(map);
        return msg;
    }




}
