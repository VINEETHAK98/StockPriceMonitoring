package com.ApplicationStockMarket.Controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ApplicationStockMarket.Entity.CompanyEntry;
import com.ApplicationStockMarket.Entity.StockUser;
import com.ApplicationStockMarket.Service.CompanyEntryService;
import com.ApplicationStockMarket.Service.StockUserService;

@RestController
@RequestMapping("/user")
public class StockUserController {
	@Autowired
	StockUserService stockUserService;
	@Autowired
	CompanyEntryService companyEntryService;
	@GetMapping("/getUserDetails")
    public ResponseEntity<?> getUserDetails() {
		return new ResponseEntity(stockUserService.findByUserName(),HttpStatus.FOUND);
	}
	@GetMapping("/getCompanyEntryDetails")
    public ResponseEntity<?> getCompanyEntryDetails() {
		return new ResponseEntity(stockUserService.getCompanyEntries(),HttpStatus.FOUND);
	}
	@PostMapping("/addCompanyEntry")
    public ResponseEntity<?> addnewCompanyEntry(@RequestBody CompanyEntry companyEntry) {

        return new ResponseEntity(stockUserService.addCompanyEntries(companyEntry),HttpStatus.FOUND);
    }
	@PutMapping("/updateCompanyEntry/{entryid}")
    public ResponseEntity<?> changeCompanyEntry(@PathVariable ObjectId entryid,@RequestBody CompanyEntry companyEntry) {

        return new ResponseEntity(stockUserService.updateCompanyEntries(entryid,companyEntry),HttpStatus.FOUND);
	}
	@PutMapping("/changeUserDetails")
    public ResponseEntity<?> changeUserDetails(@RequestBody StockUser user) {
        return new ResponseEntity(stockUserService.putUserEntry(user),HttpStatus.OK);
    }
}
