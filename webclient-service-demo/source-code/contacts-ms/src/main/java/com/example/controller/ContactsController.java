package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Contacts;
import com.example.service.ContactsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/contacts")
public class ContactsController {
	
	
   @Autowired
   ContactsService service;
   
   @Value("${server.port}")
   private String port;
   
   @GetMapping("{userId}")
   public List<Contacts> getContacstByUserId(@PathVariable int userId)
   {
	   log.info("Request served by Contacts Service on Port {}", port);

	   return service.getContactsByUserId(userId);
   }
   
   @GetMapping("/all")
   public List<Contacts> getAllContacts()
   {
	   return service.getAllContacts();
   }
}
