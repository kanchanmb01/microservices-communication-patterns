package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Contacts;
import com.example.entities.User;
import com.example.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService 
{
	@Autowired
	UserRepository repo;
	
	@Autowired
	ContactsUser userContacts;

	@Override
	public User getUserById(int userId) {
		User user= repo.findById(userId).get();
		
		log.info("Request to Contacts Service for user {}",userId);
	
		List<Contacts> contacts = userContacts.getContacstByUserId(userId);
		
		user.setContacts(contacts);
		
		return user;
		
	}


	@Override
	public List<User> getAllUsers() {
		
		return repo.findAll();
	}

	
}
