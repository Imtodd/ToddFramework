package com.todd.framework.contorller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.todd.framework.activemq.MessageSender.MessageSender;
import com.todd.framework.po.Role;
import com.todd.framework.po.Student;
import com.todd.framework.po.User;
import com.todd.framework.service.IRoleService;
import com.todd.framework.service.IStudentService;
import com.todd.framework.service.IUserService;
import com.todd.framework.tools.PasswordHelper;

@Controller
@RequestMapping(value = "user")
public class MainController {

	@Autowired
	private IUserService userservice;
	@Autowired
	private IRoleService roleservice;
	@Autowired
	private MessageSender messagesender;
	@Autowired
	private IStudentService studentsercice;

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
			List<Role> roles = new ArrayList<Role>();
			Role role = roleservice.getRoleWithName("guest");
			if (role != null) {
				roles.add(role);
				user.setRoles(roles);
			}
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

	@RequestMapping(value = "getRole", method = RequestMethod.GET)
	@ResponseBody
	public Set<String> getRoleList() {
		return userservice.findRoles("todd");
	}

	@RequestMapping(value = "getpermission", method = RequestMethod.GET)
	@ResponseBody
	public Set<String> getpermission() {
		return userservice.findPermissions("todd");
	}

	@RequestMapping(value = "Login", method = RequestMethod.POST)
	public String login(User user) {
		Subject subject = SecurityUtils.getSubject();// 获得shiro的subject对象
		User temp = userservice.getUserWithName(user.getUserName());// 根据用户名获取用户
		if (temp == null) {// 判断是否找到账户
			throw new UnknownAccountException();
		}
		PasswordHelper.DecryptPassword(temp.getSalt(), user);// 将盐信息添加到用户对象
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());// 获取token
		subject.login(token);// 登陆操作
		if (subject.isAuthenticated()) {
			System.out.println("用户登陆成功");
			User u = (User) subject.getPrincipal();
			messagesender.userLogin(u.getId(), user.getUserName());
		}
		return "redirect:/user/main";
	}

	@RequestMapping(value = "main", method = RequestMethod.GET)
	public String toMain() {
		return "Main";
	}

	@RequestMapping(method = RequestMethod.POST, value = "students")
	@ResponseBody
	public JSONObject getStudent(int rows, int page) {
		JSONObject json = new JSONObject();
		rows = rows == 0 ? 10 : rows;
		page = page == 0 ? 1 : page;
		json.put("rows", studentsercice.getStudentList(rows, page));
		json.put("total", studentsercice.totleStudent());
		return json;
	}

	@RequestMapping(value = "saveStu", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject saveStudent(Student stu) {
		JSONObject json = new JSONObject();
		try {
			studentsercice.saveStudent(stu);
			json.put("success", true);
			json.put("msg", "保存完成");
		} catch (Exception e) {
			json.put("success", false);
			json.put("msg", "保存失败");
			e.printStackTrace();
		}
		return json;
	}

	@RequestMapping(value = "updateStu", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updateStudent(Student stu) {
		JSONObject json = new JSONObject();
		try {
			studentsercice.updateStudent(stu);
			json.put("success", true);
			json.put("msg", "保存完成");
		} catch (Exception e) {
			json.put("success", false);
			json.put("msg", "保存失败");
			e.printStackTrace();
		}
		return json;
	}

	@RequestMapping(value = "deleteStu", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject deleteStudent(int id) {
		JSONObject json = new JSONObject();
		try {
			studentsercice.deleteStudent(id);
			json.put("success", true);
			json.put("msg", "删除完成");
		} catch (Exception e) {
			json.put("success", false);
			json.put("msg", "删除失败");
			e.printStackTrace();
		}
		return json;
	}
}
