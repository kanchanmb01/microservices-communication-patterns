package com.example.service;

import java.util.List;

import com.example.entities.Income;

public interface IncomeService {
	List<Income> getAllIncomeRecords();
	List<Income> getIncomeByUserId(int userId);

}
