package com.swsadWeb.dao;

import com.swsadWeb.entity.Permission;
import com.swsadWeb.entity.Role;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * @auther wangjing
 * @date 2019/6/13
 */
public interface RoleDao {

    void correlationPermissions(@Param("roleId") Long roleId,
                                @Param("permissionId") Long permissionId);

    void uncorrelationPermissions(@Param("roleId") Long roleId,
                                  @Param("permissionId") Long permissionId);

    boolean exists(@Param("roleId") Long roleId,
                   @Param("permissionId") Long permissionId);

    Role findById(Long id);

    Role findByRole(String role);

    void create(Role role);

    void deleteUserRole(Long roleId);

    void deleteRole(Long roleId);

    List<Role> findAll();

    List<Permission> findRolePermissionByRoleId(Long id);

    List<Permission> findPermissionByRoleId(Long id);

    void update(Role role);

    void deleteAllRolePermissions(Long id);

    void updateUserRole_Id(Role role);
}
