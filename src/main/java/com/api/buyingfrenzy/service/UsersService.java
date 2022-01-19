package com.api.buyingfrenzy.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.buyingfrenzy.entities.Restaurants;
import com.api.buyingfrenzy.entities.Users;
import com.api.buyingfrenzy.exception.business.RestaurantNotFound;
import com.api.buyingfrenzy.exception.business.UsersNotFound;
import com.api.buyingfrenzy.repositories.RestaurantsRepository;
import com.api.buyingfrenzy.repositories.UsersRepository;
import com.api.buyingfrenzy.util.CommonUtils;
import com.api.buyingfrenzy.vo.UsersVO;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private RestaurantsRepository restaurantsRepository;
	
	private final Logger logger = LogManager.getLogger(UsersService.class);
	
	public List<UsersVO> findAll() {
		
		logger.info("----UsersService-findAll----");
		
		List<Users> users = this.usersRepository.findAll();
		
		if(users.isEmpty()) {
			throw new UsersNotFound();
		}
		
		return CommonUtils.copyPropertiesToListUsers(users);
	}
	
	public UsersVO findById(Long id) {
		
		logger.info("----UsersService-findById----");
		
		Optional<Users> users = this.usersRepository.findById(id);
				
		if(!users.isPresent()) {
			throw new UsersNotFound();
		}
		return CommonUtils.copyProperties(users.get());
	}
				
	public Users save(Users users) {		
		if(users.getPurchaseHistory()!=null)
		users.getPurchaseHistory().forEach(p->{
			Restaurants r = this.restaurantsRepository.getByRestaurantName(p.getRestaurantName());
			p.setRestaurants(r);
		});
        return usersRepository.save(users);
    }
	
	public void save(List<Users> users) {
		users.forEach(u->save(u));
    }
	
	@Transactional
	public UsersVO update(Long id, String dishName, String restaurants) {
		
		logger.info("----UsersService-update----");
		
		UsersVO userVO = findById(id);
		
		UsersVO usersVOOutput = this.usersRepository.update(id,dishName,restaurants,userVO);
		
		if(usersVOOutput.getPurchaseHistory()==null) {
			throw new RestaurantNotFound();
		}
		
		return usersVOOutput;
	}
	
	public void deleteAll() {
		
		logger.info("----UsersService-deleteAll----");
		
		usersRepository.deleteAll();
	}
	
}
