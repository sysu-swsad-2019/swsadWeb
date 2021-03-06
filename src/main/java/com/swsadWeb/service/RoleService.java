package com.swsadWeb.service;

import com.swsadWeb.entity.Permission;
import com.swsadWeb.entity.Role;

import java.util.List;

/**
 * @auther wangjing
 * @date 2019/6/13
 */
public interface RoleService{

    /**
     * 添加角色-权限之间关系
     *
     * @param roleId
     * @param permissionIds
     */
    void correlationPermissions(Long roleId, Long... permissionIds);

    /**
     * 移除角色-权限之间关系
     *
     * @param roleId
     * @param permissionIds
     */
    void uncorrelationPermissions(Long roleId, Long... permissionIds);

    /**
     * 根据角色id查询当前role-permission表中关联的数据
     *
     * @return
     */
    List<Permission> findRolePermissionByRoleId(Long id);

    /**
     * 根据角色id查询permission表中属于此角色节点的子节点权限
     *
     * @param id
     * @return
     */
    List<Permission> findPermissionByRoleId(Long id);

    /**
     * 删除此角色所有关联的权限id
     *
     * @param id
     */
    void deleteAllRolePermissions(Long id);

    /**
     * 通过当前角色的更新数据，更新用户表中显示关联角色的字段
     *
     * @param role
     */
    void updateUserRole_Id(Role role);



    /**
     * 根据ID查询其所有数据
     *
     * @param id
     * @return
     */
    Role findById(Long id);

    /**
     * 根据Role字段查询其所有数据
     *
     * @param role
     * @return
     */
    Role findByRole(String role);

    /**
     * 创建用户
     *
     * @param
     */
    void create(Role role);

    /**
     * 根据用户ID删除用户信息
     *
     * @param id
     */
    void delete(Long id);


    /**
     * 查询所有
     *
     * @return
     */
    List<Role> findAll();

    /**
     * 更新用户信息
     *
     * @param
     */
    void update(Role role);



}
