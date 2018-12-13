package com.berzenin.university.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
	
	@RequestMapping(value = {"/", "/index" }, method = RequestMethod.GET)
	public String index(Model model) {
		String message = "Abandon hope all ye who enter here";
		model.addAttribute("message", message);
		return "index";
	}
}
