package com.cqie.serviceImpl;

import com.cqie.mapper.UserMapper;
import com.cqie.pojo.User;
import com.cqie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserByCode(String code) {
        return userMapper.queryUserByCode(code);
    }

    @Override
    public User ValidationName(String code) {
        return userMapper.ValidationName(code);
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }
}
