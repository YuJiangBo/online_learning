package com.cqie.service;

import com.cqie.pojo.Course;
import com.cqie.pojo.Stu_Course;

import java.util.List;

public interface StudentService {

    //我的课程
    List<Stu_Course> getMyCourseId(int sid);
    Course getMyCourse(int cid);

    int doAdd(Stu_Course stu_course);
}
