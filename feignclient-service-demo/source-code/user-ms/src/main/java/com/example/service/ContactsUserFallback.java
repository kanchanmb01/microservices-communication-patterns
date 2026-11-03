package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.entities.Contacts;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ContactsUserFallback implements ContactsUser {

	@Override
	public List<Contacts> getContacstByUserId(int userId) {
		
		log.error("Contacts Service unavailable. Executing fallback.Please try again later...");
		
		return new ArrayList<Contacts>();

	}
}
