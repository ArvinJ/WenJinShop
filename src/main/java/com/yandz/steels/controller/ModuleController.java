package com.yandz.steels.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yandz.steels.entity.Module;
import com.yandz.steels.service.ModuleService;

import ch.qos.logback.classic.Logger;

/**
 * 
 * 
 * @Title: ModuleController.java
 * @Package com.yandz.steels.controller
 * @Description: 模块管理
 * @author: wenjin.zhu
 * @date: 2018年12月5日 下午9:29:41
 * @version V1.0
 */
@Controller
@RequestMapping("/module")
public class ModuleController {
	@Resource
	private ModuleService moduleService;
	public Logger logger = (Logger) LoggerFactory.getLogger(getClass());

	/**
	 * 根据id查询显示
	 */
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	@ResponseBody
	public Module showModule(HttpServletRequest request, Model model) {
		int moduleId = Integer.parseInt(request.getParameter("id"));
		Module module = moduleService.getModuleById(moduleId);
		return module;
	}

	/**
	 * 查询显示所有
	 */
	@RequestMapping(value = "/showList", method = RequestMethod.GET)
	@ResponseBody
	public List<Module> showModules() {
		List<Module> modules = moduleService.getModuleList();
		return modules;
	}

	/**
	 * 以json对象字符串做上传参数提交 添加记录
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public String addModule(@RequestBody Module module) {
		boolean temp = moduleService.addModule(module);
		logger.info("执行添加记录结果：" + temp);
		return "执行添加记录结果：" + temp;
	}

	/**
	 * 根据id逻辑删除
	 */
	@RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String delModule(@PathVariable Integer id) {
		boolean temp = moduleService.delModule(id);
		logger.info("执行逻辑删除结果：" + temp);
		return "执行逻辑删除结果：" + temp;
	}

	/**
	 * 根据提交上来的json对象进行修改非空项
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public String updateModule(@RequestBody Module module) {
		moduleService.updateModule(module);
		return "SUCCESS";
	}

}
