package com.todd.framework.contorller;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

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

@Controller
@RequestMapping(value = "user")
public class MainController {

	@Autowired
	private IUserService userservice;

//	@ResponseBody
//	@RequestMapping(value = "getAll", method = RequestMethod.GET)
//	public Collection<User> getAllUser() {
//		return userservice.getAll();
//	}

	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public String uploadPIC(@RequestParam(name = "file", required = false) MultipartFile file, Map<String, Object> map,
			HttpServletRequest request) {
		String realPath = request.getSession().getServletContext().getRealPath("upload");
		String contentType = file.getContentType();
		int startnum = contentType.lastIndexOf("/");
		int endnum = contentType.length();
		String name = UUID.randomUUID() + contentType.substring(startnum, endnum).replace("/", ".");
		File f = new File(realPath, name);
		if (!f.exists()) {
			f.mkdirs();
		}
		try {
			file.transferTo(f);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		map.put("filepath", request.getContextPath() + "/upload/" + name);
		return "showpic";
	}

	@ResponseBody
	@RequestMapping(value = "getuser", method = RequestMethod.GET)
	public User getUser() {
		User user = userservice.getUser();
		return user;
	}
}
