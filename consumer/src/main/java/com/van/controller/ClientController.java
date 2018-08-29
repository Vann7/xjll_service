package com.van.controller;


import com.google.gson.Gson;
import com.van.thrift.ServiceProvider;
import com.van.thrift.ThriftClient;
import com.van.thrift.user.User;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {


    @Autowired
    private ThriftClient client;
    @Autowired
    private ServiceProvider serviceProvider;

    //@Autowired
    //private Thrift_AsynClient client_asyn;

    @RequestMapping("/index")
    @ResponseBody
    public String index() {
        return "this is consumer";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public String getList() {
        //ThriftClient client = new ThriftClient();
        //client.open();
        List<User> list = null;
        try {
            client.open();
            //list = serviceProvider.getUserService().getList();
            list = client.getUserService().getList();
            client.close();
        } catch (TException e) {
            e.printStackTrace();
        }
        //client.close();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    @ResponseBody
    public String getUser(@RequestParam("name")String name) {
        //ThriftClient client = new ThriftClient();


        client.open();
        User user = null;
        try {
           user = client.getUserService().getUserByName(name);

           System.out.println("client received ");
        } catch (TException e) {
            e.printStackTrace();
        }finally {
            client.close();
        }

        if(user == null) {
            return "null";
        }
        return user.toString();
    }


    @ResponseBody
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public String getJson() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List> responseEntity = restTemplate.getForEntity("http://localhost:8080/list",List.class);
        HttpHeaders headers = responseEntity.getHeaders();
        HttpStatus status = responseEntity.getStatusCode();
        System.out.println(status.value());
        List<User> list = responseEntity.getBody();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        System.out.println(list.toString());
        //String response = restTemplate.getForObject("http://localhost:8080/list",String.class);
        //System.out.println(response);
        return json;
    }


    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public void updateUser() {
        User user = new User();
        user.setName("test0");
        user.setAge(999);
        user.setHome("BJ");
        client.open();
        try {
            client.getUserService().updateUser(user);
        } catch (TException e) {
            e.printStackTrace();
        }
        client.close();
    }


    @RequestMapping(value = "/del",method = RequestMethod.POST)
    @ResponseBody
    public void deleteUser() {
        client.open();
        try {
            client.getUserService().delUser("test1");
        } catch (TException e) {
            e.printStackTrace();
        }
        client.close();
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public void insert() {
        User user = new User();
        user.setName("test000");
        user.setAge(999);
        user.setHome("HRB");
        client.open();
        try {
            client.getUserService().addUser(user);
        } catch (TException e) {
            e.printStackTrace();
        }
        client.close();
    }


    @RequestMapping(value = "/login2")
    @ResponseBody
    public void login() {

        //client.open();

        try {
            //client.getLoginService().isLogin("test");
            client.getLoginService().test1();
        } catch (TException e) {
            e.printStackTrace();
        }
        System.out.println("login in ");
        //client.close();
        //try {
        //    //client_asyn.open();
        //    //for(int i=1; i<4; i++) {
        //    //    CountDownLatch latch = new CountDownLatch(10);
        //    //    client_asyn.getUserService_async().getUserById(33, new AsynCallback(latch));
        //        //client_asyn.getUserService_async().getUserByName("test1" + i, null);
        //    //}
        //    System.out.println("login in");
        //    //return client_asyn.getLoginService().isLogin("test");
        //    // client_asyn.getLoginService_async().isLogin("test",null);
        //} catch (TException e) {
        //    e.printStackTrace();
        //
        //}finally {
        //    //client_asyn.close();
        //}
    }



}
