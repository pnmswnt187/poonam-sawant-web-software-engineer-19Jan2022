package com.api.buyingfrenzy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.api.buyingfrenzy.entities.Restaurants;
import com.api.buyingfrenzy.exception.business.RestaurantNotFound;
import com.api.buyingfrenzy.repositories.RestaurantsRepository;
import com.api.buyingfrenzy.util.CommonConstants;
import com.api.buyingfrenzy.util.CommonUtils;
import com.api.buyingfrenzy.vo.RestaurantsVO;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class RestaurantsService {
	
	@Autowired
	private RestaurantsRepository restaurantsRepository;
		
	private final Logger logger = LogManager.getLogger(RestaurantsService.class);
		
	public List<RestaurantsVO> findAll() {
		
		logger.info("----RestaurantsService-findAll----");
		
		List<Restaurants> restaurants = this.restaurantsRepository.findAll();
				
		if(restaurants.isEmpty()) {
			throw new RestaurantNotFound();
		}
		return CommonUtils.copyPropertiesToList(restaurants);
	}
	
	public RestaurantsVO findById(Long id) {
		
		logger.info("----RestaurantsService-findById----");
		
		Optional<Restaurants> restaurants = this.restaurantsRepository.findById(id);
				
		if(!restaurants.isPresent()) {
			throw new RestaurantNotFound();
		}
		return CommonUtils.copyProperties(restaurants.get());
	}
	
	public List<RestaurantsVO> findByOpeningHours(String openingHours)
	{
		logger.info("----RestaurantsService-findByOpeningHours----");
		
		List<Restaurants> restaurants=this.restaurantsRepository.findByOpeningHours(CommonConstants.LIKESIGN+openingHours+CommonConstants.LIKESIGN);
		
		if(ObjectUtils.isEmpty(restaurants)) {
			throw new RestaurantNotFound();
		}
		return CommonUtils.copyPropertiesToList(restaurants);
	}
	
	public List<String> getRestaurantsByOpeningHrs(String openingHours)
	{
		logger.info("----RestaurantsService-getRestaurantsByOpeningHrs----");
		
		List<String> restaurants=this.restaurantsRepository.getRestaurantsByOpeningHrs(CommonConstants.LIKESIGN+openingHours+CommonConstants.LIKESIGN);
		
		if(ObjectUtils.isEmpty(restaurants)) {
			throw new RestaurantNotFound();
		}
		return restaurants;
	}
	
	public List<String> getRestaurantsByParam(int y,int x,String inputCondition)
	{
		logger.info("----RestaurantsService-getRestaurantsByParam----");
		
		List<String> restaurants=this.restaurantsRepository.getRestaurantsByParam(x,y,inputCondition);
		
		if(ObjectUtils.isEmpty(restaurants)) {
			throw new RestaurantNotFound();
		}
		return restaurants;
	}
	
	
	public Restaurants save(Restaurants restaurants) {
        return restaurantsRepository.save(restaurants);
    }
	
	public void save(List<Restaurants> restaurants) {
		restaurants.forEach(r->save(r));
    }
	
	public List<String> getDetailsByRelevance(String columnName,String matchString) {
		
		logger.info("----RestaurantsService-getDetailsByRelevance----");
		
		List<String> columnData;
		
		String[] strings = new String[1];
		Arrays.fill(strings, CommonConstants.NODATA);
		columnData = Arrays.asList(strings);
		
		columnData=columnName.contains(CommonConstants.RESTAURANT)?restaurantsRepository.findByRestaurantName(CommonConstants.LIKESIGN+matchString+CommonConstants.LIKESIGN):
			columnName.contains(CommonConstants.DISH)?restaurantsRepository.findByDishName(CommonConstants.LIKESIGN+matchString+CommonConstants.LIKESIGN):columnData;
		
		return columnData;
	}
		
	public void deleteAll() {
		
		logger.info("----RestaurantsService-deleteAll----");
		
		restaurantsRepository.deleteAll();
	}
}
