package com.ApplicationStockMarket.Model;

import java.util.ArrayList;

import org.bson.types.ObjectId;

import com.mongodb.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class CompanyData {
	private String userName;
    private String email;
    private ArrayList<CompanyStockDetailsData> details=new ArrayList<>();
   
}