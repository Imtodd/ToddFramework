package com.todd.framework.contorller;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.todd.framework.po.User;
import com.todd.framework.service.IUserService;
import com.todd.framework.tools.PasswordHelper;

@Controller
@RequestMapping(value = "user")
public class MainController {

	@Autowired
	private IUserService userservice;

	@RequestMapping(value = "toLogin", method = RequestMethod.GET)
	public String tologin(Map<String, Object> map) {
		map.put("userModel", new User());
		return "login";
	}

	@RequestMapping(value = "toRegist", method = RequestMethod.GET)
	public String toregist(Map<String, Object> map) {
		map.put("userModel", new User());
		return "regist";
	}

	@RequestMapping(value = "regist", method = RequestMethod.POST)
	public String regist(Map<String, Object> map, User user) {
		User temp_user = userservice.getUserWithName(user.getUserName());
		if (temp_user == null) {
			userservice.addUser(user);
			map.put("code", 101);
			map.put("msg", "注册成功");
			map.put("userModel", new User());
			return "login";
		} else {
			map.put("code", 102);
			map.put("msg", "用户名已经存在，请重新注册");
			map.put("userModel", new User());
			return "regist";
		}
	}

	@RequestMapping(value="getRole",method=RequestMethod.GET)
	@ResponseBody
	public Set<String> getRoleList(){
		return userservice.findRoles("todd");
	}
	
	@RequestMapping(value="getpermission",method=RequestMethod.GET)
	@ResponseBody
	public Set<String> getpermission(){
		return userservice.findPermissions("todd");
	}
	
	@RequestMapping(value="Login",method=RequestMethod.POST)
	public String login(User user) {
		Subject subject = SecurityUtils.getSubject();
		PasswordHelper.DecryptPassword("65f588c9d74a590b89976a425baf0f75", user);
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
		subject.login(token);
		if(subject.isAuthenticated()){
			System.out.println("用户登陆成功");
		}
		return "main";
	}
}
