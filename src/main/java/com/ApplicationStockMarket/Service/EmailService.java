package com.ApplicationStockMarket.Service;




import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ApplicationStockMarket.Model.CompanyData;
import com.ApplicationStockMarket.Model.CompanyStockDetailsData;

@Service
@Slf4j

public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String body) {
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(to);
            mail.setSubject(subject);
            mail.setText(body);
            javaMailSender.send(mail);
        } catch (Exception e) {
            log.error("Exception while sendEmail ", e);
        }
    }
        public void sendEmailUser(String to, String subject, CompanyData companyData) {
        	try {
        	    SimpleMailMessage mail = new SimpleMailMessage();
        	    mail.setTo(to);
        	    mail.setSubject(subject);

        	    StringBuilder bodyBuilder = new StringBuilder();
        	    bodyBuilder.append("Hi "+ companyData.getUserName()+",").append("\n");

        	    bodyBuilder.append("List of Companies whose Stock Price has increased").append("\n");
          	  
        	    List<CompanyStockDetailsData> list=companyData.getDetails();
        	    for (int i=0;i<list.size();i++) {
        	    	bodyBuilder.append(i+1+". ");
        	        //bodyBuilder.append(list.get(i).getCompanyName()).append(": ").append(list.get(i).getSetPrice()).append(": ").append(list.get(i).getCurrentPrice()).append("\n");
        	        bodyBuilder.append("CompanyName").append(": ").append(list.get(i).getCompanyName()).append("\n");
        	        bodyBuilder.append("Set Stock Price").append(": ").append(list.get(i).getSetPrice()).append("\n");
        	        bodyBuilder.append("Current stock Price").append(": ").append(list.get(i).getCurrentPrice()).append("\n"); 
        	        bodyBuilder.append("\n");
        	    }
        	 
        	    String body = bodyBuilder.toString();

        	    mail.setText(body);
        	    javaMailSender.send(mail);
        	} catch (Exception e) {
        	    // Handle exceptions (e.g., log the error)
        	    log.error("Error sending email", e);
        	}
    }


}