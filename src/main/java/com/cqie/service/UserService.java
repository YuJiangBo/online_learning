package com.cqie.service;


import com.cqie.pojo.User;

public interface UserService {

    User getUserByCode(String code);
    User ValidationName(String code);
    int addUser(User user);
}