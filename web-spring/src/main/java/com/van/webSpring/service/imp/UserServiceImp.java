package com.van.webSpring.service.imp;

import com.van.webSpring.bean.User;
import com.van.webSpring.dao.UserDao;
import com.van.webSpring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getList() {
        return userDao.findAllUser();
    }

    @Override
    public User getUser(String name) {
        return userDao.getUser(name);
    }

    @Override
    public boolean updateUser(User user) {
        return updateUser(user);
    }

}
