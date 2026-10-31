package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Income;

public interface IncomeRepository extends JpaRepository<Income, Integer> {

	List<Income> getIncomeByUserId(int userId);
}
