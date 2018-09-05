package com.van.webSpring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {


    @RequestMapping("/index")
    public String index(HttpServletRequest request, @RequestParam(value = "name", required = false, defaultValue = "springboot-thymeleaf")String name) {
        request.setAttribute("name", name);
        return "index";
    }
}
