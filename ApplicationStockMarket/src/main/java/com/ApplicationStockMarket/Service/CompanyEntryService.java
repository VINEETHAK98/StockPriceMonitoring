package com.ApplicationStockMarket.Service;




import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.ApplicationStockMarket.Entity.CompanyEntry;
import com.ApplicationStockMarket.Entity.StockUser;
import com.ApplicationStockMarket.Repository.CompanyEntryRepository;

@Component
public class CompanyEntryService {
	@Autowired
	private CompanyEntryRepository companyEntryRepository;
	
	@Autowired
	private StockUserService stockUserService;
	
	public List<CompanyEntry> getCompanyEntries() {
		  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        String userName = authentication.getName();
	        StockUser user = stockUserService.findByUserName(userName);
		return user.getCompanyStockPriceEntries();
		
	}
	
	
	
		@Transactional
	public StockUser postCompanyEntry(CompanyEntry companyEntry) {
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        String userName = authentication.getName();
	        try {
	        	 StockUser user = stockUserService.findByUserName(userName);
	        	  if (user == null) {
	                  throw new Exception("User not found with username: " + userName);
	              }
	        	 // CompanyEntry.setDate(LocalDateTime.now());
	        	  CompanyEntry saved = companyEntryRepository.save(companyEntry);
	             user.getCompanyStockPriceEntries().add(saved);
	             stockUserService.postNewUserEntry(user);
	             return user;
	        } catch (Exception e) {
	            throw new RuntimeException( e.getMessage());
	        }
	    

	}
	
	public CompanyEntry putCompanyEntry(ObjectId id,CompanyEntry companyEntry) {
		
		   Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        String userName = authentication.getName();
	        try {
	        	 StockUser user = stockUserService.findByUserName(userName);
	        	  if (user == null) {
	                  throw new Exception("User not found with username: " + userName);
	              }
	        	 // CompanyEntry.setDate(LocalDateTime.now());
	        	  CompanyEntry saved = companyEntryRepository.save(companyEntry);
	             user.getCompanyStockPriceEntries().add(saved);
	             stockUserService.postNewUserEntry(user);
	             //return saved;
	        } catch (Exception e) {
	            throw new RuntimeException( e.getMessage());
	        }
		companyEntryRepository.save(companyEntry);
		return companyEntry;
		
	}
	public boolean deleteCompanyEntry(ObjectId id) {
	    //  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	      //  String name = authentication.getName();
		List<CompanyEntry> entries=stockUserService.getCompanyEntries();
		CompanyEntry entry=companyEntryRepository.findById(id).orElse(null);
		entries.remove(entry);
		companyEntryRepository.deleteById(id);
		return true;
	}
	public CompanyEntry findCompanyEntry(ObjectId id) {
		return companyEntryRepository.findById(id).orElse(null);
		//return true;
	}
}
