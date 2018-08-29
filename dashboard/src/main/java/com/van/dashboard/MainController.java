package com.van.dashboard;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {



    @RequestMapping("/index")
    public String index() {
        System.out.println("isLogin");
        return "index";
    }

    @RequestMapping("/dash")
    public String dash() {

        System.out.println("in dashboard");
        return "line";
    }


    @RequestMapping("/chat")
    public String chat() {
        return "chat";
    }




}
