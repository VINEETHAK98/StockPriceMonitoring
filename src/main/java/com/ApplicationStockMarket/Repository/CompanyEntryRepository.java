package com.ApplicationStockMarket.Repository;



import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ApplicationStockMarket.Entity.CompanyEntry;
import com.ApplicationStockMarket.Entity.StockUser;

@Repository
public interface CompanyEntryRepository extends MongoRepository<CompanyEntry,ObjectId> {

	
}