package com.api.buyingfrenzy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.api.buyingfrenzy.entities.Restaurants;

@Repository
public interface RestaurantsRepository extends JpaRepository<Restaurants, Long>, PagingAndSortingRepository<Restaurants, Long> {
	
	@Query(value = "SELECT * from Restaurants r where lower(r.opening_hours) like lower(?1)", nativeQuery = true)
	public List<Restaurants> findByOpeningHours(String openingHours);
	
	@Query(value = "SELECT r.restaurant_name from Restaurants r where lower(r.opening_hours) like lower(?1)", nativeQuery = true)
	public List<String> getRestaurantsByOpeningHrs(String openingHours);

	public List<String> getRestaurantsByParam(int y,int x,String inputCondition);

	@Query(value = "SELECT r from Restaurants r where r.restaurantName = ?1")
	public Restaurants getByRestaurantName(String restaurantName);
	
	@Query(value = "SELECT r.restaurant_name from Restaurants r where lower(r.restaurant_name) like lower(?1)", nativeQuery = true)
	List<String> findByRestaurantName(String restaurant);
	
	@Query(value = "SELECT m.dish_name from Menu m where lower(m.dish_name) like lower(?1)", nativeQuery = true)
	List<String> findByDishName(String dish);

}
