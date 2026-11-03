package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.User;
import com.example.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	UserService service;
	
	@GetMapping("/{userId}")
	public User getUserById(@PathVariable int userId)
	{
		return service.getUserById(userId);
	}
	
	@GetMapping("/all")
	public List<User> getAllUsers()
	{
		return service.getAllUsers();
	}

}
