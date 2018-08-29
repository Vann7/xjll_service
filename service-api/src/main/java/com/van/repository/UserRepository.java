package com.van.repository;


import com.van.domain.UserDto;
import com.van.thrift.user.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author van
 */
@Mapper
public interface UserRepository {

    @Select("select * from people where id = #{id}")
    User getUserById(@Param("id") int id);

    @Select("select * from people where name = #{name}")
    User getUserByName(@Param("name") String name);


    @Select("select * from people where name = #{name}")
    UserDto getUserDtoByName(@Param("name") String name);

    @Select("select * from people")
    List<User> getList();

    @Select("select * from people")
    List<UserDto> getDtoList();

    @Insert("insert into people(name,age,home) values(#{name},#{age},#{home})")
    void addUser(User user);

    @Update("update people set  age = #{age}, home = #{home} where name = #{name} ")
    void updateUser(User user);

    @Delete("delete from people where name = #{name}")
    void delUser(String name);


}
