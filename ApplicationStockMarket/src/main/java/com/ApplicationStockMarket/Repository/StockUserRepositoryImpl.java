package com.ApplicationStockMarket.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ApplicationStockMarket.Entity.StockUser;


public class StockUserRepositoryImpl {


    @Autowired
    private MongoTemplate mongoTemplate;

    public List<StockUser> getUserForSA() {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$"));
        //query.addCriteria(Criteria.where("StockAnalysis").is(true));
        return mongoTemplate.find(query, StockUser.class);
    }


}