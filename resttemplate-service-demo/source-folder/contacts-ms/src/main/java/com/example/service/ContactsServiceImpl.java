package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Contacts;
import com.example.repository.ContactsRepository;

@Service
public class ContactsServiceImpl implements ContactsService {

	@Autowired
	ContactsRepository repo;
	
	@Override
	public List<Contacts> getContactsByUserId(int userId) 
	{
		return repo.getContactsByUserId(userId);
	}

	@Override
	public List<Contacts> getAllContacts() {
		return repo.findAll();
	}

}
