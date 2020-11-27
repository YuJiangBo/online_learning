package com.cqie.service;

import com.cqie.pojo.Course;
import com.cqie.pojo.Stu_Course;

import java.util.List;

public interface TeacherService {

    //我教的课程

    List<Course> getMyCourse(int scode);
    List<Course> getMyCourseOn(int scode);
    List<Course> getMyCourseOff(int scode);

    int doAdd(Course course);

    List<Course> tGetSomeCourse(String str);
}
