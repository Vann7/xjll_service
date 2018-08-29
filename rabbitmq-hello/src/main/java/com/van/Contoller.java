package com.van;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Contoller {

    @Autowired
    Sender sender;

    @RequestMapping(value = "/hello")
    @ResponseBody
    public String hello() {
        sender.send();
        return "hello";
    }


}
