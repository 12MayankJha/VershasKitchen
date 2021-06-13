package com.vershasKitchen.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@GetMapping("api/home")
	public String getHome() {
		return "Welcome to vershas Kitchen";
	}

}
