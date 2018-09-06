package com.van.webSpring.service;

import com.van.webSpring.bean.Student;

import java.util.List;

public interface StudentService {

    List<Student> searchAll();


    Student searchOne(String name);

}
