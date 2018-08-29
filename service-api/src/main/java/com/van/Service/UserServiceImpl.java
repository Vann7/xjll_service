package com.van.Service;


import com.van.Utils.SwitchUtils;
import com.van.domain.UserDto;
import com.van.redis.RedisClient;
import com.van.repository.UserRepository;
import com.van.thrift.user.User;
import com.van.thrift.user.UserService;
import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService.Iface {

    @Resource
    private UserRepository userRepository;
    @Resource
    private RedisClient redisClient;

    private  List<User> userList;
    private  List<UserDto> list;


    @Override
    public User getUserById(int id) {
        User u = userRepository.getUserById(id);
        if (u != null) {
            System.out.println("get User: " + u.toString());
        }else {
            System.out.println("get null");
            u = new User();
        }

        return u;
    }

    @Override
    public User getUserByName(String name)  {
        System.out.println("search name: " + name);
        User user = null;
        UserDto userDto = null;

        userDto = redisClient.get(name);
        if (userDto != null){
            user = SwitchUtils.toBean(userDto,User.class);
            return user;
        }else {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //user = userRepository.getUserByName(name);
            userDto = userRepository.getUserDtoByName(name);

            if (userDto != null) {
                redisClient.set(name,userDto, 3600);
            }
           user = SwitchUtils.toBean(userDto,User.class);
            return user;
        }

    }

    @Override
    public List<User> getList() throws TException {

        list = redisClient.get("userlist");

        if (list == null) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            userList = userRepository.getList();
            list = SwitchUtils.toList(userList,UserDto.class);
            redisClient.set("userlist",list);
        }else {
            userList = SwitchUtils.toList(list, User.class);
        }
        return userList;
    }

    @Override
    public void addUser(User user){
        userRepository.addUser(user);
    }

    @Override
    public void updateUser(User user){
        userRepository.updateUser(user);
    }

    @Override
    public void delUser(String name) {
        userRepository.delUser(name);
    }


}


