package com.ApplicationStockMarket.Repository;



import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.ApplicationStockMarket.Entity.CompanyEntry;


public interface CompanyEntryRepository extends MongoRepository<CompanyEntry,ObjectId> {

}