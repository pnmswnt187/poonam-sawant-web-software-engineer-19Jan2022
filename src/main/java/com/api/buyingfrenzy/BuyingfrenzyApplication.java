package com.api.buyingfrenzy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BuyingfrenzyApplication {
	
	private final static Logger logger = LogManager.getLogger(BuyingfrenzyApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BuyingfrenzyApplication.class, args);
		logger.info("----Server Started----");
	}

}
