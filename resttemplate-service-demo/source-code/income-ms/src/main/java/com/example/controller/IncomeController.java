package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Income;
import com.example.service.IncomeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/income")
public class IncomeController {
	
	@Value("${server.port}")
	private String port;
	
	@Autowired
	IncomeService service;
	
	
	 @GetMapping("{userId}")
	 public List<Income> getIncomeByUserId(@PathVariable int userId){
		 
		 log.info("Request served by Income Service on Port {}", port);
		   return service.getIncomeByUserId(userId);
	   }
	   
	  @GetMapping("/all")
	  public List<Income> getAllIncome()
	   {
		   return service.getAllIncomeRecords();
	   }
	
}
