package com.api.buyingfrenzy.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.buyingfrenzy.service.RestaurantsService;
import com.api.buyingfrenzy.util.CommonConstants;
import com.api.buyingfrenzy.vo.RestaurantsVO;

@RestController
@RequestMapping(path="/restaurants")
class RestaurantsController {

	@Autowired
	private RestaurantsService restaurantsService;
	
	private final static Logger logger = LogManager.getLogger(RestaurantsController.class);
		
	@PostMapping
	public ResponseEntity<?> getAllRestaurantsDetails()
	{
		logger.info("----getAllRestaurantsDetails----");
		
		List<RestaurantsVO> restaurantsVO = restaurantsService.findAll();
				
		return new ResponseEntity<>(restaurantsVO, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<?> getRestaurantsByOpeningHrs(@NotNull @RequestParam("openingHours") String openingHours)
	{
		logger.info("----getRestaurantsByOpeningHrs----");
		logger.info("----Input Parameter----");
		logger.info("openingHours = "+openingHours);
		
		List<String> restaurants = restaurantsService.getRestaurantsByOpeningHrs(openingHours);
		
		return new ResponseEntity<>(restaurants, HttpStatus.OK);
	}
	
	@GetMapping(value = "/toprestaurants")
	public ResponseEntity<?> getRestaurantsByParam(@NotNull @RequestParam(name="y") int y,
			@NotNull @RequestParam(name="x") int x,
			@RequestParam(name="inputCondition",defaultValue = CommonConstants.GRTTHEN) String inputCondition)
	{
		logger.info("----getRestaurantsByParam----");
		logger.info("----Input Parameter----");
		logger.info("y = "+y+" x = "+x+" inputCondition = ");
		
		List<String> restaurants = restaurantsService.getRestaurantsByParam(x,y,inputCondition);
		return new ResponseEntity<>(restaurants, HttpStatus.OK);
	}
		
	@GetMapping(value = "/relevance")
	public ResponseEntity<?> getDetailsByRelevance(
			@NotNull @RequestParam("columnname") String columnName,
			@NotNull @RequestParam("matchstring") String matchString)
	{
		logger.info("----getDetailsByRelevance----");
		logger.info("----Input Parameter----");
		logger.info("columnName = "+columnName+" matchString = "+matchString);
		
		List<String> columnData = restaurantsService.getDetailsByRelevance(columnName,matchString);
				
		return new ResponseEntity<>(columnData, HttpStatus.OK);
	}
	
}
