package com.cqie.serviceImpl;

import com.cqie.mapper.AdminMapper;
import com.cqie.pojo.Course;
import com.cqie.pojo.User;
import com.cqie.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServieImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public List<Course> getAllCourse() {
        return adminMapper.getAllCourse();
    }

    @Override
    public List<Course> getRecommend() {
        return adminMapper.getRecommend();
    }

    @Override
    public List<User> getAllUser() {
        return adminMapper.getAllUser();
    }

    @Override
    public List<User> getSomeUsers(String s) {
        return adminMapper.getSomeUsers(s);
    }

    @Override
    public int addUser(User user) {
        return adminMapper.addUser(user);
    }

    @Override
    public int updateUser(User user) {
        return adminMapper.updateUser(user);
    }

    @Override
    public int deleteUser(String code) {
        return adminMapper.deleteUser(code);
    }

    @Override
    public int updateRole(String code, String perms) {
        return adminMapper.updateRole(code,perms);
    }

    @Override
    public List<Course> allCourse() {
        return adminMapper.allCourse();
    }

    @Override
    public List<Course> getCourseName() {
        return adminMapper.getCourseName();
    }

    @Override
    public List<Course> getSomeCourse(String str) {
        return adminMapper.getSomeCourse(str);
    }

    @Override
    public int updateCouState(int state,int id) {
        return adminMapper.updateCouState(state,id);
    }

    @Override
    public int deleteCourse(int id) {
        return adminMapper.deleteCourse(id);
    }

    @Override
    public Course oneCourse(int id) {
        return adminMapper.oneCourse(id);
    }
}
