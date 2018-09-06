package com.van.webSpring.dao.master;

import com.van.webSpring.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {

    void createUser(User user);

    List<User> findAllUser();

    User getUser(@Param("name") String name);

    boolean updateUser(User user);
}
