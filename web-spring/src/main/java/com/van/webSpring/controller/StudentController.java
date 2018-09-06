package com.van.webSpring.controller;

import com.google.gson.Gson;
import com.van.webSpring.bean.Student;
import com.van.webSpring.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService service;
    private Gson gson = new Gson();

    @RequestMapping("/getList")
    public String getList() {
        List<Student> list = service.searchAll();
        String json = gson.toJson(list);
        return json;

    }

    @RequestMapping("/search/{name}")
    public String search(@PathVariable String name) {
        Student student = service.searchOne(name);
        String json = gson.toJson(student);
        return json;
    }

}
