package com.linyuwei.service;

import com.linyuwei.pojo.User;

public interface UserService {
    User login(String username,String password);
    boolean register(User user);
}
