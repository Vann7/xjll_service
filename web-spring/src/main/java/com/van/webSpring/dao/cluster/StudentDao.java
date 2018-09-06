package com.van.webSpring.dao.cluster;

import com.van.webSpring.bean.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentDao {

    List<Student> findAll();

    Student searchByName(String name);

}
