package com.van.webSpring.controller;


import com.google.gson.Gson;
import com.van.webSpring.bean.User;
import com.van.webSpring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Scope("prototype")
public class UserController {

    @Autowired
    private UserService userService;
    private Gson gson = new Gson();

    @RequestMapping("/getList")
    public String getUserList() {
        List<User> list = userService.getList();
        if (list == null){
            return null;
        }
        String json = gson.toJson(list);
        return json;
    }

    @RequestMapping("/search/{name}")
    public String searchUser(@PathVariable String name) {
        User u = userService.getUser(name);
        if (u == null) return null;

        return gson.toJson(u, User.class);
    }

}
