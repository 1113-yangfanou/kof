package com.yang.dao;

import com.yang.pojo.User;

import java.util.List;

public interface UserMapper {
    // 增加一个用户
    int addUser(User user);
    // 更新用户
    int update(User user);
    // 获得所有用户
    List<User> getUser();
    // 根据Name得到User
    User getUserByName(String Name);

}
