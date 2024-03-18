package com.linyuwei.mapper;

import com.linyuwei.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select * from tb_user where password=#{password} and username=#{username}")
    User select(@Param("username") String username, @Param("password") String password);
    @Select("select * from tb_user where username=#{username} ;")
    User selectByUsername(String username);
    @Insert("insert into tb_user  values (null,#{username},#{password});")
    void add(User user);
}
