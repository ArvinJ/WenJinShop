package com.yandz.steels.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yandz.steels.entity.Module;

@Controller
public class GetViewController {

	@RequestMapping("/login")
	public String login() {
		// 登录界面
		return "background/login";
	}

	@RequestMapping("/signin")
	public String signin(HttpServletRequest request, Model model) {
		// 登录界面
		int state=200;
		try {
			state = Integer.parseInt(request.getParameter("state"));
		} catch (NumberFormatException e) {
		}
		if (state == 501) {
			 model.addAttribute("state","用户名错误");
		} else if (state == 502) {
			 model.addAttribute("state","密码错误");
		} else if(state==500) {
			model.addAttribute("state","登录名或密码不正确");
		}else {
			model.addAttribute("state","请输入用户名和密码");
		}
		return "background/signin";
	}

	@RequestMapping("/")
	public String index() {
		// 首页
		return "background/index";
	}

	@RequestMapping("/signup")
	public String register() {
		// 注册
		return "background/signup";
	}

	@RequestMapping("/test")
	public String test() {
		// 注册
		return "background/test";
	}
}
