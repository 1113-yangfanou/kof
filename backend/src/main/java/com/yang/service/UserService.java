package com.yang.service;

import com.yang.pojo.User;

import java.util.List;

public interface UserService {

    int addUser(User user);

    int update(User user);

    List<User> getUser();

    User getUserByName(String Name);
}
