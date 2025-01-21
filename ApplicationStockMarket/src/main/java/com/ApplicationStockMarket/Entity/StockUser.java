package com.ApplicationStockMarket.Entity;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Data;
import lombok.NoArgsConstructor;


//User entry      

@Document("StockUser_entries")
@Data
@NoArgsConstructor
public class StockUser {
   
         @Id
         private ObjectId id;
         @Indexed(unique = true)
         @NonNull
         private String userName;
         @NonNull
         @Indexed(unique = true)
         private String email;
       
         @NonNull
         private String password;
         
        
         private String StockAnalysis;
 
         @DBRef
         private List<CompanyEntry> CompanyStockPriceEntries = new ArrayList<>();
         
        
         private List<String> roles;
		
};

