package com.example.ShoppingCartDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shopping")
public class HomeController {
	@GetMapping("/init")
	public String homepage() {
		return "home";
	}

}
