package com.swsadWeb.service;

import com.swsadWeb.entity.Permission;
import com.swsadWeb.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther wangjing
 * @date 2019/6/13
 */
public interface PermissionService {

    /**
     * 创建权限
     *
     * @param
     */
    void create(Permission permission);

    /**
     * 根据ID删除信息
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 查询所有
     *
     * @return
     */
    List<Permission> findAll();

    /**
     * 更新信息
     *
     * @param
     */
    void update(Permission permission);

    /**
     * 根据ID查询其所有数据
     *
     * @param id
     * @return
     */
    Permission findById(Long id);

    /**
     * 根据权限id查询其所关联的角色数据
     *
     * @param id
     * @return
     */
    List<Role> findRoleByPermissionId(Long id);

    /**
     * 删除此权限关联的所有角色id
     *
     * @param id
     */
    void deleteAllPermissionsRoles(Long id);

    /**
     * 更新此角色的权限依赖关系
     *
     * @param permissionId
     * @param roleId
     */
    void correlationRoles(Long permissionId, Long roleId);

}
