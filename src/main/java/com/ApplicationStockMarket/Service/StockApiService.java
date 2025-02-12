package com.ApplicationStockMarket.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.ApplicationStockMarket.Constants.Placeholders;
import com.ApplicationStockMarket.Entity.StockResponse;
@Component
public class StockApiService {
	@Autowired
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
	    private String STOCK_API;

	//@GetMapping("/stockApi/{company}")

    public StockResponse getStockDetails(String company) {
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
    }
}
