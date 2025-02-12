package com.ApplicationStockMarket.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ApplicationStockMarket.Constants.Placeholders;
import com.ApplicationStockMarket.Entity.StockResponse;
import com.ApplicationStockMarket.Entity.StockUser;
import com.ApplicationStockMarket.Repository.StockUserRepositoryImpl;
import com.ApplicationStockMarket.Service.EmailService;
import com.ApplicationStockMarket.Service.RedisService;
import com.ApplicationStockMarket.Service.SchedulerService;
import com.ApplicationStockMarket.Service.StockUserService;




@RestController
@RequestMapping("/public")
public class PublicController {
	@Autowired
	StockUserService stockUserService;
	@Autowired
	StockUserRepositoryImpl stockUserRepositoryImpl;
	@Autowired
	SchedulerService schedulerService;
	/*@Autowired
    private RestTemplate restTemplate;
	 @Autowired
	    private RedisService redisService;
	 @Autowired
	    private RedisTemplate redisTemplate;
	 @Autowired
	    private EmailService emailservice;
	    @Value("${stock.api.key}")
	    private String apiKey;
	    @Value("${STOCK_API}")
	    private String STOCK_API;*/
	@GetMapping("/test")
	public ResponseEntity<?>test(){
		//emailservice.sendEmail("vineethakomati@gmail.com", "Sentiment for previous week", "test");
		/*List<StockUser> users = stockUserRepositoryImpl.getUserForSA();
		return new ResponseEntity(users,HttpStatus.OK);*/
		
		
		schedulerService.fetchUsersAndSendSaMail();
		// emailservice.sendEmail("vineethakomati@gmail.com", "Sentiment for previous week", "test");
		return new ResponseEntity("working",HttpStatus.OK);
		
		
		
	}
	
	/*@PutMapping("/add")
	public ResponseEntity<?> addUser(@RequestBody StockUser stockUser){
		//StockUser s=stockUserService.addUser(stockUser);
		StockUser s=stockUserService.register(stockUser);
		return new ResponseEntity(s,HttpStatus.OK);
	}*/
	  @PostMapping("/register")
	    public ResponseEntity<?> register(@RequestBody StockUser user) {
	        return new ResponseEntity(stockUserService.register(user),HttpStatus.CREATED);
	    }

	    @PostMapping("/login")
	    public ResponseEntity<?> login(@RequestBody StockUser user) {

	        return new ResponseEntity(stockUserService.verify(user),HttpStatus.FOUND);
	    }

	/*	@GetMapping("/stockApi/{company}")

	    public StockResponse getStockDetails(@PathVariable String company) {
			 StockResponse weatherResponse = redisService.get("Stock_price_of_AAPL", StockResponse.class);
			   if (weatherResponse != null) {
				   emailservice.sendEmail("vineethakomati@gmail.com", "Sentiment for previous week", "test");
		            return weatherResponse;
		        } 
		
		        else {
		        	String finalAPI = STOCK_API.replace(Placeholders.COMPANY, company).replace(Placeholders.API_KEY, apiKey);
	           ResponseEntity<StockResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, StockResponse.class);
	 
	            StockResponse body = response.getBody();
		        	
	            if (body != null) {
	                System.out.println("inside");
	            }
	           redisService.set("Stock_price_of_AAPL", body,1);
	            //StockResponse weatherResponse1 = redisService.get("Stock_price_of_AAPL", StockResponse.class);
	           emailservice.sendEmail("vineethakomati@gmail.com", "Sentiment for previous week", "test");
	           return body;
			    
	           
	        
		}
			  

	
	

		}*/
}
