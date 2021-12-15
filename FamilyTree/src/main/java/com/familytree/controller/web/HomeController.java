package com.familytree.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/", name ="Home")
public class HomeController {
	@RequestMapping(value = {"","/home","index"}, name="home")
	public String home() {
		return "Home";
	}
}
