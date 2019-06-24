package com.swsadWeb.dao;

import com.swsadWeb.entity.Permission;
import com.swsadWeb.entity.Role;

import java.util.List;
import org.apache.ibatis.annotations.Param;


/**
 * @auther wangjing
 * @date 2019/6/13
 */

public interface PermissionDao {

    void create(Permission permission);

    void deleteRolePermission(Long permissionId);

    void deletePermission(Long permissionId);

    List<Permission> findAll();

    void update(Permission permission);

    Permission findById(Long id);

    List<Role> findRoleByPermissionId(Long id);

    void deleteAllPermissionsRoles(Long id);

    void correlationRoles(@Param("permissionId") Long permissionId,
                          @Param("roleId") Long roleId);

}
