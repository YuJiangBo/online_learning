package com.cqie.controller;

import com.cqie.pojo.Course;
import com.cqie.pojo.Stu_Course;
import com.cqie.pojo.User;
import com.cqie.service.AdminService;
import com.cqie.service.StudentService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    AdminService adminService;
    @Autowired
    StudentService studentService;

    @RequestMapping("/index")
    public String getCourses(Model model){
        Subject currentSubject = SecurityUtils.getSubject();
        Session session = currentSubject.getSession();
        User user = (User) session.getAttribute("loginUser");
        List<Stu_Course> stu_Course = studentService.getMyCourseId(Integer.parseInt(user.getCode()));
        List<Course> myCourse = new ArrayList();
        for(int i = 0;i < stu_Course.size();i++){
            myCourse.add(i,studentService.getMyCourse(stu_Course.get(i).getCid()));
        }
        System.out.println(myCourse);
        model.addAttribute("myCourse",myCourse);
        List<Course> recommend = adminService.getRecommend();
        model.addAttribute("recommend",recommend);
        return "index";
    }
    @RequestMapping("/addCourse")
    public String addCourse(Model model){
        Subject currentSubject = SecurityUtils.getSubject();
        Session session = currentSubject.getSession();
        User user = (User) session.getAttribute("loginUser");
        List<Stu_Course> stu_Course = studentService.getMyCourseId(Integer.parseInt(user.getCode()));
        List<Course> myCourse = new ArrayList();
        for(int i = 0;i < stu_Course.size();i++){
            myCourse.add(i,studentService.getMyCourse(stu_Course.get(i).getCid()));
        }
        List<Course> courses = adminService.getAllCourse();
        for(int i = 0;i < courses.size();i++){
            if(myCourse.contains(courses.get(i))){
                courses.remove(i);
            }
        }
        List<Course> courseName = adminService.getCourseName();
        model.addAttribute("courseName",courseName);
        model.addAttribute("courses",courses);
        return "addCourse";
    }

    @RequestMapping("/doAdd")
    public String doAdd(HttpServletRequest request, Model model){
        Subject currentSubject = SecurityUtils.getSubject();
        Session session = currentSubject.getSession();
        User user = (User) session.getAttribute("loginUser");
        int cid = Integer.parseInt(request.getParameter("addid"));
        String cname = request.getParameter("addname");
        int sid = Integer.parseInt(user.getCode());
        Stu_Course stu_course = new Stu_Course();
        stu_course.setSid(sid);
        stu_course.setCid(cid);
        stu_course.setCname(cname);
        studentService.doAdd(stu_course);
        return "redirect:/index";
    }
}
