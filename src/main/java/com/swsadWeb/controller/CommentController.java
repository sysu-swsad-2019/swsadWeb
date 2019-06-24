package com.swsadWeb.controller;

import com.swsadWeb.service.CommentService;
import com.swsadWeb.dao.UserDao;
import com.swsadWeb.entity.Comment;
import com.swsadWeb.entity.Msg;
import com.swsadWeb.service.CommentService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


@Controller
@RequestMapping("/comment")
@CrossOrigin
//这里用了@SessionAttributes，可以直接把model中的user(也就key)放入其中
//这样保证了session中存在user这个对象
public class CommentController {


    @Autowired
    private CommentService commentService;

    @RequestMapping("/insertComment")
    @ResponseBody
    public Msg insertComment(@RequestParam(value = "username", required = false)String username,
                             @RequestParam(value = "groupid", required = false)String groupid,
                             @RequestParam(value = "commentDetail", required = false)String commentDetail) {

        System.out.println(username);

        System.out.println(groupid);

        System.out.println(commentDetail);

        if (username == null || groupid == null || commentDetail == null){
            return Msg.error("插入失败");
        }

        Comment comment = new Comment();
        comment.setCommentDetail(commentDetail);
        comment.setGroupid(groupid);
        //Subject subject = SecurityUtils.getSubject();
        //String username = subject.getPrincipals().toString();
        comment.setUsername(username);


        //DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss", Locale.ENGLISH);

        Timestamp timestamp = new Timestamp(new Date().getTime());

        comment.setCommentTime(timestamp);

        commentService.insertComment(comment);

        Map<String, Object> map = new HashMap<>();

        map.put("id", comment.getId());

        Msg msg = Msg.success("插入成功");

        msg.setData(map);


        return msg;
    }

    @RequestMapping("/getCommentByGroupId")
    @ResponseBody
    public Msg getCommentByGroupId(@RequestParam(value = "groupid")String groupid) {


        List<Comment> list;

        list = commentService.getCommentByGroupId(groupid);

        Map<String, Object> map = new HashMap<>();

        map.put("comments", list);

        Msg msg = Msg.success("获取成功");

        msg.setData(map);


        return msg;
    }

    @RequestMapping("/getCommentByUsername")
    @ResponseBody
    public Msg getCommentByUsername(@RequestParam(value = "username")String username) {


        List<Comment> list;

        list = commentService.getCommentByUsername(username);

        Map<String, Object> map = new HashMap<>();

        map.put("comments", list);

        Msg msg = Msg.success("获取成功");

        msg.setData(map);


        return msg;
    }


    @RequestMapping("/deleteComment")
    @ResponseBody
    public Msg deleteComment(@RequestParam(value = "id", required = false)Long id) {



        commentService.deleteComment(id);

        Msg msg = Msg.success("删除成功");


        return msg;
    }

}