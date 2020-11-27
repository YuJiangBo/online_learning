package com.cqie.serviceImpl;

import com.cqie.mapper.StudentMapper;
import com.cqie.pojo.Course;
import com.cqie.pojo.Stu_Course;
import com.cqie.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;
    @Override
    public List<Stu_Course> getMyCourseId(int sid) {
        return studentMapper.getMyCourseId(sid);
    }

    @Override
    public Course getMyCourse(int cid) {
        return studentMapper.getMyCourse(cid);
    }

    @Override
    public int doAdd(Stu_Course stu_course) {
        return studentMapper.doAdd(stu_course);
    }
}
