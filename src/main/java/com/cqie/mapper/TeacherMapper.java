package com.cqie.mapper;

import com.cqie.pojo.Course;
import com.cqie.pojo.Stu_Course;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TeacherMapper {

    //我教的课程
    List<Course> getMyCourse(int scode);
    List<Course> getMyCourseOn(int scode);
    List<Course> getMyCourseOff(int scode);

    int doAdd(Course course);

    List<Course> tGetSomeCourse(String str);
}
