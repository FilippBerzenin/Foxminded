package com.berzenin.university.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/index")
	@GetMapping
	public String hellow (Map<String, Object> model) {
		model.put("title", "Hello world");
		model.put("name", "Filipp");
		return "index";
	}

}
