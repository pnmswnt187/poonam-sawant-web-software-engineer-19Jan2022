package com.api.buyingfrenzy.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.buyingfrenzy.service.UsersService;
import com.api.buyingfrenzy.vo.UsersVO;

@RestController
@RequestMapping(path="/users")
class UsersController {

	@Autowired
	private UsersService usersService;
	
	private final static Logger logger = LogManager.getLogger(UsersController.class);

	@GetMapping
	public ResponseEntity<?> getAllUserDetails()
	{
		logger.info("----getAllUserDetails----");
		
		List<UsersVO> usersVO = usersService.findAll();
				
		return new ResponseEntity<>(usersVO, HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<UsersVO> getUsersById(@PathVariable("id") Long id)
	{
		logger.info("----getUsersById----");
		logger.info("----Input Parameter----");
		logger.info("id = "+id);
		
		UsersVO usersVO = usersService.findById(id);
				
		return new ResponseEntity<>(usersVO, HttpStatus.OK);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<UsersVO> updateBuyDishTransaction(@PathVariable Long id,
			@NotNull @RequestParam("dishName") String dishName,
			@NotNull @RequestParam("restaurantName") String restaurantName) {
		
		logger.info("----update----");
		logger.info("----Input Parameter----");
		logger.info("dishName = "+dishName+" restaurantName = "+restaurantName);
		
		UsersVO usersVO= usersService.update(id, dishName, restaurantName);
		return new ResponseEntity<>(usersVO, HttpStatus.ACCEPTED); 
	}
	
}
