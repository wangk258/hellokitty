package com.anan.plate.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

	@RequestMapping("admin")
	public ModelAndView view(){
		return new ModelAndView("admin");
	}
}
