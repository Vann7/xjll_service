package com.van;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    HelloService helloService;

    @RequestMapping("/index")
    public String index() {
        return helloService.hello();
    }
}
