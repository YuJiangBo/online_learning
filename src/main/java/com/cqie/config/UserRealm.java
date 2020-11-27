package com.cqie.config;

import com.cqie.pojo.Menu;
import com.cqie.pojo.User;
import com.cqie.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    public Integer perms;

//    授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//拿到当前登录对象
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
//        设置当前用户的权限   currentUser.getPerms()数据库中的授权字段
        info.addStringPermission(currentUser.getPerms());

        return info;
    }

//    认证   这里登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("认证");
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        User user = userService.getUserByCode(userToken.getUsername());
        if (user == null) {
            return  null;  //抛出用户名不存在异常
        }

        if(user.getPerms().equals("student")){
            perms = 0;
        }else if(user.getPerms().equals("teacher")){
            perms = 1;
        }else if(user.getPerms().equals("admin")){
            perms = 2;
        }
//        List<Menu> menu = menuService.getMenuByPerms(perms);

//      登录成功后放进session
        Subject currentSubject = SecurityUtils.getSubject();
        Session session = currentSubject.getSession();
        session.setAttribute("loginUser",user);
//        session.setAttribute("menus",menu);

//        密码认证  自动加密了   user.getPassword()为数据库中的密码
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
