package com.yandz.steels.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GetViewController {

	@RequestMapping("/login")
	public String login() {
		// 登录界面
		return "background/login";
	}
	@RequestMapping("/signin")
	public String signin() {
		// 登录界面
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

}
