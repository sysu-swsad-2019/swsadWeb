package com.swsadWeb.service;

import com.swsadWeb.entity.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;



public interface CommentService {

    List<Comment> getCommentByUsername(String username);

    List<Comment> getCommentByGroupId(String groupid);

    void insertComment(Comment comment);

    void deleteComment(Long id);
}
