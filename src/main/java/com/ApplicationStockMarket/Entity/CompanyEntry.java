package com.ApplicationStockMarket.Entity;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import com.mongodb.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("Company_entries")
public class CompanyEntry {
	
	  @Id
      private ObjectId id;
      @NonNull
      private String companyName;

      @NonNull
      private Double setPrice;
      
      private Double currentPrice;
}
