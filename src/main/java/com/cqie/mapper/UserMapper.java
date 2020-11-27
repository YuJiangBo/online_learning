package com.cqie.mapper;

import com.cqie.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    User queryUserByCode(String code);
    User ValidationName(String code);
    int addUser(User user);
}
