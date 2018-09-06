package com.van.webSpring.controller;


import com.google.gson.Gson;
import com.van.webSpring.bean.User;
import com.van.webSpring.service.UserService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Scope("prototype")
@RequestMapping("/user")
public class UserController {
    private  Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;
    private Gson gson = new Gson();

    @RequestMapping("/getList")
    public String getUserList() {
        logger.info("getList");
        List<User> list = userService.getList();
        if (list == null){
            logger.error("find no one.");
            return null;
        }
        String json = gson.toJson(list);
        return json;
    }

    @RequestMapping("/search/{name}")
    public String searchUser(@PathVariable String name) {
        logger.info("search : " + name);
        User u = userService.getUser(name);
        if (u == null) {
            logger.error("find no one.");
            return null;
        }
        String str = gson.toJson(u, User.class);
        logger.info("find: " + str);
        return str;
    }

}
