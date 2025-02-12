package com.ApplicationStockMarket.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ApplicationStockMarket.Service.StockUserService;
@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	StockUserService stockUserService;

	@GetMapping("/getUserDetails/{name}")

    public ResponseEntity<?> getUserDetails(@PathVariable String name) {
		return new ResponseEntity(stockUserService.findByUserName(name),HttpStatus.FOUND);
	}
	@GetMapping("/getCompanyDetails/{name}")

    public ResponseEntity<?> getCompanyDetails(@PathVariable String name) {
		return new ResponseEntity(stockUserService.getCompanyEntries(name),HttpStatus.FOUND);
	}
	
}
