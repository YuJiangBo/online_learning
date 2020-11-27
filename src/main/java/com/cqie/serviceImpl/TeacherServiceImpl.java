package com.cqie.serviceImpl;

import com.cqie.mapper.TeacherMapper;
import com.cqie.pojo.Course;
import com.cqie.pojo.Stu_Course;
import com.cqie.service.TeacherService;
import com.sun.org.apache.bcel.internal.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public List<Course> getMyCourse(int scode) {
        return teacherMapper.getMyCourse(scode);
    }

    @Override
    public List<Course> getMyCourseOn(int scode) {
        return teacherMapper.getMyCourseOn(scode);
    }

    @Override
    public List<Course> getMyCourseOff(int scode) {
        return teacherMapper.getMyCourseOff(scode);
    }

    @Override
    public int doAdd(Course course) {
        return teacherMapper.doAdd(course);
    }

    @Override
    public List<Course> tGetSomeCourse(String str) {
        return teacherMapper.tGetSomeCourse(str);
    }
}
