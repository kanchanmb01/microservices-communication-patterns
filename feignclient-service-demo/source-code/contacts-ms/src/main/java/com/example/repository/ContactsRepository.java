package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Contacts;

public interface ContactsRepository extends JpaRepository<Contacts, Integer>
{
	List<Contacts> getContactsByUserId(int userId);

}
