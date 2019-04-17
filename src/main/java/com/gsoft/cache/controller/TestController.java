package com.gsoft.cache.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gsoft.cache.service.serviceImpl.UserServiceImpl;

@Controller
public class TestController {
    
	@Autowired
	private UserServiceImpl userService;
	
	
	
	@RequestMapping(value="/getHello.json")
	@ResponseBody
	public String getRedisData() {
		String hello = userService.getHello("张123");
		
	//	String query = userService.query(2222333L);
		
		return hello;
		
	}
	
	@RequestMapping(value="/getQuery.json")
	@ResponseBody
	public String getQuery() {
		
		
		String query = userService.query(2222333L);
		
		return query;
		
	}
}
