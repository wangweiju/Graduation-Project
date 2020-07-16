package com.wangweiju.config;

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

    //ShiroFillterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
         ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
         //设置安全管理器
         bean.setSecurityManager(defaultWebSecurityManager);
         //添加shiro的内置过滤器
        /*
            anon:无需认证就可以访问
            authc:必须认证才能访问
            user:必须拥有记住我功能才能用
            perms：拥有对某个资源的权限才能访问
            role:拥有某个角色权限才能访问
         */
        Map<String, String> filterMap = new LinkedHashMap<>();
        //拦截
        filterMap.put("/roles","perms[80]");
        filterMap.put("/user/*","authc");//目录为放置前端页面的目录
        //授权


        bean.setFilterChainDefinitionMap(filterMap);

        //设置登录请求
        bean.setLoginUrl("/user/login");

         return bean;
    }

    //DafaultwebSecurityManager
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //创建realm对象，需要自定义
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    //整合shiroDialect:用来整合shiro 和 thymeleaf
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
