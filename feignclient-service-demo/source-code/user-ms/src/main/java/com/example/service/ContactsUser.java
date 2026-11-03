package com.example.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.entities.Contacts;

@FeignClient(name="contacts-ms", fallback=ContactsUserFallback.class)
public interface ContactsUser {

	@GetMapping("/api/contacts/{userId}")
	List<Contacts> getContacstByUserId(@PathVariable int userId);

}
