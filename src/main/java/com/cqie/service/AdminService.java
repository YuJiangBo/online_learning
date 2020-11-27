package com.cqie.service;

import com.cqie.pojo.Course;
import com.cqie.pojo.User;

import java.util.List;

public interface AdminService {

    //所有课程
    List<Course> getAllCourse();
    //最新推荐
    List<Course> getRecommend();

    //所有用户
    List<User> getAllUser();
    //搜索用户
    List<User> getSomeUsers(String s);
    //添加用户
    int addUser(User user);
    //修改用户
    int updateUser(User user);
    //删除用户
    int deleteUser(String code);
    //修改角色
    int updateRole(String code, String perms);
    //查询所有课程
    List<Course> allCourse();
    //查询所有课程（名称不重复）
    List<Course> getCourseName();
    //条件查询课程
    List<Course> getSomeCourse(String str);
    //修改课程状态
    int updateCouState(int state,int id);
    //删除课程
    int deleteCourse(int id);
    //根据课程id查询课程
    Course oneCourse(int id);
}
