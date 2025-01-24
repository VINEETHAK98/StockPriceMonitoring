package com.ApplicationStockMarket.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;


import com.ApplicationStockMarket.Entity.CompanyEntry;

public class SchedulerService {
    @Autowired
    private KafkaTemplate<String, CompanyEntry> kafkaTemplate;

    @Scheduled(cron = "0 0 9 * * SUN")
    
    public void fetchUsersAndSendSaMail() {
    	
    }
    

}
