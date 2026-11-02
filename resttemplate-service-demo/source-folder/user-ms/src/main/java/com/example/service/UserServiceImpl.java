package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.entities.Contacts;
import com.example.entities.User;
import com.example.repository.UserRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService 
{
	@Autowired
	UserRepository repo;

	@Autowired
	RestTemplate restTemplate;
	
	@Override
	@CircuitBreaker(name="userService" , fallbackMethod="getUserByIdFallback")
	public User  getUserById(int userId)
	{
		
		User user=	repo.findById(userId).get();	
		log.info("Request to Contacts Service");

		 List<Contacts> contacts=  restTemplate.getForObject("http://contacts-ms/api/contacts/"+userId,List.class);
		 user.setContacts(contacts);
		 return user;
	}
	
	public User getUserByIdFallback(int userId , Throwable t)
	{
		 log.error("Contacts Service unavailable. Executing fallback. Please try again later...");
		
		 User user = new User();
		 user.setUserId(userId);
		 user.setUserName("Fallback User");
		
		 return user;
	}


	@Override
	public List<User> getAllUsers() {
		
		return repo.findAll();
	}

	
}
