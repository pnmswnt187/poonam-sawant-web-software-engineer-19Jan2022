package com.api.buyingfrenzy.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.api.buyingfrenzy.entities.Restaurants;
import com.api.buyingfrenzy.service.RestaurantsService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@Order(1)
public class RestaurantsJsonToDB implements CommandLineRunner{
	
	private final Logger logger = LogManager.getLogger(RestaurantsJsonToDB.class);
	
	@Autowired
    RestaurantsService restaurantsService;
	
	@Override
    public void run(String... args) throws Exception {
        logger.info("----RestaurantsJsonToDB----");
                
        ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<Restaurants>> typeReference = new TypeReference<List<Restaurants>>(){};
		InputStream inputStream = TypeReference.class.getResourceAsStream("/json/restaurant_with_menu.json");
		try {
			if(inputStream!=null) {
			List<Restaurants> restaurants = mapper.readValue(inputStream,typeReference);
			restaurantsService.save(restaurants);
			logger.info("----Restaurants Detail Saved!----");
			}
			else 
				logger.info("----No Details to save!----");
		} catch (IOException e){
			logger.info("----Unable to save Restaurants Details:----"+e.getMessage());
		}
    }

}
