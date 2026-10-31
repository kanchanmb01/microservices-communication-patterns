package com.example.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Income {
	
	int incomeId;

	int categoryId;
	double amount;
	String notes;
	
	int userId;
}
