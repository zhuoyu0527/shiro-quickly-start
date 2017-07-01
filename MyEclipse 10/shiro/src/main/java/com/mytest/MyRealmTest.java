package com.mytest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import sun.org.mozilla.javascript.internal.Token;

public class MyRealmTest {
	@Test
	public void testLogin() throws Exception {
		
		//1.构建SecurityManager工厂
		IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
		//2.通过工厂创建SecurityManager
		SecurityManager manager = factory.getInstance();
		//3.将SecurityManager设置到运行环境中
		SecurityUtils.setSecurityManager(manager);
		//4.创建一个Subject主体实例
		Subject subject = SecurityUtils.getSubject();
		//5.创建令牌
		AuthenticationToken token = new UsernamePasswordToken("小蜜蜂1","游啊游");
		//6.主体登录
		try {
			subject.login(token);
		} catch (UnknownAccountException e) {
			System.out.println("用户名不存在");
			e.printStackTrace();
		}catch(IncorrectCredentialsException e){
			System.out.println("密码错误");
			e.printStackTrace();
		}
		System.out.println(subject.isRemembered());
		System.out.println(subject.isAuthenticated());
		subject.logout();
		System.out.println(subject.isAuthenticated());
		
	}
}
