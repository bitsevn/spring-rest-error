package com.cubestacklabs.resterror;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestExceptionController {

	@RequestMapping(value="/")
	public String index() {
		return "Hey Buddy!";
	}
	
	@RequestMapping(value="/throw")
	public String ex() {
		throw new IllegalArgumentException("You screwed up!");
	}
}
