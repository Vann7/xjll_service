package com.van.webSpring.service.imp;

import com.van.webSpring.bean.User;
import com.van.webSpring.dao.master.UserDao;
import com.van.webSpring.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class UserServiceImp implements UserService {
    @Resource
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
