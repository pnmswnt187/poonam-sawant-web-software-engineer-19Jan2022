package com.api.buyingfrenzy.implementation;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.api.buyingfrenzy.util.CommonConstants;

@Component
public class RestaurantsRepositoryImpl {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final static Logger logger = LogManager.getLogger(RestaurantsRepositoryImpl.class);
		
	public List<String> getRestaurantsByParam(int y, int x, String inputCondition) {
		// TODO Auto-generated method stub
		
		logger.info("----RestaurantsRepositoryImpl-getRestaurantsByParam----");
		
		StringBuilder sqlCritaria = new StringBuilder(1000);
		
		inputCondition = inputCondition.equals(CommonConstants.LESSTHEN)?CommonConstants.SIGNLESSTHEN:CommonConstants.SIGNGRTTHEN;
		
		sqlCritaria.append("select top "+y+" restaurant_name from "
				+ "Restaurants rest, Menu menu "
				+ "where rest.id=menu.rest_id "
				+ "and "+x+" "+inputCondition+" (select count(dish_name)  from Menu "
				+ "where rest_id=rest.id) "
				+ "order by menu.price desc");
		
		List<String> restaurants = jdbcTemplate.queryForList(sqlCritaria.toString(), String.class);
		return restaurants;
	}
}
