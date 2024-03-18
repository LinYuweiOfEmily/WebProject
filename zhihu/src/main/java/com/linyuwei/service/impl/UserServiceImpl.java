package com.linyuwei.service.impl;

import com.linyuwei.mapper.UserMapper;
import com.linyuwei.pojo.User;
import com.linyuwei.service.UserService;
import com.linyuwei.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserServiceImpl implements UserService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public User login(String username, String password){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.select(username, password);
        sqlSession.close();
        return user;
    }

    @Override
    public boolean register(User user){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user1 = mapper.selectByUsername(user.getUsername());
        if(user1==null){
            mapper.add(user);
            sqlSession.commit();
        }
        sqlSession.close();
        return user1==null;

    }
}