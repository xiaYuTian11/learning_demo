package com.demo.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;

/**
 * @author TMW
 * @since 2020/2/12 12:17
 */
public class ShiroDemo {

    @Test
    public void test01(){
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro-auth.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123456");
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);

        System.out.println(subject.isAuthenticated());
        System.out.println(subject.getPrincipal());
        System.out.println(subject.getPreviousPrincipals());
        System.out.println(subject.getPrincipals());
        System.out.println(subject.getSession());
    }

}
