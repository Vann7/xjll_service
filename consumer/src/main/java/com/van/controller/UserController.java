package com.van.controller;

import com.google.gson.Gson;
import com.van.thrift.ServiceProvider;
import com.van.thrift.user.User;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ServiceProvider serviceProvider;


    @RequestMapping(value = "login")
    @ResponseBody
    public String login() {
        User user = new User();
        try {
           boolean flag =  serviceProvider.getLoginService().isLogin("test");
            user  = serviceProvider.getUserService().getUserByName("test88");

        } catch (TException e) {
            e.printStackTrace();

        }
        if (user != null) {
            String json = user.toString();

            Gson gson = new Gson();
            String str = gson.toJson(user);
            return str;
        }else {
            return "User not found!";
        }
    }

}
