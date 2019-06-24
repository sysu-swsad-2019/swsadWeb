package com.swsadWeb.service.impl;

import com.swsadWeb.dao.RoleDao;
import com.swsadWeb.entity.Permission;
import com.swsadWeb.entity.Role;
import com.swsadWeb.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @auther wangjing
 * @date 2019/6/13
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Transactional
    @Override
    public void correlationPermissions(Long roleId, Long... permissionIds) {
        if(permissionIds == null || permissionIds.length == 0){
            return;
        }
        for(Long permissionId : permissionIds){
            if(!exists(roleId, permissionId)){
                roleDao.correlationPermissions(roleId,permissionId);
            }
        }
    }

    @Transactional
    @Override
    public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
        if(roleId == null || permissionIds.length == 0){
            return;
        }
        for(Long permissionId : permissionIds){
            if(exists(roleId, permissionId)){
                roleDao.uncorrelationPermissions(roleId, permissionId);
            }
        }
    }

    @Transactional
    @Override
    public Role findById(Long id) {
        return roleDao.findById(id);
    }


    @Transactional
    @Override
    public Role findByRole(String role){
        return roleDao.findByRole(role);
    }

    /**
     * 查询表中是否存在此数据
     * @param roleId
     * @param permissionId
     * @return
     */
    private boolean exists(Long roleId, Long permissionId) {
        return roleDao.exists(roleId, permissionId);
    }

    @Transactional
    @Override
    public void create(Role role) {
        if (role.getPid() == null){
            role.setPid(0L);
        }
        roleDao.create(role);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        //先将和角色相关的表删除
        roleDao.deleteUserRole(id);

        //再删除角色表数据
        roleDao.deleteRole(id);
    }

    @Transactional
    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Transactional
    @Override
    public List<Permission> findRolePermissionByRoleId(Long id) {
        return roleDao.findRolePermissionByRoleId(id);
    }

    @Transactional
    @Override
    public List<Permission> findPermissionByRoleId(Long id) {
        return roleDao.findPermissionByRoleId(id);
    }

    @Transactional
    @Override
    public void update(Role role) {
        roleDao.update(role);
    }

    @Transactional
    @Override
    public void deleteAllRolePermissions(Long id) {
        roleDao.deleteAllRolePermissions(id);
    }

    @Transactional
    @Override
    public void updateUserRole_Id(Role role) {
        roleDao.updateUserRole_Id(role);
    }

}

