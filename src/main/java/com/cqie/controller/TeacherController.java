package com.cqie.controller;

import com.cqie.pojo.Course;
import com.cqie.pojo.Stu_Course;
import com.cqie.pojo.User;
import com.cqie.service.AdminService;
import com.cqie.service.StudentService;
import com.cqie.service.TeacherService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TeacherController {

    @Autowired
    AdminService adminService;
    @Autowired
    TeacherService teacherService;

    @RequestMapping("/teacher")
    public String getCourses(Model model){
        Subject currentSubject = SecurityUtils.getSubject();
        Session session = currentSubject.getSession();
        User user = (User) session.getAttribute("loginUser");
        List<Course> myCourseOn = teacherService.getMyCourseOn(Integer.parseInt(user.getCode()));
        List<Course> myCourseOff = teacherService.getMyCourseOff(Integer.parseInt(user.getCode()));
        List<Course> recommend = adminService.getRecommend();
        model.addAttribute("recommend",recommend);
        model.addAttribute("myCourseOn",myCourseOn);
        model.addAttribute("myCourseOff",myCourseOff);
        return "teacher/teacher";
    }
    @RequestMapping("/tCourseManage")
    public String addCourse(Model model){
        Subject currentSubject = SecurityUtils.getSubject();
        Session session = currentSubject.getSession();
        User user = (User) session.getAttribute("loginUser");
        List<Course> allCourse = teacherService.getMyCourse(Integer.parseInt(user.getCode()));
        model.addAttribute("allCourse",allCourse);
        return "teacher/tCourseManage";
    }

    @RequestMapping("/tAddCourse")
    public String tAddCourse(){
        return "teacher/tAddCourse";
    }

    /**pic上传地址*/
    @Value("${file.upload.path}")
    private String picfilePath;


    @RequestMapping("/upload")
    public String upload(@RequestParam("state") String state,@RequestParam("cname") String cname,@RequestParam("video") MultipartFile video, @RequestParam("file") MultipartFile file, Model model) throws IOException {
        if(video.isEmpty()){
            model.addAttribute("file",video.getOriginalFilename());
            return "redirect:/teacher";
        }
        String fileName = video.getOriginalFilename();
        String filePath = "E:\\intellij idea\\online_learning\\src\\main\\resources\\static\\img\\course\\";
        File dest = new File(filePath+fileName);
        video.transferTo(dest);
        String videoPath = "/img/course/"+fileName;
//        ------------图片上传-------------------------------------------
        String filename = file.getOriginalFilename();
        // 定义上传文件保存路径
        String path = picfilePath;
        // 新建文件
        File filepath = new File(path, filename);
        // 判断路径是否存在，如果不存在就创建一个
        if (!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }
        try {
            // 写入文件
            file.transferTo(new File(path + File.separator + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 将src路径发送至html页面
        String picPath = "/img/course/"+filename;

        //保存
        Subject currentSubject = SecurityUtils.getSubject();
        Session session = currentSubject.getSession();
        User user = (User) session.getAttribute("loginUser");
        Course course = new Course();
        course.setCname(cname);
        course.setState(Integer.parseInt(state));
        course.setTcode(user.getCode());
        course.setTname(user.getName());
        course.setImg(picPath);
        course.setVideo(videoPath);
        teacherService.doAdd(course);
        return "redirect:/teacher";
    }

    //修改课程状态
    @RequestMapping("/tUpdateCouState")
    public String updateCouState(HttpServletRequest request){
        int state = Integer.parseInt(request.getParameter("state"));
        int id = Integer.parseInt(request.getParameter("upId"));
        adminService.updateCouState(state,id);
        return "redirect:/tCourseManage";
    }

    //删除课程
    @RequestMapping("/tDeleteCourse")
    public String deleteCourse(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("delCode"));
        adminService.deleteCourse(id);
        return "redirect:/tCourseManage";
    }

    //搜索课程
    @RequestMapping("/tSearchCourse")
    public String searchCourse(HttpServletRequest request,Model model){
        String str = request.getParameter("str");
        List<Course> course = teacherService.tGetSomeCourse(str);
        model.addAttribute("searchCourse",course);
        return "teacher/tSearchCourse";
    }
}
