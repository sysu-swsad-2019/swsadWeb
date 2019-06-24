package com.swsadWeb.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.swsadWeb.service.UserInfoService;

@Controller
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/getuser")
    public String getuser(){
        //ServletContext sc = new ServletContext();
        //ObjectMapper om = new ObjectMapper();
        //return "redirect:/user/regist.jsp";
        return "last/login";
    }
}
