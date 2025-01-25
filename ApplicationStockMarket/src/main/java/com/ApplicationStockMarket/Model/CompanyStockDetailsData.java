package com.ApplicationStockMarket.Model;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CompanyStockDetailsData {
	    private String companyName;
	    private Double setPrice;
	    private Double CurrentPrice;
}
