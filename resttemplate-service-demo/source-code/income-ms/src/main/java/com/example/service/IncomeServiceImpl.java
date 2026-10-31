package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Income;
import com.example.repository.IncomeRepository;

@Service
public class IncomeServiceImpl implements IncomeService {
	
	@Autowired
	IncomeRepository repo;

	@Override
	public List<Income> getAllIncomeRecords() {
		return repo.findAll();
	}

	@Override
	public List<Income> getIncomeByUserId(int userId) {
		List<Income> income = repo.getIncomeByUserId(userId);
		return income;
		
	}
	
}
