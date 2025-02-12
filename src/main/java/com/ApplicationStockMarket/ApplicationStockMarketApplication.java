package com.ApplicationStockMarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class ApplicationStockMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationStockMarketApplication.class, args);
	}
	  @Bean
	    public RestTemplate restTemplate() {
	        return new RestTemplate();
	        
	    }
		@Bean
		public PlatformTransactionManager add(MongoDatabaseFactory dbFactory) {
			return new MongoTransactionManager(dbFactory);
			
		}
	    @Bean
	    public RedisTemplate redisTemplate(RedisConnectionFactory factory){
	        RedisTemplate redisTemplate = new RedisTemplate<>();
	        redisTemplate.setConnectionFactory(factory);

	        redisTemplate.setKeySerializer(new StringRedisSerializer());
	        redisTemplate.setValueSerializer(new StringRedisSerializer());

	        return redisTemplate;
	    }
	 


}
