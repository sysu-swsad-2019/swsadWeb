package com.swsadWeb.service.impl;


import com.swsadWeb.dao.PermissionDao;
import com.swsadWeb.entity.Permission;
import com.swsadWeb.entity.Role;
import com.swsadWeb.service.PermissionService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @auther wangjing
 * @date 2019/6/13
 */

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Transactional
    @Override
    public void create(Permission permission) {
        permissionDao.create(permission);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        //先将和Permission相关的表数据删除
        permissionDao.deleteRolePermission(id);

        //再删除Permission表数据
        permissionDao.deletePermission(id);
    }

    @Transactional
    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    @Transactional
    @Override
    public void update(Permission permission) {
        permissionDao.update(permission);
    }

    @Transactional
    @Override
    public Permission findById(Long id) {
        return permissionDao.findById(id);
    }

    @Transactional
    @Override
    public List<Role> findRoleByPermissionId(Long id) {
        return permissionDao.findRoleByPermissionId(id);
    }

    @Transactional
    @Override
    public void deleteAllPermissionsRoles(Long id) {
        permissionDao.deleteAllPermissionsRoles(id);
    }

    @Transactional
    @Override
    public void correlationRoles(Long permissionId, Long roleId) {
        permissionDao.correlationRoles(permissionId, roleId);
    }

}

