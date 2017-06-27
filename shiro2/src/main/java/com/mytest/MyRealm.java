package com.mytest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyRealm extends AuthorizingRealm {

	/*
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		//模拟获取传入的用户名
		String username = (String) token.getPrincipal();
		//模拟Service查询结果进行校验
		String name = "小蜜蜂1";
		String password = "游啊游";
		//验证失败
		if(!name.equals(username)){
			return null;
		}
		//验证成功,交由认证器管理 
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username,password,this.getName());
		return info;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

}
