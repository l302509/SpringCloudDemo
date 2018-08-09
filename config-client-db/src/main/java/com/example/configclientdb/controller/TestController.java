package com.example.configclientdb.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RefreshScope
@RestController
public class TestController {
	
	 @Value("${logging.path}")
	    private String message;

	    @GetMapping("/test")
	    public String test() {
	        return message;
	    }

}
