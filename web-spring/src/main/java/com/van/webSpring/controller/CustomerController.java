package com.van.webSpring.controller;


import com.google.gson.Gson;
import com.van.webSpring.bean.Customer;
import com.van.webSpring.repository.CustomerReposiitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 采用MongoDB 进行Customer 数据的增删改查
 */
@RequestMapping("/mongo/")
@RestController
public class CustomerController {

    @Autowired
    private CustomerReposiitory reposiitory;

    @Autowired
    private MongoTemplate template;

    private Gson gson = new Gson();

    @RequestMapping("create")
    public void create() {
        reposiitory.deleteAll();
        reposiitory.save(new Customer("Alice", "Smith"));
        reposiitory.save(new Customer("Bob", "Smith"));

        for (Customer customer : reposiitory.findAll()) {
            System.out.println(customer.toString());
        }


    }

    @RequestMapping("search_first/{name}")
    public String search(@PathVariable String name) {
        Customer customer = reposiitory.findByFirstName(name);
        System.out.println(customer);
        return gson.toJson(customer, Customer.class);
    }

    @RequestMapping("search_last/{name}")
    public String search_last(@PathVariable String name) {
        List<Customer> list  = reposiitory.findByLastName(name);
        return gson.toJson(list);
    }


    @RequestMapping("test")
    public void test() {
        Customer customer = new Customer("Tony", "Smith");
        template.insert(customer);
        List<Customer> list = template.findAll(Customer.class);
        System.out.println(gson.toJson(list));
    }


}
