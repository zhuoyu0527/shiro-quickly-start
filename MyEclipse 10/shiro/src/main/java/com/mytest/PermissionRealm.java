package com.mytest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class PermissionRealm extends AuthorizingRealm {

	/*
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		//模拟获取传入的用户名
		String username = (String) token.getPrincipal();
		//模拟Service查询结果进行校验
		String name = "小蜜蜂";
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
		//获取登录用户对象
		Object principal = principals.getPrimaryPrincipal();
		System.out.println(principal);
		Set<String> roles = new HashSet<>();
		roles.add("role1");
		roles.add("role2");
		List<String> permission = new ArrayList<>();
		permission.add("user:create");
		permission.add("user:view");
		//将角色和权限关联,返回AuthorizationInfo对象
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
		info.addStringPermissions(permission);
		return info;
	}

}
