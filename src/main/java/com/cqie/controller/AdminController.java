package com.cqie.controller;

import com.cqie.pojo.Course;
import com.cqie.pojo.User;
import com.cqie.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class AdminController {

    @Autowired
    AdminService adminService;

    @RequestMapping("/admin")
    public String getCourses(Model model){
        List<Course> courses = adminService.getAllCourse();
        List<Course> recommend = adminService.getRecommend();
        model.addAttribute("recommend",recommend);
        model.addAttribute("courses",courses);
        return "admin/admin";
    }

    @RequestMapping("/video")
    public String toVideo(String cid,Model model){
        Course course = adminService.oneCourse(Integer.parseInt(cid));
        model.addAttribute("course",course);
        return "video";
    }
/*一、 用户管理*/
    @RequestMapping("/userManage")
    public String toUserManage(){
        return "redirect:/allUser";
    }

    //所有用户
    @RequestMapping("/allUser")
    public String getAllUser(Model model){
        List<User> allUser = adminService.getAllUser();
        model.addAttribute("allUser",allUser);
        return "admin/userManage";
    }
    //搜索用户
    @RequestMapping("/someUser")
    public String getSomeUsers(HttpServletRequest request,Model model){
        String str = request.getParameter("str");
        List<User> users = adminService.getSomeUsers(str);
        model.addAttribute("searchUsers",users);
        return "admin/searchUser";
    }
    //添加用户
    @RequestMapping("/adminAddUser")
    public String addUser(HttpServletRequest request){
        String code = request.getParameter("addCode");
        String name = request.getParameter("addName");
        String pass = request.getParameter("addPass");
        String perm = request.getParameter("addPerm");
        User user = new User();
        user.setCode(code);
        user.setName(name);
        user.setPassword(pass);
        user.setPerms(perm);
        adminService.addUser(user);
        return "redirect:/allUser";
    }
    //修改用户
    @RequestMapping("/updateUser")
    public String updateUser(HttpServletRequest request){
        String code = request.getParameter("upCode");
        String name = request.getParameter("upName");
        String pass = request.getParameter("upPass");
        User user = new User();
        user.setCode(code);
        user.setName(name);
        user.setPassword(pass);
        adminService.updateUser(user);
        return "redirect:/allUser";
    }
    //删除用户
    @RequestMapping("/delUser")
    public String deleteUser(HttpServletRequest request){
        String delCode = request.getParameter("delCode");
        adminService.deleteUser(delCode);
        return "redirect:/allUser";
    }
/*二、角色管理*/
    @RequestMapping("/roleManage")
    public String toRoleManage(){
        return "redirect:/allRole";
    }

    //所有角色用户
    @RequestMapping("/allRole")
    public String allRole(Model model){
        List<User> allUser = adminService.getAllUser();
        model.addAttribute("allRole",allUser);
        return "admin/roleManage";
    }
    //搜索用户
    @RequestMapping("/searchRole")
    public String searchRole(HttpServletRequest request,Model model){
        String str = request.getParameter("str");
        List<User> users = adminService.getSomeUsers(str);
        model.addAttribute("searchRole",users);
        return "admin/searchRole";
    }
    //修改角色
    @RequestMapping("/updateRole")
    public String updateRole(HttpServletRequest request){
        String code = request.getParameter("upCode");
        String perms = request.getParameter("upPerms");
        adminService.updateRole(code,perms);
        return "redirect:/allRole";
    }

/*三、课程管理*/
    @RequestMapping("/courseManage")
    public String toCourseManage(){
        return "redirect:/allCourse";
    }
    //所有课程
    @RequestMapping("/allCourse")
    public String getAllCourse(Model model){
        List<Course> allCourse = adminService.allCourse();
        List<Course> courseName = adminService.getCourseName();
        model.addAttribute("allCourse",allCourse);
        model.addAttribute("courseName",courseName);
        return "admin/courseManage";
    }

    //搜索课程
    @RequestMapping("/searchCourse")
    public String searchCourse(HttpServletRequest request,Model model){
        String str = request.getParameter("str");
        List<Course> course = adminService.getSomeCourse(str);
        List<Course> allCourseName = adminService.getCourseName();
        model.addAttribute("allCourseName",allCourseName);
        model.addAttribute("searchCourse",course);
        return "admin/searchCourse";
    }

    //修改课程状态
    @RequestMapping("/updateCouState")
    public String updateCouState(HttpServletRequest request){
        int state = Integer.parseInt(request.getParameter("state"));
        int id = Integer.parseInt(request.getParameter("upId"));
        adminService.updateCouState(state,id);
        return "redirect:/allCourse";
    }

    //删除课程
    @RequestMapping("/deleteCourse")
    public String deleteCourse(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("delCode"));
        adminService.deleteCourse(id);
        return "redirect:/allCourse";
    }
}
