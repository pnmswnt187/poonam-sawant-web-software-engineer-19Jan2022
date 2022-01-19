package com.api.buyingfrenzy.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

import com.api.buyingfrenzy.entities.Restaurants;
import com.api.buyingfrenzy.entities.Users;
import com.api.buyingfrenzy.vo.RestaurantsVO;
import com.api.buyingfrenzy.vo.UsersVO;

public class CommonUtils {
	
	public static RestaurantsVO copyProperties(Restaurants restaurants) {
		
		RestaurantsVO restaurantsVO = new RestaurantsVO();
		
		restaurantsVO.setId(restaurants.getId());
		restaurantsVO.setResturentName(restaurants.getRestaurantName());
		restaurantsVO.setCashBalance(restaurants.getCashBalance());
		restaurantsVO.setMenu(restaurants.getMenus());
		restaurantsVO.setOpeningHours(restaurants.getOpeningHours());
		
		return restaurantsVO;
	}
	
	public static Restaurants copyProperties(RestaurantsVO restaurantsVO) {
		
		Restaurants restaurants = new Restaurants();
		
		restaurants.setId(restaurantsVO.getId());
		restaurants.setRestaurantName(restaurantsVO.getResturentName());
		restaurants.setCashBalance(restaurantsVO.getCashBalance());
		restaurants.setMenu(restaurantsVO.getMenu());
		restaurants.setOpeningHours(restaurantsVO.getOpeningHours());
			
		return restaurants;
	}
	
	public static List<RestaurantsVO> copyPropertiesToList(List<Restaurants> restaurants) {
		
		return restaurants.stream()
				.map(CommonUtils::copyProperties)
				.collect(Collectors.toList());
	}
	
	public static UsersVO copyProperties(Users users) {
		
		UsersVO usersVO = new UsersVO();
		
		usersVO.setId(users.getId());
		usersVO.setName(users.getName());
		usersVO.setCashBalance(users.getCashBalance());
		usersVO.setPurchaseHistory(users.getPurchaseHistory());
		
		return usersVO;
	}
	
	public static Users copyProperties(UsersVO usersVO) {
		
		Users users = new Users();
		
		users.setId(usersVO.getId());
		users.setName(usersVO.getName());
		users.setCashBalance(usersVO.getCashBalance());
		users.setPurchaseHistory(usersVO.getPurchaseHistory());
				
		return users;
	}
	
	public static List<UsersVO> copyPropertiesToListUsers(List<Users> users) {
		
		return users.stream()
				.map(CommonUtils::copyProperties)
				.collect(Collectors.toList());
	}
	
	public static double roundWithScale(double val) {
		
		BigDecimal bd = new BigDecimal(val).setScale(2, RoundingMode.HALF_UP);
		val = bd.doubleValue();
		
		return val;
	}

}
