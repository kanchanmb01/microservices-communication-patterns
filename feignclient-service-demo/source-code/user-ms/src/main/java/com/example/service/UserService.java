package com.example.service;

import java.util.List;

import com.example.entities.User;

public interface UserService {
	
	User getUserById(int userId);

	List<User> getAllUsers();
}
