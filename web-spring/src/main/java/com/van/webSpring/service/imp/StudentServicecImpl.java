package com.van.webSpring.service.imp;

import com.van.webSpring.bean.Student;
import com.van.webSpring.dao.cluster.StudentDao;
import com.van.webSpring.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServicecImpl implements StudentService {
    @Resource
    private StudentDao studentDao;

    @Override
    public List<Student> searchAll() {
        return studentDao.findAll();
    }

    @Override
    public Student searchOne(String name) {
        return studentDao.searchByName(name);
    }
}
