package com.swsadWeb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.swsadWeb.dao.CommentDao;
import com.swsadWeb.entity.Comment;
import com.swsadWeb.entity.Role;
import com.swsadWeb.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    @Transactional
    public void insertComment(Comment comment){
        commentDao.insertComment(comment);
        System.out.println("Insert a new comment, it's id is: "+ comment.getId());
    }

    @Override
    @Transactional
    public void deleteComment(Long id){
        commentDao.deleteComment(id);
    }

    @Override
    @Transactional
    public List<Comment> getCommentByUsername(String username){


        //使用分页PageHelper
        PageHelper.startPage(1,10);
        List<Comment> list = commentDao.getCommentByUsername(username);

        //Map<String, Object> map = null;
        //PageInfo<Comment> newsPageInfo = null;

        return list;
    }

    @Override
    @Transactional
    public List<Comment> getCommentByGroupId(String groupid){
        return commentDao.getCommentByGroupId(groupid);
    }

}
