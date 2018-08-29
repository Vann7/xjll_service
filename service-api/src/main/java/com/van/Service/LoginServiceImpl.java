package com.van.Service;

import com.van.thrift.user.LoginService;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService.Iface {
    @Override
    public boolean isLogin(String name) {
        System.out.println(name+" come to method:isLogin");
        return true;
    }

    @Override
    public void test1(){
        System.out.println("come to method:test1");
    }
}
