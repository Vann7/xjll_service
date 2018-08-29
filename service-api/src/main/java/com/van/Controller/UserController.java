package com.van.Controller;


import com.google.gson.Gson;
import com.van.domain.People;
import com.van.domain.UserDto;
import com.van.redis.RedisClient;
import com.van.repository.UserRepository;
import com.van.thrift.user.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/server")
public class UserController {

    @Resource
    UserRepository userRepository;

    @Resource
    RedisClient redisClient;


    @RequestMapping(value = "/list")
    @ResponseBody
    public String getList() {

        List<User> list = userRepository.getList();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    @ResponseBody
    public String getUser(@RequestParam("name")String name) {

        User user = userRepository.getUserByName(name);
        System.out.println(user.name);
        redisClient.set(user.name,user);
        UserDto userDto = new UserDto();
        People people = new People();
        BeanUtils.copyProperties(user, people);
        System.out.println(people);
        return user.toString();
    }


    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public void updateUser() {
        User user = new User();
        user.setName("test0");
        user.setAge(999);
        user.setHome("BJ");
        userRepository.updateUser(user);
    }


    @RequestMapping(value = "/del",method = RequestMethod.POST)
    @ResponseBody
    public void deleteUser() {
        userRepository.delUser("test1");
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public void insert() {
        User user = null;
        for(int i=0; i<100; i++) {
            user = new User();
            user.setName("test"+ i);
            user.setAge(i);
            user.setHome("HRB"+ i);
            userRepository.addUser(user);
        }
    }

    @RequestMapping(value = "/index")
    @ResponseBody
    public String index() {
        return "echo echo";
    }




}
