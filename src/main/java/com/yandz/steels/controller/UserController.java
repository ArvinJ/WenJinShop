package com.yandz.steels.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yandz.steels.entity.Result;
import com.yandz.steels.entity.User;
import com.yandz.steels.service.UserService;

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
	 * 以json对象字符串做上传参数提交 添加记录
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Result add(@RequestBody User user) {
		result.setData(user);
		// TODO 校验数据是否已经存在当前提交的对应关系
		user.setCreateDate(new Date());
		boolean temp = userService.addObject(user);
		logger.info("执行添加记录结果：" + temp);
		if (temp) {
			result.setRes("200");
		} else {
			result.setRes("500");
		}
		result.setMsg("执行添加记录结果：" + temp);
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
