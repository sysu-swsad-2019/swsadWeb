package com.swsadWeb.controller;

import com.swsadWeb.entity.*;
import com.swsadWeb.service.PermissionService;
import com.swsadWeb.service.RoleService;
import com.swsadWeb.service.UserInfoService;
import com.swsadWeb.service.UserService;
import com.sun.deploy.net.HttpResponse;
import net.sf.ehcache.Cache;
import org.apache.ibatis.jdbc.Null;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.HttpCookie;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther wangjing
 * @date 2019/6/13
 */
@Controller
public class LoginController {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 用户登录的入口
     *
     * @param username
     * @param password
     * @param model
     * @return
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public Msg login(
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "remember", required = false) String remember,
            Model model, HttpSession session) {


        System.out.println(session.getId());
        //一个抽象类且没有任何实现类，三个静态方法，一个静态属性securityManager
        //getSubject()是知识通过ThreadContext来获取当前subject，原理和spring的声明式事物或者是hibernate的open-session-in-view的实现是一个道理
        Subject t = SecurityUtils.getSubject();
        System.out.println(t.getSession().getId());



        System.out.println("登陆用户输入的用户名：" + username + "，密码：" + password);
        String error = null;

        // need .equals("")
        if (username != null && password != null && !username.equals("") && !password.equals("")) {
            //初始化
            Subject subject = SecurityUtils.getSubject();

            //UsernamePasswordToken 与对应Realm 中的 AuthenticationToken 有区别
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);

            if (remember != null){
                if (remember.equals("on")) {
                    //说明选择了记住我
                    token.setRememberMe(true);
                } else {
                    token.setRememberMe(false);
                }
            }else{
                token.setRememberMe(false);
            }

            try {
                //登录，即身份校验，由通过Spring注入的UserRealm会自动校验输入的用户名和密码在数据库中是否有对应的值
                subject.login(token);
                System.out.println("用户是否登录：" + subject.isAuthenticated());
                return Msg.success("登录成功");
//                Map<String, String> ppp = new HashMap<>();
//                ppp.put("sid", SecurityUtils.getSubject().getSession().getId().toString());
//                return ppp;
            } catch (UnknownAccountException e) {
                e.printStackTrace();
                error = "用户账户不存在，错误信息：" + e.getMessage();
            } catch (IncorrectCredentialsException e) {
                e.printStackTrace();
                error = "用户名或密码错误，错误信息：" + e.getMessage();
            } catch (LockedAccountException e) {
                e.printStackTrace();
                error = "该账号已锁定，错误信息：" + e.getMessage();
            } catch (DisabledAccountException e) {
                e.printStackTrace();
                error = "该账号已禁用，错误信息：" + e.getMessage();
            } catch (ExcessiveAttemptsException e) {
                e.printStackTrace();
                error = "该账号登录失败次数过多，错误信息：" + e.getMessage();
            } catch (Exception e){
                e.printStackTrace();
                error = "未知错误，错误信息：" + e.getMessage();
            }
        } else {
            error = "请登录";
        }
        //登录失败，跳转到login页面

        model.addAttribute("error", error);
//        Map<String, String> ttt = new HashMap<>();
//        ttt.put("sid", SecurityUtils.getSubject().getSession().getId().toString());
        //ttt.put("rememberMe", SecurityUtils.getSubject().getSession().getAttribute("rememberMe").toString());
        //return ttt;
        return Msg.error(error);

    }

//    /**
//     * 退出登录，我们不需要实现，Shiro的Filter过滤器会帮我们生成一个logout请求，
//     *    当浏览器访问`/logout`请求时，Shiro会自动清空缓存并重定向到配置好的loginUrl页面
//     *
//     * @return
//     */
//    @RequestMapping("/logout")
//    public String logout() {
//        Subject subject = SecurityUtils.getSubject();
//        subject.logout();
//        return "index";
//    }

    /**
     * 登录成功，跳转到首页
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {

        //String t = SecurityUtils.getSubject().getSession().getAttribute("rememberMe").toString();
        return "index";
    }

    @RequestMapping("/testsid")
    @ResponseBody
    public Map<String, String> testsid() {
        Subject t = SecurityUtils.getSubject();
        Map<String, String> temp = new HashMap<>();
        temp.put("sid", t.getSession().getId().toString());
        return temp;
    }

    @RequestMapping(value = "/initial", method = RequestMethod.POST)
    @ResponseBody
    public String initial() {
        Permission permission = new Permission("user:create","用户模块新增",Boolean.TRUE);
        Permission permission2 = new Permission("user:update","用户模块修改",Boolean.TRUE);
        permissionService.create(permission);
        permissionService.create(permission2);


        Role role = new Role("admin","管理员",Boolean.TRUE);
        Role role2 = new Role("user","用户管理员",Boolean.TRUE);
        roleService.create(role);
        roleService.create(role2);

        User user = new User("admin", "123");
        User user2 = new User("tycoding", "123");
        User user3 = new User("涂陌", "123");
        userService.create(user);
        userService.create(user2);
        userService.create(user3);



        return "yes";
    }

    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    @ResponseBody
    public Msg regist(@RequestParam(value = "username", required = false)String username,
                      @RequestParam(value = "password", required = false)String password) {

        User tuser = new User(username, password);
        if (userService.findByName(username)!=null){
            return Msg.error("用户名已存在");
        }else {
            userService.create(tuser);
            UserInfo userInfo = new UserInfo();
            userInfo.setUsername(tuser.getUsername());
            userInfo.setPassword(tuser.getPassword());
            userInfoService.insertUser(userInfo);

            System.out.println("new user in sys_user table id is: "+ tuser.getId());

            userService.correlationRoles(tuser.getId(), roleService.findByRole("user").getId());
        }

        return Msg.success("注册成功");
    }


//    @RequestMapping(value = "/logout", method = RequestMethod.POST)
//    @ResponseBody
//    public Msg logout(){
//        Subject subject = SecurityUtils.getSubject();
//
//        subject.logout();
//
//    }

}

