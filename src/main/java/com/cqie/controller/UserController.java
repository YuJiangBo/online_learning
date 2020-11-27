package com.cqie.controller;

import com.cqie.pojo.User;
import com.cqie.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping({"/"})
    public String toIndex(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(String username, String password, Model model){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try {
            subject.login(token);
            Subject currentSubject = SecurityUtils.getSubject();
            Session session = currentSubject.getSession();
            User user = (User) session.getAttribute("loginUser");
            if(user.getPerms().equals("admin")){
                return "redirect:/admin";
            }else if (user.getPerms().equals("teacher")){
                return "redirect:/teacher";
            }else {
                return "redirect:/index";
            }
        }catch (UnknownAccountException e){
            model.addAttribute("msg","该用户不存在");
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "login";
        }
    }

    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }

    @RequestMapping("/signUp")
    public String tosignUp(){
        return "signUp";
    }

//    @RequestMapping("/signUp/validation")
//    public String toValidation(){
//
////        if(userService.ValidationName(username) == null){
////            model.addAttribute("msg","用户名可用");
////            System.out.println("用户名可用");
////        }else {
////            model.addAttribute("msg","用户名不可用");
////            System.out.println("用户名不可用");
////        }
//
//        return "signUp";
//    }

    @RequestMapping("/addUser")
    public String addUser(String code,String username,String password,String role,Model model){
        User user = new User();
        user.setCode(code);
        user.setName(username);
        user.setPassword(password);
        user.setPerms(role);
        userService.addUser(user);
        model.addAttribute("signUpMsg","ok");
        return "redirect:/";
    }

    @RequestMapping("/noauth")
    @ResponseBody
    public String noauth(){
        return "未授权!";
    }
}
