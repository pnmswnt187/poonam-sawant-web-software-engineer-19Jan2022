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

import com.api.buyingfrenzy.entities.Users;
import com.api.buyingfrenzy.service.UsersService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@Order(2)
public class UsersJsonToDB implements CommandLineRunner{
	
private final Logger logger = LogManager.getLogger(UsersJsonToDB.class);
	
	@Autowired
    UsersService usersService;
	
	@Override
    public void run(String... args) throws Exception {
        logger.info("----UsersJsonToDB----");
                
        ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<Users>> typeReference = new TypeReference<List<Users>>(){};
		InputStream inputStream = TypeReference.class.getResourceAsStream("/json/users_with_purchase_history.json");
		try {
			if(inputStream!=null) {
			List<Users> users = mapper.readValue(inputStream,typeReference);
			usersService.save(users);
			logger.info("----Users Detail Saved!----");
			}
			else 
				logger.info("----No Details to save!----");
		} catch (IOException e){
			logger.info("----Unable to save Users Details:----" + e.getMessage());
		}
    }

}
