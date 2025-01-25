package com.ApplicationStockMarket.Service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.ApplicationStockMarket.Entity.CompanyEntry;
import com.ApplicationStockMarket.Entity.StockUser;
import com.ApplicationStockMarket.Repository.StockUserRepository;


@Component
public class StockUserService {
	
	
	   @Autowired
	    private JWTService jwtService;

	    @Autowired
	    private AuthenticationManager authManager;
	
	@Autowired
	StockUserRepository stockUserRepository;
	@Autowired
	CompanyEntryService companyEntryService;
	
	/*public StockUser addUser(StockUser stockUser) {
		
		StockUser s=stockUserRepository.save(stockUser);
		return s;
	}*/

    //private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

   /* public StockUser register(StockUser user) {
        user.setPassword(encoder.encode(user.getPassword()));
        stockUserRepository.save(user);
        return user;
    }
    */
    
    
    private BCryptPasswordEncoder passwordEncoder  = new BCryptPasswordEncoder(12);

    public String  register(StockUser user) {
        user.setPassword(passwordEncoder .encode(user.getPassword()));
        stockUserRepository.save(user);
        return jwtService.generateToken(user.getUserName());
        //return user;
    }

    public String verify(StockUser user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUserName());
        } else {
            return "fail";
        }
    }
    //private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
   	public List<StockUser> getUserEntries() {
   		return stockUserRepository.findAll();
   	}
   	
  /*public boolean postUserEntry(StockUser user){
   		//user.setDate(LocalDateTime.now());
   		//journalEntry2.setId("1");
   		stockUserRepository.save(user);
   		return true;
   	}*/
   	public boolean postNewUserEntry(StockUser user){
   		//user.setDate(LocalDateTime.now());
   		//journalEntry2.setId("1");
   		 user.setPassword(passwordEncoder.encode(user.getPassword()));
   		stockUserRepository.save(user);
   		return true;
   	}
   	
   	public StockUser putUserEntry(StockUser user) {
   	   Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String name = authentication.getName();
   		StockUser oldUser=stockUserRepository.findByUserName(name);
   		oldUser.setUserName(user.getUserName());
   		oldUser.setPassword(passwordEncoder .encode(user.getPassword()));
   		stockUserRepository.save(oldUser);
   		return oldUser;
   		
   	}
  //forUser
   	public boolean deleteUserEntry() {
   	   Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String name = authentication.getName();
   		StockUser oldUser=stockUserRepository.findByUserName(name);
   		List<CompanyEntry> entries=oldUser.getCompanyStockPriceEntries();
   		for(int i=0;i<entries.size();i++) {
   			
   			
   			entries.remove(i);
   		}
   		
   		stockUserRepository.deleteByUserName(name);
   		
   		return true;
   	}
  //forADMIN
 	public boolean deleteUserEntry(String name) {
    	 //  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //String name = authentication.getName();
    		StockUser oldUser=stockUserRepository.findByUserName(name);
    		List<CompanyEntry> entries=oldUser.getCompanyStockPriceEntries();
    		for(int i=0;i<entries.size();i++) {
    			
    			
    			entries.remove(i);
    		}
    		
    		stockUserRepository.deleteByUserName(name);
    		
    		return true;
    	}
 	//forADMIN
   	public StockUser findByUserName(String userName) {
   	        return stockUserRepository.findByUserName(userName);
   	    }
   	//forUser
 	public StockUser findByUserName() {
 		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
 	       String name = authentication.getName();
	        return stockUserRepository.findByUserName(name);
	    }
 	//forUser
   	public List<CompanyEntry> getCompanyEntries(){ 
   	   Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String name = authentication.getName();
   		StockUser oldUser=stockUserRepository.findByUserName(name);
   		return oldUser.getCompanyStockPriceEntries();
   	}
   	//forADMIN
   	public List<CompanyEntry> getCompanyEntries(String name){ 
    	  // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //String name = authentication.getName();
    		StockUser oldUser=stockUserRepository.findByUserName(name);
    		return oldUser.getCompanyStockPriceEntries();
    	}
	public List<CompanyEntry> addCompanyEntries(CompanyEntry companyEntry ){ 
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	       String name = authentication.getName();
	   		StockUser oldUser=stockUserRepository.findByUserName(name);
	   		oldUser.getCompanyStockPriceEntries();
		CompanyEntry newCompanyEntry=companyEntryService.postCompanyEntry(companyEntry);
		oldUser.getCompanyStockPriceEntries().add(newCompanyEntry);
		stockUserRepository.save(oldUser);
  		return oldUser.getCompanyStockPriceEntries();
  	}
	public List<CompanyEntry> updateCompanyEntries(ObjectId id,CompanyEntry companyEntry ){ 
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	       String name = authentication.getName();
	   		StockUser oldUser=stockUserRepository.findByUserName(name);
	   		List<CompanyEntry> list=oldUser.getCompanyStockPriceEntries();
	   		/*for(int i=0;i<list.size();i++) {
	   			if(list.get(i).getId()==id) {
	   				companyEntryService.putCompanyEntry(id,companyEntry);
	   				break;
	   			}
	   		}*/
	   		companyEntryService.putCompanyEntry(id,companyEntry);
	   		stockUserRepository.save(oldUser);
		//CompanyEntry newCompanyEntry=companyEntryService.putCompanyEntry(id,companyEntry);
 		return stockUserRepository.findByUserName(name).getCompanyStockPriceEntries();
 	}
	public List<CompanyEntry> deleateCompanyEntries(ObjectId id,CompanyEntry companyEntry ){ 
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	       String name = authentication.getName();
	   		StockUser oldUser=stockUserRepository.findByUserName(name);
	   	/*	List<CompanyEntry> list=oldUser.getCompanyStockPriceEntries();
	   		for(int i=0;i<list.size();i++) {
	   			if(list.get(i).getId()==id) {
	   				companyEntryService.deleteCompanyEntry(id);
	   				break;
	   			}
	   		}*/
	   		companyEntryService.deleteCompanyEntry(id);
	   		stockUserRepository.save(oldUser);
		//CompanyEntry newCompanyEntry=companyEntryService.putCompanyEntry(id,companyEntry);
	   		return stockUserRepository.findByUserName(name).getCompanyStockPriceEntries();
 	}
  	
  	

}



