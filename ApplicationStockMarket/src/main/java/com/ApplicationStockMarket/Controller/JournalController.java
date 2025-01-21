package com.ApplicationStockMarket.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/journal")
public class JournalController {
	@GetMapping("/test")
	public ResponseEntity<?> test(){
		return new ResponseEntity("Working",HttpStatus.OK);
	}
	
	

}
