package com.cqie.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
//        设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
//        添加shiro内置过滤器
        /*
        anon:无需认证就可以访问
        authc:必须认证才能访问
        user:必须拥有  记住我 功能才能访问
        perms:拥有对某个资源的权限才能访问
        role:拥有某个角色权限才能访问
        */
//        拦截
        Map<String, String> filterMap = new LinkedHashMap<>();

        //授权，正常的情况下，没有授权会跳转到未授权页面
        filterMap.put("/static/**","anon");
        filterMap.put("/templates/**","anon");
        filterMap.put("/teacher/**","perms[teacher]");
        filterMap.put("/student/**","perms[student]");
        filterMap.put("/admin/**","perms[admin]");
//        filterMap.put("/user/*","authc");
        bean.setFilterChainDefinitionMap(filterMap);
//        设置登录的请求
        bean.setLoginUrl("/login");
//        未授权跳转的页面
        bean.setUnauthorizedUrl("/noauth");

        return bean;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        关联userRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    @Bean(name = "userRealm")
    public UserRealm userRealm(){
        return new UserRealm();
    }

//    ShiroDialect  用来整合shiro thymeleaf
    @Bean
    public ShiroDialect getsShiroDialect(){
        return new ShiroDialect();
    }
}
