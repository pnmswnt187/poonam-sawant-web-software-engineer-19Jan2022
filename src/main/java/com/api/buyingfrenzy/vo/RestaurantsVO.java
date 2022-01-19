package com.api.buyingfrenzy.vo;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.api.buyingfrenzy.entities.Menu;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class RestaurantsVO {
	
	@JsonIgnore
	private Long id;
		
	@JsonProperty(value = "cashBalance")
	@NotNull(message = "cashBalance field is required")
	private double cashBalance;
	
	private List<Menu> menu;
	
	@JsonProperty(value = "openingHours")
	@NotBlank(message = "openingHours field is required")
	private String openingHours;
	
	
	@JsonProperty(value = "restaurantName")
	@NotBlank(message = "restaurantName field is required")
	private String resturentName;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getResturentName() {
		return resturentName;
	}

	public void setResturentName(String resturentName) {
		this.resturentName = resturentName;
	}

	public double getCashBalance() {
		return cashBalance;
	}

	public void setCashBalance(double cashBalance) {
		this.cashBalance = cashBalance;
	}

	public List<Menu> getMenu() {
		return menu;
	}

	public void setMenu(List<Menu> menu) {
		this.menu = menu;
	}

	public String getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(String openingHours) {
		this.openingHours = openingHours;
	}

}
