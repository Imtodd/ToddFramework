package com.todd.framework.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;


import com.todd.framework.po.User;
import com.todd.framework.service.IUserService;
import com.todd.framework.service.impl.UserServie;

public class ToddRealm extends AuthorizingRealm {

	@Autowired
	private IUserService userservice;

	/**
	 * 获取授权信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(userservice.findRoles(username));
		authorizationInfo.setStringPermissions(userservice.findPermissions(username));
		return authorizationInfo;
	}

	/**
	 * 验证身份信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		User user = userservice.getUserWithName(username);
		if (user == null) {
			throw new UnknownAccountException();// 没找到帐号
		}
		if (Boolean.TRUE.equals(user.isLocked())) {
			throw new LockedAccountException(); // 帐号锁定
		}
		// 交给 AuthenticatingRealm 使用 CredentialsMatcher 进行密码匹配，如果觉得人家
		// 的不好可以在此判断或自定义实现
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, // 用户名
				user.getPassword(), // 密码
				ByteSource.Util.bytes(user.getSalt()), // salt=username+salt
				getName() // realm name
		);
		return authenticationInfo;
	}

}
