package com.cqie.mapper;

import com.cqie.pojo.Course;
import com.cqie.pojo.Stu_Course;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper {

    //我的课程
    List<Stu_Course> getMyCourseId(int sid);
    Course getMyCourse(int cid);

    int doAdd(Stu_Course stu_course);
}
