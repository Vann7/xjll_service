package com.van.webSpring.service;

import com.van.webSpring.bean.User;

import java.util.List;

public interface UserService {

    List<User> getList();

    User getUser(String name);

    boolean updateUser(User user);

}
