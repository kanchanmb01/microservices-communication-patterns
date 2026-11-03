package com.example.service;

import java.util.List;

import com.example.entities.Contacts;

public interface ContactsService 
{
	
	List<Contacts> getAllContacts();
	List<Contacts> getContactsByUserId(int userId);
	
}
