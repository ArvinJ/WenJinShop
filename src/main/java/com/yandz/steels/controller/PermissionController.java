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

import com.yandz.steels.entity.Permission;
import com.yandz.steels.entity.Result;
import com.yandz.steels.service.PermissionService;

import ch.qos.logback.classic.Logger;

@Controller
@RequestMapping(value = "/permission")
public class PermissionController {
	@Autowired
	PermissionService permissionService;
	private Result result = new Result();
	public Logger logger = (Logger) LoggerFactory.getLogger(getClass());

	/**
	 * 根据roleId查询显示所有的权限
	 */
	@RequestMapping(value = "/show/{roleId}", method = RequestMethod.GET)
	@ResponseBody
	public List<Permission> show(@PathVariable Integer roleId) {
		List<Permission> permissions = permissionService.getPermissionByRoleId(roleId);

		return permissions;
	}

	/**
	 * 以json对象字符串做上传参数提交 添加记录
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Result add(@RequestBody Permission permission) {
		// TODO 校验数据是否已经存在当前提交的对应关系
		permission.setCreateDate(new Date());
		boolean temp = permissionService.addObject(permission);
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
	 * 根据roleId逻辑删除
	 */
	@RequestMapping(value = "/del/{roleId}", method = RequestMethod.GET)
	@ResponseBody
	public String del(@PathVariable Integer roleId) {
		boolean temp = permissionService.delObject(roleId);
		logger.info("执行逻辑删除结果：" + temp);
		return "执行逻辑删除结果：" + temp;
	}

	/**
	 * 根据提交上来的json对象进行修改非空项
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Result update(@RequestBody Permission permission) {
		// 根据模块id和角色id  控制 crud操作权限
		result.setData(permission);
		if (permission.getRoleId() == null) {
			result.setRes("501");
			result.setMsg("update object roleId is null.");
		}else if(permission.getModuleId() ==null) {
			result.setRes("502");
			result.setMsg("update object moduleId is null.");
		}else {
			boolean flag = permissionService.updateObject(permission);
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
