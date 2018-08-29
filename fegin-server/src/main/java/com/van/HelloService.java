package com.van;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient("hello-service")
public interface HelloService {

    @RequestMapping("/server/index")
    String hello();
}
