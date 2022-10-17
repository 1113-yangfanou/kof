package com.yang.service.Impl;

import com.yang.dao.UserMapper;
import com.yang.pojo.User;
import com.yang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public List<User> getUser() {
        return userMapper.getUser();
    }

    @Override
    public User getUserByName(String Name) {
        return userMapper.getUserByName(Name);
    }
}
