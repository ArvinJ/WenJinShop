package com.yandz.steels.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yandz.steels.entity.Result;
import com.yandz.steels.entity.User;
import com.yandz.steels.service.UserService;
import com.yandz.steels.utils.MD5;

import ch.qos.logback.classic.Logger;

/**
 * 
 * 
 * @Title: UserController.java
 * @Package com.yandz.steels.controller
 * @Description: 用户管理
 * @author: wenjin.zhu
 * @date: 2018年12月6日 下午3:30:37
 * @version V1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	private Result result = new Result();
	public Logger logger = (Logger) LoggerFactory.getLogger(getClass());

	/**
	 * 登录
	 */
	@RequestMapping(value = "/loginjson", method = RequestMethod.POST)
	@ResponseBody
	public Result loginJSON(@RequestBody User u) {

		try {
			u.setPass(MD5.md5(u.getName(), u.getPass()));
		} catch (Exception e1) {
			System.err.println("密码加密重组错误！");
			e1.printStackTrace();
		}
		// 添加用户认证信息
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(u.getName().toString(),
				u.getPass().toString());
		// 进行验证，这里可以捕获异常，然后返回对应信息
		try {
			subject.login(usernamePasswordToken);
			User user = userService.findByName(u.getName());
			result.setRes("200");
			result.setMsg("登录成功");
			result.setData(user);
		} catch (UnknownAccountException e) {
			result.setRes("501");
			result.setMsg("用户名错误");
		} catch (IncorrectCredentialsException e) {
			result.setRes("502");
			result.setMsg("密码错误");
		} catch (AuthenticationException e) {
			result.setRes("500");
			result.setMsg("登录失败");
		}

		return result;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request) {
		String name = request.getParameter("username");
	 	String pass = request.getParameter("password");
		User u = new User();
		u.setName(name);u.setPass(pass);
	 
		try {
			u.setPass(MD5.md5(u.getName(), u.getPass()));
		} catch (Exception e1) {
			System.err.println("密码加密重组错误！");
			e1.printStackTrace();
		}
		// 添加用户认证信息
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(u.getName().toString(),
				u.getPass().toString());
		// 进行验证，这里可以捕获异常，然后返回对应信息
		try {
			subject.login(usernamePasswordToken);
			User user = userService.findByName(u.getName());
			request.getSession().setAttribute("user",user);
			return "redirect:/";
		} catch (UnknownAccountException e) {
			return "redirect:/signin?state=501";
		} catch (IncorrectCredentialsException e) {
			return "redirect:/signin?state=502";
		} catch (AuthenticationException e) {
			return "redirect:/steels/signin?state=500";
		}

	}

	/**
	 * 根据id查询用户
	 */
	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
	@ResponseBody
	public User show(@PathVariable Integer id) {
		User user = null;
		try {
			user = userService.getObjectById(id);
			result.setData(user);
			result.setRes("200");
			result.setMsg("根据id查询显示记录返回成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.setRes("500");
			result.setMsg("根据id查询显示记录返回失败");
		}
		return user;
	}

	/**
	 * 查询显示所有用户
	 */
	@RequestMapping(value = "/showList", method = RequestMethod.GET)
	@ResponseBody
	public List<User> showList() {
		List<User> users = userService.getObjectList();
		return users;
	}

	/**
	 * 注册，md5加密，以json对象字符串做上传参数提交 添加记录
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Result add(@RequestBody User user) {
		user.setStatus(1);
		User u = userService.findByName(user.getName());
		if (u != null) {
			// 已经存在，不允许再次创建
			result.setRes("503");
			result.setMsg("用户已经存在，不允许再次创建");
		} else {
			try {
				user.setPass(MD5.md5(user.getName(), user.getPass()));
				result.setData(user);
				user.setCreateDate(new Date());
				boolean temp = userService.addObject(user);
				logger.info("执行添加记录结果：" + temp);
				if (temp) {
					result.setRes("200");
				} else {
					result.setRes("500");
				}
				result.setMsg("添加操作执行状态：" + temp);

			} catch (Exception e) {
				logger.error("密码加密有误！");
				result.setRes("504");
				result.setMsg("用户密码加密有误");
			}

		}

		return result;
	}

	/**
	 * 根据id逻辑删除用户
	 */
	@RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Result del(@PathVariable Integer id) {
		result.setData(null);
		boolean temp = userService.delObject(id);
		logger.info("执行逻辑删除结果：" + temp);
		if (temp) {
			result.setRes("200");
			result.setMsg("del success.");
		} else {
			result.setRes("500");
			result.setMsg("del failure.");
		}
		return result;
	}

	/**
	 * 根据提交上来的json对象进行修改非空项
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Result update(@RequestBody User user) {
		result.setData(user);
		if (user.getId() == null) {
			result.setRes("501");
			result.setMsg("update object id is null.");
		} else {
			boolean flag = userService.updateObject(user);
			if (flag) {
				result.setRes("200");
				result.setMsg("update success.");
			} else {
				result.setRes("500");
				result.setMsg("update failure.");
			}
		}

		return result;
	}

	/**
	 * 为用户添加角色
	 */
	@RequestMapping(value = "/updateByUserAddRole")
	@ResponseBody
	public Result userAddRole(@RequestBody User user) {
		result.setData(user);
		if (user.getId() == null) {
			result.setRes("501");
			result.setMsg("update object id is null.");
		} else {
			boolean flag = userService.updateObject(user);
			if (flag) {
				result.setRes("200");
				result.setMsg("update success.");
			} else {
				result.setRes("500");
				result.setMsg("update failure.");
			}
		}

		return result;
	}

}
