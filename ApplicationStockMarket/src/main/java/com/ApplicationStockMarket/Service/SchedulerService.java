package com.ApplicationStockMarket.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ApplicationStockMarket.Entity.CompanyEntry;
import com.ApplicationStockMarket.Entity.StockResponse;
import com.ApplicationStockMarket.Entity.StockUser;
import com.ApplicationStockMarket.Model.CompanyData;
import com.ApplicationStockMarket.Model.CompanyStockDetailsData;
import com.ApplicationStockMarket.Repository.StockUserRepositoryImpl;
@Component
public class SchedulerService {
    @Autowired
    private KafkaTemplate<String, CompanyData> kafkaTemplate;
	@Autowired
	StockUserRepositoryImpl stockUserRepositoryImpl;
	@Autowired
	 StockApiService stockApiService;
	@Autowired
	EmailService emailService;
	

  //  @Scheduled(cron = "0 0 9 * * SUN")
    
	@Scheduled(cron = "0 */2 * * * ?")
  public void fetchUsersAndSendSaMail() {
    List<StockUser> users = stockUserRepositoryImpl.getUserForSA();
    System.out.println(users.size());
    for (StockUser user : users) {
        List<CompanyEntry> companyEntries = user.getCompanyStockPriceEntries();
        //List<Company> companies = companyEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());
        List<CompanyData> companies=new ArrayList<>();
        
        //Map<Company, Integer> sentimentCounts = new HashMap<>();
        CompanyData companyData = new CompanyData(user.getUserName(),user.getEmail(), new ArrayList<CompanyStockDetailsData>());
        
        for (int i=0;i<companyEntries.size();i++) {
        	CompanyEntry c=companyEntries.get(i);
        	StockResponse s=stockApiService.getStockDetails(c.getCompanyName());
        	if(s.getCurrentPrice()>c.getSetPrice()) {
        		//CompanyData companyData = new CompanyData(user.getEmail(),user.getUserName(), null);
        		CompanyStockDetailsData cs=new CompanyStockDetailsData(c.getCompanyName(),c.getSetPrice(),s.getCurrentPrice());
        		companyData.getDetails().add(cs);		
        	}
        }
        
      /*
       *   Map<Company, Integer> sentimentCounts = new HashMap<>();
        for (Comapany comapany : comapanies) {
            if (company != null)
                sentimentCounts.put(company, sentimentCounts.getOrDefault(company, 0) + 1);
        }
        Company mostFrequentSentiment = null;
        int maxCount = 0;
        for (Map.Entry<Company, Integer> entry : sentimentCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostFrequentSentiment = entry.getKey();
            }
        }*/
        System.out.println(companyData.getEmail());
        System.out.println(companyData.getUserName());
        System.out.println(companyData.getDetails().get(0).getCompanyName());
        System.out.println(companyData.getDetails().get(0).getSetPrice());
        System.out.println(companyData.getDetails().get(0).getCurrentPrice());
      /*  kafkaTemplate.send("weekly-sentiments", companyData.getEmail(), companyData);
        //companyEntryRepository.findById(id).orElse(null)*/
        if (companyData.getDetails()!= null) {
        	//CompanyData sentimentData = CompanyData.builder().email(user.getEmail()).sentiment("Sentiment for last 7 days " + mostFrequentSentiment).build();
            try{
                //kafkaTemplate.send("weekly-sentiments", companyData.getEmail(), companyData);
                emailService.sendEmailUser(companyData.getEmail(), "Alert", companyData);
            }catch (Exception e){
                emailService.sendEmail(companyData.getEmail(), "Sentiment for previous week","companyData");
            }
        }
    }
}
}

