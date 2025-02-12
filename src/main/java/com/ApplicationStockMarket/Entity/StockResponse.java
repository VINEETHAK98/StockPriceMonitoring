package com.ApplicationStockMarket.Entity;




import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockResponse {
	@JsonProperty("c")
    private Double currentPrice;
	
	@JsonProperty("h")
    private Double highPriceOfTheDay;
	
	@JsonProperty("l")
    private Double lowPriceOfTheDay;
	
	@JsonProperty("o")
    private Double openPriceOfTheDay;
	
	@JsonProperty("pc")
    private Double previousClosePrice;
	
	@JsonProperty("t")
    private Integer time;
	   public StockResponse(String jsonString) throws JsonProcessingException {
	        ObjectMapper mapper = new ObjectMapper();
	        StockResponse stockResponse = mapper.readValue(jsonString, StockResponse.class); 
	        this.currentPrice = stockResponse.currentPrice;
	        this.highPriceOfTheDay = stockResponse.highPriceOfTheDay;
	        this.lowPriceOfTheDay = stockResponse.lowPriceOfTheDay;
	        this.openPriceOfTheDay = stockResponse.openPriceOfTheDay;
	        this.previousClosePrice= stockResponse.previousClosePrice;
	        this.time= stockResponse.time;
	    }
    public void printToString() {
    	System.out.println("currentPrice   " +this.currentPrice  );
    }
}