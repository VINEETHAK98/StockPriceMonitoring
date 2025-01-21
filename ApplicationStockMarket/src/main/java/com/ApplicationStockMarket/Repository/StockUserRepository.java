package com.ApplicationStockMarket.Repository;


import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ApplicationStockMarket.Entity.StockUser;


@Repository
public interface StockUserRepository  extends MongoRepository<StockUser,ObjectId> {

	StockUser findByUserName(String name);

	void deleteByUserName(String name);

	//void deleteByName(String name);

}
