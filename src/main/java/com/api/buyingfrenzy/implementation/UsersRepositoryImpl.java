package com.api.buyingfrenzy.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.buyingfrenzy.entities.PurchaseHistory;
import com.api.buyingfrenzy.entities.Restaurants;
import com.api.buyingfrenzy.entities.Users;
import com.api.buyingfrenzy.repositories.RestaurantsRepository;
import com.api.buyingfrenzy.repositories.UsersRepository;
import com.api.buyingfrenzy.util.CommonConstants;
import com.api.buyingfrenzy.util.CommonUtils;
import com.api.buyingfrenzy.vo.UsersVO;

@Component
public class UsersRepositoryImpl {
	
	@Autowired
	private RestaurantsRepository restaurantsRepository;
	
	@Autowired
	private UsersRepository usersRepository;
	
	private final static Logger logger = LogManager.getLogger(UsersRepositoryImpl.class);
		
	public UsersVO update(Long id, String dishName, String restaurants,UsersVO userVO) {
		
		logger.info("----UsersRepositoryImpl-update----");
		
		UsersVO usersVOOutput = new UsersVO();
		
		Users users = CommonUtils.copyProperties(userVO);
		List<PurchaseHistory> purchaseHistory = users.getPurchaseHistory().stream().filter(
				   hist -> hist.getDishName().equalsIgnoreCase(dishName) 
				&& hist.getRestaurantName().equalsIgnoreCase(restaurants)).collect(Collectors.toList());
		
		if(!purchaseHistory.isEmpty()) {
			
		Restaurants restaurant = purchaseHistory.get(0).getRestaurants();
		restaurant.setCashBalance(restaurant.getCashBalance()+purchaseHistory.get(0).getTransactionAmount());
		restaurantsRepository.save(restaurant);
		
		users.setId(id);
		double cashBalance = CommonUtils.roundWithScale((users.getCashBalance()-purchaseHistory.get(0).getTransactionAmount()));
		users.setCashBalance(cashBalance<=CommonConstants.AMTDECIMALZERO?CommonConstants.AMTZERO:cashBalance);
				
		usersVOOutput = CommonUtils.copyProperties(usersRepository.save(users));
		}
		
		return usersVOOutput;
	}
}
