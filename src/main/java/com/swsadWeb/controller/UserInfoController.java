package com.swsadWeb.controller;


import javax.servlet.http.HttpSession;

import com.swsadWeb.entity.Msg;
import com.swsadWeb.entity.UserInfo;
import com.swsadWeb.util.FileUploadUtil;
import com.swsadWeb.util.MD5Util;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;

import javax.servlet.http.HttpServletRequest;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.swsadWeb.service.UserInfoService;

import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/userinfo")
@CrossOrigin
//这里用了@SessionAttributes，可以直接把model中的user(也就key)放入其中
//这样保证了session中存在user这个对象
@SessionAttributes("user")
public class UserInfoController {

    private static final Log logger = LogFactory.getLog(UserInfoController.class);

    @Autowired
    private UserInfoService userInfoService;

    //正常访问login页面
//    @ResponseBody
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String login(@RequestParam(required = false) String username, @RequestParam(required = false) String password){
//        return "login";
//    }

    @RequestMapping(value = "/login")
    public String login(){
        //ServletContext sc = new ServletContext();
        //ObjectMapper om = new ObjectMapper();
        //return "redirect:/userInfo/regist.jsp";
        return "last/login";
    }

    /**
     * ResponseBody 注解，可以将如下类型的数据做成json：
     * 1）基本数据类型，如 boolean , String , int 等
     * 2) Map 类型数据
     * 3）集合或数组
     * 4）实体对象
     * 5）实体对象集合
     *
     */
    @ResponseBody
    @RequestMapping(value = "/trypost", method = RequestMethod.POST)
    public List<String> trypost(@RequestParam(value = "username") String username){
        List<String> list = new ArrayList<String>();

        System.out.println("login: "+username);
        String pas = userInfoService.findUser(username);
        list.add(pas);
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "/trypost2", method = RequestMethod.POST)
    public List<UserInfo> trypost2(@RequestBody UserInfo userInfo){
        System.out.println(userInfo.getUsername()+"  "+ userInfo.getPassword());
        List<UserInfo> list = new ArrayList<UserInfo>();
        UserInfo userInfo1 = new UserInfo();
        userInfo1.setUsername("00001111");
        userInfo1.setPassword("tttttttt");

        UserInfo userInfo2 = new UserInfo();
        userInfo2.setUsername("22221111");
        userInfo2.setPassword("pppppppp");

        list.add(userInfo1);
        list.add(userInfo2);

        userInfoService.Regist(userInfo);

        return list;

    }

    // @ModelAttribute注解的实体类接收前端发来的数据格式需要为"x-www-form-urlencoded"
    // @RequestBody注解的实体类接收前端的数据格式为JSON(application/json)格式。
    //（若是使用@ModelAttribute接收application/json格式，虽然不会报错，但是值并不会自动填入）
    //表单提交过来的路径
    @RequestMapping("/checkLogin")
    public String checkLogin(UserInfo userInfo, Model model){
        //调用service方法
        userInfo = userInfoService.checkLogin(userInfo.getUsername(), userInfo.getPassword());
        //若有user则添加到model里并且跳转到成功页面
        if(userInfo != null){
            //model中加入user,并且外面有@SessionAttribute,能使得将'userInfo'同时拷贝到session作用域里
            model.addAttribute("user", userInfo);
            return "last/success";
        }
        return "last/fail";
    }

    //test1
    @RequestMapping("/test1")
    public void test1(String str, HttpServletRequest rq){
        HandlerMethodArgumentResolver tt1;
        HandlerMethodReturnValueHandler tt2;
        //rq.getSession().setAttribute("password", "123123");
        ModelAndView mv = new ModelAndView();

        //FileInputStream fs = new FileInputStream();


    }

    //测试超链接跳转到另一个页面是否可以取到session值
    @RequestMapping("/anotherpage")
    public String hrefpage(){
        //ModelAndView view = new ModelAndView("test");
        //ExtendedModelMap extendedModelMap = new ExtendedModelMap();
        return "last/anotherpage";
    }

    //注销方法
    @RequestMapping("/outLogin")
    public String outLogin(HttpSession session){
        //通过session.invalidata()方法来注销当前的session
        session.invalidate();
        return "last/login";
    }

    @RequestMapping("/regist")
    public String regist(HttpServletRequest request){
        // Output : Users/wangjing/Desktop/swsadWeb/target/swsadWeb/
        System.out.println(request.getServletContext().getRealPath("/"));

        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        String remoteAddress = request.getRemoteAddr();
        String servletPath = request.getServletPath();
        String realPath = request.getServletContext().getRealPath("");
        String remoteUser = request.getRemoteUser();
        String requestURI = request.getRequestURI();

        System.out.println(path);
        System.out.println(basePath);
        System.out.println(remoteAddress);
        System.out.println(servletPath);
        System.out.println(realPath);
        System.out.println(remoteUser);
        System.out.println(requestURI);

        String strPath = ",webapps,files";

        // 解析出文件存放路径位置
        String filepath = System.getProperty("catalina.base") + strPath.replace(',', File.separatorChar);
        System.out.println(filepath);

        File file = new File("/Users/wangjing/Desktop/swsadPicture/test2.txt");

        try {
            file.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
        }

        try {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("xxxaffdf");
            bw.flush();
            bw.close();
            fw.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        System.out.println(File.separatorChar);

        //JavaScriptSerializer javaScriptSerializer = new JavaScriptSerializer();



        return "last/regist";
    }

    @RequestMapping("/doRegist")
    public String doRegist(UserInfo userInfo, Model model){
        System.out.println(userInfo.getUsername());
        userInfoService.Regist(userInfo);
        return "last/success";
    }


    /*
     * app登录验证
     */
//    @ResponseBody
//    @RequestMapping(value = "/api/get", method = RequestMethod.GET)
//    public Object get(HttpServletRequest request,@RequestParam("sessionId")String sessionId) {
//        //判断用户的session是否正常
//        //获取session
//        if (sessionId == null || sessionId.equals("")) {
//            //Resoponse 是一个工具类
//            return new Response(Status.ERROR, "sessionId不能为空");
//        }
//    }
    @RequestMapping(value="/index3",method=RequestMethod.GET)
    public Map<String, String> index3(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "222");
        //map.put相当于request.setAttribute方法
        return map;
    }

    @RequestMapping(value = "/registIcon", method=RequestMethod.POST)
    @ResponseBody
    public String registIcon(){
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        //MultipartHttpServletRequest multipartHttpServletRequest
        //CommonsMultipartFile commonsMultipartFile = new CommonsMultipartFile();
        //MultipartFile multipartFile
        //MultipartResolver multipartResolver

        return "ok";
    }


    /**
     * 单个、批量文件上传
     *
     * @param request
     * @param session
     * 获取传入的模块名称
     * @return
     */
    @RequiresRoles(value={"admin","user"}, logical = Logical.OR)
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Msg uploadFile(Model model,HttpServletRequest request, HttpSession session) {

        // 获取session中保存的用户信息
        //UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");

        Subject subject = SecurityUtils.getSubject();
        String userUUID = userInfoService.findByUsername(subject.getPrincipal().toString()).getUuid();

        // 创建list集合用于获取文件上传返回路径名
        List<String> list = new ArrayList<String>();

        try {

            // 获取上传完文件返回的路径,判断module模块名称是否为空，如果为空则给default作为文件夹名
            list = FileUploadUtil.uploadFile(request, userUUID);
            // model属性也行
            model.addAttribute("fileUrlList", list);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            logger.error("上传文件发生错误=》》" + e.getMessage());

        }

        // 转发到uploadTest.jsp页面
        // 返回存有路径的List
        Msg msg = Msg.success("上传成功");
        Map<String, Object> map = new HashMap<>();
        map.put("pathURL", list);
        msg.setData(map);
        return msg;
    }


    @RequiresRoles(value={"admin","user"}, logical = Logical.OR)
    @RequestMapping(value = "/setUserIcon", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Msg setUserIcon(Model model,HttpServletRequest request, HttpSession session) {

        // 获取session中保存的用户信息
        //UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");

        Subject subject = SecurityUtils.getSubject();
        String userUUID = userInfoService.findByUsername(subject.getPrincipal().toString()).getUuid();

        // 创建list集合用于获取文件上传返回路径名

        String iconPath = "";
        try {

            // 获取上传完文件返回的路径,判断module模块名称是否为空，如果为空则给default作为文件夹名
            iconPath = FileUploadUtil.uploadIcon(request, userUUID);
            // model属性也行
            model.addAttribute("iconURL", iconPath);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            logger.error("上传文件发生错误=》》" + e.getMessage());

        }

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(subject.getPrincipal().toString());
        userInfo.setIconpath(iconPath);

        userInfoService.updateIconPathByUsername(userInfo);

        // 转发到uploadTest.jsp页面
        // 返回存有路径的List
        Msg msg = Msg.success("上传成功");
        Map<String, Object> map = new HashMap<>();
        map.put("iconURL", iconPath);
        msg.setData(map);
        return msg;
    }

    /**
     * 跳转至文件上传页面
     *
     * @return
     */
    @RequestMapping(value = "/common/upload-page", method = RequestMethod.GET)
    public String uploadTestPage() {

        return "last/uploadTest";
    }

    @RequestMapping(value = "/insert1", method = RequestMethod.POST)
    @ResponseBody
    public String insertTest1() {
        userInfoService.insertTest1();
        return "yes";
    }

    @RequestMapping(value = "/insert2", method = RequestMethod.POST)
    @ResponseBody
    public String insertTest2() {
        userInfoService.insertTest2();
        return "yes";
    }

    @RequestMapping(value = "/insertUser", method = RequestMethod.POST)
    @ResponseBody
    public String insertUser(@RequestBody UserInfo userInfo) {

        //System.out.println(userInfoService.insertUser(userInfo));

        userInfoService.insertUser(userInfo);

        System.out.println(userInfo.toString());
        return userInfo.toString();
    }



    @RequestMapping(value = "/testmd5", method = RequestMethod.POST)
    @ResponseBody
    public String testmd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        //System.out.println(userInfoService.insertUser(userInfo));
        System.out.println(str);

        return MD5Util.EncoderByMd5(str);

    }

    @RequiresRoles(value={"admin","user"}, logical = Logical.OR)
    @RequestMapping(value = "setUserNickname", method = RequestMethod.POST)
    @ResponseBody
    public Msg setUserNickName(@RequestParam(value = "nickname") String nickname){

        //System.out.println(userInfoService.insertUser(userInfo));
        System.out.println(nickname);

        Subject subject = SecurityUtils.getSubject();
        String username = subject.getPrincipal().toString();
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setNickname(nickname);
        userInfoService.updateNicknameByUsername(userInfo);


        return Msg.success("设置成功");

    }

    @RequiresRoles(value={"admin","user"}, logical = Logical.OR)
    @RequestMapping(value = "getUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> getUserInfo(){


        Subject subject = SecurityUtils.getSubject();
        String username = subject.getPrincipal().toString();

        UserInfo userInfo = userInfoService.findByUsername(username);

        Map<String, String> map = new HashMap<>();

        map.put("uuid", userInfo.getUuid());
        map.put("username", userInfo.getUsername());
        map.put("nickname", userInfo.getNickname());
        map.put("sex", userInfo.getSex().toString());
        map.put("phone", userInfo.getPhone());
        map.put("email", userInfo.getEmail());
        map.put("iconpath", userInfo.getIconpath());
        map.put("university", userInfo.getUniversity());
        map.put("academy", userInfo.getAcademy());
        map.put("grade", userInfo.getGrade()==null ? null : userInfo.getGrade().toString());
        map.put("money", userInfo.getMoney()==null ? null : userInfo.getMoney().toString());
        map.put("credit", userInfo.getCredit()==null ? null : userInfo.getCredit().toString());


        return map;

    }

    @RequestMapping(value = "/setUserInfo")
    @ResponseBody
    public Msg setUserInfo(@RequestBody UserInfo userInfo){
        Subject subject = SecurityUtils.getSubject();
        UserInfo user = userInfoService.findByUsername(subject.getPrincipal().toString());
        userInfo.setId(user.getId());

        userInfoService.updateUserInfo(userInfo);

        return Msg.success("修改成功");
    }

    @RequestMapping(value = "/getUserInfoById")
    @ResponseBody
    public Msg getUserInfoById(@RequestParam(value = "id") Long id){
        UserInfo userInfo = userInfoService.getUserInfoById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("userinfo", userInfo);
        Msg msg = Msg.success("查找成功");
        msg.setData(map);

        return msg;
    }









}

