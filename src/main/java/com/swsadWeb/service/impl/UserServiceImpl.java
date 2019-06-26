package com.swsadWeb.service.impl;

import com.swsadWeb.dao.UserDao;
import com.swsadWeb.entity.Permission;
import com.swsadWeb.entity.Role;
import com.swsadWeb.entity.User;
import com.swsadWeb.service.UserService;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @auther wangjing
 * @date 2019/6/13
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordHelper passwordHelper;

    /**
     * 添加用户-角色关系
     *
     * @param userId
     * @param roleIds
     */
    @Transactional
    public void correlationRoles(Long userId, Long... roleIds) {
        if (roleIds == null || roleIds.length == 0) {
            return;
        }
        for (Long roleId: roleIds) {
            if (!exists(userId, roleId)) {
                userDao.correlationRoles(userId, roleId);
            }
        }
    }

    /**
     * 移除用户-角色关系
     *
     * @param userId
     * @param roleIds
     */
    @Transactional
    public void uncorrelationRoles(Long userId, Long... roleIds) {
        if (roleIds == null || roleIds.length == 0) {
            return;
        }
        for (Long roleId: roleIds) {
            if (exists(userId, roleId)) {
                userDao.uncorrelationRoles(userId, roleId);
            }
        }
    }

    /**
     * 判断当前的用户和角色是否存在
     *
     * @param userId
     * @param roleId
     * @return
     */
    @Transactional
    public boolean exists(Long userId, Long roleId) {
        return userDao.exists(userId, roleId);
    }

    @Transactional
    @Override
    public List<Role> findRoles(String username) {
        return userDao.findRoles(username);
    }

    @Transactional
    @Override
    public List<Permission> findPermissions(String username) {
        return userDao.findPermissions(username);
    }

    @Transactional
    @Override
    public void changePassword(Long id, String newPassword) {
        //User user = ((UserServiceImpl)AopContext.currentProxy()).findById(id);
        User user = userDao.findById(id);

        // 因为数据库中`locked`字段使用的类型：`tinyint(1)`，
        // 那么使用mybatis查询数据库会自动将数据转换成boolean类型(使用了boolean类型接收)，0：false；1或其他非零数字：true
        System.out.println("是否锁定：" + user.getLocked());

        if (user==null){
            return;
        }
        user.setPassword(newPassword);
        passwordHelper.encryptPassword(user);
        userDao.update(user);
    }

    @Transactional
    @Override
    public void create(User user) {
        //加密密码
        passwordHelper.encryptPassword(user);
        userDao.create(user);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Transactional
    @Override
    public void update(User user) {
        //加密密码
        passwordHelper.encryptPassword(user);
        userDao.update(user);
    }

    @Transactional
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Transactional
    @Override
    public User findByName(String username) {
        User user = userDao.findByName(username);
        if (user == null) {
            return null;
        }
        return user;
    }

    @Transactional
    @Override
    public User findById(Long id) {
        User user = userDao.findById(id);

        // 因为数据库中`locked`字段使用的类型：`tinyint(1)`，
        // 那么使用mybatis查询数据库会自动将数据转换成boolean类型(使用了boolean类型接收)，0：false；1或其他非零数字：true
        System.out.println("是否锁定：" + user.getLocked());

        if (user == null) {
            return null;
        }
        return user;
    }

    @Transactional
    @Override
    public void deleteAllUserRoles(Long id) {
        userDao.deleteAllUserRoles(id);
    }


    @Transactional
    @Override
    public void correlationTask(Long userId, Long taskId){

        userDao.correlationTask(userId, taskId);
    }

    @Transactional
    @Override
    public void uncorrelationTask(Long userId, Long taskId){
        userDao.uncorrelationTask(userId, taskId);
    }


    @Transactional
    @Override
    public void correlationGroup(Long userId, Long groupId){
        userDao.correlationGroup(userId, groupId);
    }


    @Transactional
    @Override
    public void uncorrelationGroup(Long userId, Long groupId){
        userDao.uncorrelationGroup(userId, groupId);
    }


}

