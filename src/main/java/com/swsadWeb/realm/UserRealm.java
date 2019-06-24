package com.swsadWeb.realm;

import com.swsadWeb.entity.Permission;
import com.swsadWeb.entity.Role;
import com.swsadWeb.entity.User;
import com.swsadWeb.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @auther wangjing
 * @date 2019/6/13
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 权限校验
     * @param principals
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {


        System.out.println("权限校验--执行了doGetAuthorizationInfo...");

        String username = (String) principals.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //注意这里的setRoles和setStringPermissions需要的都是一个Set<String>类型参数
        Set<String> role = new HashSet<String>();
        List<Role> roles = userService.findRoles(username);
        for (Role r : roles){
            role.add(r.getRole());
        }
        authorizationInfo.setRoles(role);
        Set<String> permission = new HashSet<String>();
        List<Permission> permissions = userService.findPermissions(username);
        for (Permission p : permissions){
            permission.add(p.getPermission());
        }
        authorizationInfo.setStringPermissions(permission);

        return authorizationInfo;
    }

    /**
     * 身份校验
     * @param token
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("身份校验--执行了goGetAuthenticationInfo...");

        //token.getCredentials().toString();
        //credentials这个属性，在UsernamePasswordToken中其实是个Object，查看源代码，getCredentials()方法返回的就是password
        //若要正确得到UsernamePasswordToken的password，可以将credentials转为char[]再String.valof()方法获得String
        String username = (String) token.getPrincipal();

        User user = userService.findByName(username);

        if (user == null) {
            throw new UnknownAccountException(); //没有找到账号
        }

        if (Boolean.TRUE.equals(user.getLocked())) {
            throw new LockedAccountException(); //账号锁定
        }

        //交给AuthenticationRealm使用CredentialsMatcher进行密码匹配
        //SimpleAccount则可以直接获取明文
        //user实体的方法getCredentialsSalt获取的salt'=username+salt，而salt为一串随机序列
        //在PasswordHelper中，同样是用这种方式作为盐值传给SimpleHash来加密
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUsername(), //用户名
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                getName() //realm name
        );

        return authenticationInfo;
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }
}
